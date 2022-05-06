package core.intefaces

interface GamePropertiesInterface {
    val gameName: String
    val ratingRepository: RatingRepositoryInterface
    val historyRepository: HistoryRepositoryInterface

    fun conditionChecker(phrase: String): Boolean
    fun ratingCalculator(phrase: String): Int
}
