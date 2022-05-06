package intefaces

import entity.CommandHelp
import entity.UserRating

interface GameShowInformationInterface {
    fun showHelp(help: List<CommandHelp>)
    fun showTop(topList: List<UserRating>)
}