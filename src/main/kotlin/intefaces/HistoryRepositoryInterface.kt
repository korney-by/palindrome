package intefaces

interface HistoryRepositoryInterface {
    fun addPhrase(userName: String, phrase: String)
    fun isPhraseUsed(userName: String, phrase: String): Boolean
}