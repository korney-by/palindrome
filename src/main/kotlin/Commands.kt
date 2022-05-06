import entity.CommandHelp
import entity.UserRating
import repository.RatingRepository

class Commands() {
    private val commandsHelp = mutableMapOf<String, String>()
    private val commandsList = mutableMapOf<String, () -> Unit>()

    fun assignCommand(commandName: String, description: String, function: () -> Unit) {
        commandsList[commandName] = function
        commandsHelp[commandName] = description
    }


    fun getHelpList(): List<CommandHelp> =
        commandsHelp.map {
            CommandHelp(
                command = it.key,
                description = it.value
            )
        }



    fun run(commandName: String) {
        val commandFunction = commandsList[commandName]
        commandFunction?.invoke()
    }

    companion object {
        fun isCommand(commandStr: String): Boolean =
            commandStr.isNotEmpty() && commandStr[0] == '/'
    }
}