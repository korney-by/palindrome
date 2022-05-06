package settings

import core.intefaces.GameShowInformationInterface
import entity.CommandDescription
import entity.UserRating

class PalindromeShowInfo : GameShowInformationInterface {

    override fun showHelp(help: List<CommandDescription>) {
        val result = StringBuffer()
        help.forEach { result.append("${it.command}-${it.description}, ") }
        println("[Команды: $result]")
    }

    override fun showTop(topList: List<UserRating>) {
        val result = StringBuffer()
        topList.forEachIndexed { index, user ->
            result.append(" ${index + 1}. ${user.userName} - рейтинг ${user.rating}\n")
        }
        println("\n[ Доска лидеров ]\n$result")
    }

    override fun showUserRating(userName: String, rating: Int) {
        println("игрок: $userName - рейтиг $rating")
    }
}
