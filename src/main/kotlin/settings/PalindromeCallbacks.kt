package settings

import core.intefaces.GameCallbackInterface

class PalindromeCallbacks : GameCallbackInterface {

    override fun onStart(gameName: String) {
        println(gameName)
    }

    override fun onPhraseAlreadyUsed(phrase: String) {
        println("\"$phrase\" уже использовалось")
    }

    override fun onPhraseNotCorrespond(phrase: String) {
        println("\"$phrase\" не палиндром!")
    }

    override fun onLeaderChanged(userName: String, rating: Int) {
        println("Теперь лидер $userName, его рейтинг: $rating")
    }

    override fun onCurrentUserChanged(userName: String) {
        println("Теперь играет $userName, введите палиндром после знака \">\"")
    }

    override fun onPhraseAccepted(phrase: String, rating: Int) {
        println("за \"$phrase\" начислено $rating очк.")
    }
}
