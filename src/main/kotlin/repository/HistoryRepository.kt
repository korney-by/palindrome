package repository

import core.intefaces.HistoryRepositoryInterface

object HistoryRepository : HistoryRepositoryInterface {
    private val phraseMap = mutableSetOf<String>()

    override fun addPhrase(userName: String, phrase: String) {
        phraseMap.add(getSentence(userName, phrase))
    }

    override fun isPhraseUsed(userName: String, phrase: String): Boolean =
        phraseMap.contains(getSentence(userName, phrase))

    private fun getSentence(userName: String, phrase: String): String =
        "$userName|${phrase.filter { it != ' ' }.lowercase()}"
}
