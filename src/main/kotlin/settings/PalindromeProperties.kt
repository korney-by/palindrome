package settings

import intefaces.GamePropertiesInterface
import intefaces.HistoryRepositoryInterface
import intefaces.RatingRepositoryInterface

class PalindromeProperties(
    override val gameName: String,
    override val ratingRepository: RatingRepositoryInterface,
    override val historyRepository: HistoryRepositoryInterface
) : GamePropertiesInterface {

    override fun conditionChecker(phrase: String): Boolean {
        val compressedPhrase = phrase.filter { it != ' ' }.lowercase()
        if (compressedPhrase.length < 2) return false
        for (i in 0..compressedPhrase.lastIndex / 2) {
            if (compressedPhrase[i] != compressedPhrase[compressedPhrase.lastIndex - i])
                return false
        }
        return true
    }

    override fun ratingCalculator(phrase: String): Int =
        phrase.filter { it != ' ' }.length

}