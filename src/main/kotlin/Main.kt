import repository.HistoryRepository
import repository.RatingRepository
import settings.PalindromeCallbacks
import settings.PalindromeInput
import settings.PalindromeProperties
import settings.PalindromeShowInfo

fun main() {
    val gamePalindrome = GameCore(
        PalindromeProperties(
            gameName = "--==[ ПАЛИНДРОМ ]==--",
            ratingRepository = RatingRepository,
            historyRepository = HistoryRepository
        ),
        PalindromeCallbacks(),
        PalindromeInput(),
        PalindromeShowInfo()
    )

    gamePalindrome.start()

}

