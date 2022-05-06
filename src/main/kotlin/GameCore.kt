import entity.UserRating
import intefaces.*

class GameCore(
    private val properties: GamePropertiesInterface,
    private val callbacks: GameCallbackInterface,
    private val input: GameInputInterface,
    private val showInfo: GameShowInformationInterface
) {
    private var isExit = false

    private val ratingRepo: RatingRepositoryInterface = properties.ratingRepository
    private val historyRepo: HistoryRepositoryInterface = properties.historyRepository

    private val conditionChecker: ((phrase: String) -> Boolean) = properties::conditionChecker
    private val ratingCalculator: ((phrase: String) -> Int) = properties::ratingCalculator

    private val onStart: ((gameName: String) -> Unit) = callbacks::onStart
    private val onPhraseAlreadyUsed: ((phrase: String) -> Unit) = callbacks::onPhraseAlreadyUsed
    private val onPhraseAccepted: ((phrase: String, rating: Int) -> Unit) = callbacks::onPhraseAccepted
    private val onPhraseNotCorrespond: ((phrase: String) -> Unit) = callbacks::onPhraseNotCorrespond
    private val onCurrentUserChanged: ((userName: String) -> Unit) = callbacks::onCurrentUserChanged
    private val onLeaderChanged: ((userName: String, rating: Int) -> Unit) = callbacks::onLeaderChanged

    private val commands = Commands()

    init {
        commands.apply {
            assignCommand("/?", "Помощь", ::showHelp)
            assignCommand("/u", "Сменить пользователя", ::changeUser)
            assignCommand("/t", "Показать TOP-5", ::showTop)
            assignCommand("/e", "Выход", ::exit)
        }
    }

    var currentUser: String? = null
        set(value) {
            if (field != value) {
                field = value
                value?.let { onCurrentUserChanged(it) }
            }
        }

    private var leader: UserRating? = null
        set(value) {
            if (field != value) {
                field = value
                value?.let { onLeaderChanged(it.userName, it.rating) }
            }
        }

    private fun checkPhrase(phrase: String) {
        if (!conditionChecker(phrase)) {
            onPhraseNotCorrespond(phrase)
        } else {
            currentUser?.let {
                if (historyRepo.isPhraseUsed(it, phrase)) {
                    onPhraseAlreadyUsed(phrase)
                } else {
                    val rating = ratingCalculator(phrase)
                    ratingRepo.addRating(it, rating)
                    historyRepo.addPhrase(it, phrase)
                    onPhraseAccepted(phrase, rating)
                }
                if (ratingRepo.leader != leader) {
                    leader = ratingRepo.leader
                }
            }
        }
    }

    private fun changeUser() {
        currentUser = input.promptInputUser()
    }

    private fun exit() {
        isExit = true
    }

    private fun showHelp() {
        showInfo.showHelp(commands.getHelpList())
    }

    private fun showTop() {
        showInfo.showTop(ratingRepo.getTopList())
    }

    fun start() {
        onStart(properties.gameName)
        showHelp()
        changeUser()

        while (!isExit) {
            currentUser?.let { user ->
                val inputStr = input.promptInput(user)
                if (Commands.isCommand(inputStr)) {
                    commands.run(inputStr)
                } else checkPhrase(inputStr)
            }
        }
    }
}

