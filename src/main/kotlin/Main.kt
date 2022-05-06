import core.GameCore
import repository.HistoryRepository
import repository.RatingRepository
import settings.PalindromeCallbacks
import settings.PalindromeInput
import settings.PalindromeProperties
import settings.PalindromeShowInfo

fun main() {
    GameCore(
        PalindromeProperties(
            gameName = "--==[ ПАЛИНДРОМ ]==--",
            ratingRepository = RatingRepository.apply { topCount = 5 },
            historyRepository = HistoryRepository
        ),
        PalindromeCallbacks(),
        PalindromeInput(),
        PalindromeShowInfo()
    ).start()
}
