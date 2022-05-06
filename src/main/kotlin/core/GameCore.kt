package core

import core.intefaces.GameCallbackInterface
import core.intefaces.GameInputInterface
import core.intefaces.GamePropertiesInterface
import core.intefaces.GameShowInformationInterface
import core.intefaces.HistoryRepositoryInterface
import core.intefaces.RatingRepositoryInterface

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

    private val control = Control().apply {
        Control.Command.values().forEach { command ->
            when (command) {
                Control.Command.HELP -> assignCommand(command.value, ::showHelp)
                Control.Command.RATING -> assignCommand(command.value, ::showUserRating)
                Control.Command.TOP -> assignCommand(command.value, ::showTop)
                Control.Command.USER -> assignCommand(command.value, ::changeUser)
                Control.Command.EXIT -> assignCommand(command.value, ::exit)
            }
        }
    }

    private var currentUser: String? = null
        set(value) {
            if (field != value) {
                field = value
                value?.let { onCurrentUserChanged(it) }
            }
        }

    private var leader: String? = null
        set(value) {
            field = value
            value?.let { leaderName ->
                ratingRepo.leader?.rating?.let { rating ->
                    onLeaderChanged(leaderName, rating)
                }
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
                if (ratingRepo.leader?.userName != leader) {
                    leader = ratingRepo.leader?.userName
                }
            }
        }
    }

    private fun changeUser() {
        input.promptInputUser().apply {
            currentUser = this
            ratingRepo.addRating(this, 0)
        }
    }

    private fun exit() {
        isExit = true
    }

    private fun showHelp() {
        showInfo.showHelp(control.getHelpList())
    }

    private fun showTop() {
        showInfo.showTop(ratingRepo.getTopList())
    }

    private fun showUserRating() {
        currentUser?.let {
            showInfo.showUserRating(it, ratingRepo.getUserRating(it))
        }
    }

    fun start() {
        onStart(properties.gameName)
        showHelp()
        changeUser()

        while (!isExit) {
            currentUser?.let { user ->
                val inputStr = input.promptInput(user)
                if (Control.isCommand(inputStr)) {
                    control.run(inputStr)
                } else checkPhrase(inputStr)
            }
        }
    }
}
