package core.intefaces

import entity.CommandDescription
import entity.UserRating

interface GameShowInformationInterface {
    fun showHelp(help: List<CommandDescription>)
    fun showTop(topList: List<UserRating>)
    fun showUserRating(userName: String, rating: Int)
}
