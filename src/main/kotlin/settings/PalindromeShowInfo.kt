package settings

import entity.CommandHelp
import entity.UserRating
import intefaces.GameShowInformationInterface

class PalindromeShowInfo : GameShowInformationInterface {

    override fun showHelp(help: List<CommandHelp>) {
        val result = StringBuffer()
        help.forEach { result.append("${it.command}-${it.description}, ") }
        println("[Команды: $result]")
    }

    override fun showTop(topList: List<UserRating>) {
        val result = StringBuffer()
        topList.forEachIndexed { index, user ->
            result.append(" ${index+1}. ${user.userName} - рейтинг ${user.rating}\n")
        }
        println("[ Доска лидеров ]\n$result")
    }

}