package core.intefaces

interface GameCallbackInterface {
    fun onStart(gameName: String)
    fun onPhraseAlreadyUsed(phrase: String)
    fun onPhraseNotCorrespond(phrase: String)
    fun onLeaderChanged(userName: String, rating: Int)
    fun onCurrentUserChanged(userName: String)
    fun onPhraseAccepted(phrase: String, rating: Int)
}
