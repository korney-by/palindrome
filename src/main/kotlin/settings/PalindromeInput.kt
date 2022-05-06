package settings

import intefaces.GameInputInterface

class PalindromeInput : GameInputInterface {
    override fun promptInput(currentUserName: String): String {
        print("[$currentUserName]> ")
        return readln()
    }

    override fun promptInputUser(): String {
        var userName: String = ""
        while (userName=="") {
            print("Введите имя нового игрока: ")
            userName = readln()
        }
        return userName
    }
}