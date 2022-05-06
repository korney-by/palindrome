package settings

import core.intefaces.GameInputInterface

class PalindromeInput : GameInputInterface {

    override fun promptInput(currentUserName: String): String {
        print("[$currentUserName]> ")
        return readln()
    }

    override fun promptInputUser(): String {
        var userName = ""
        while (userName == "") {
            print("Введите имя нового игрока: ")
            userName = readln()
        }
        return userName
    }
}
