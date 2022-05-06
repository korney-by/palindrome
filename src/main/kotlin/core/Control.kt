package core

import entity.CommandDescription

class Control {

    private val commandsHelp = mutableMapOf<String, String>()
    private val commandsList = mutableMapOf<String, () -> Unit>()

    init {
        Command.values().forEach { command ->
            when (command) {
                Command.HELP -> assignDescription(command.value, "Помощь")
                Command.USER -> assignDescription(command.value, "Сменить игрока")
                Command.RATING -> assignDescription(command.value, "Рейтинг игрока")
                Command.TOP -> assignDescription(command.value, "Доска лидеров")
                Command.EXIT -> assignDescription(command.value, "Выход")
            }
        }
    }

    fun assignCommand(commandName: String, function: () -> Unit) {
        commandsList[commandName] = function
    }

    private fun assignDescription(commandName: String, description: String) {
        commandsHelp[commandName] = description
    }

    fun getHelpList(): List<CommandDescription> =
        commandsHelp.map {
            CommandDescription(
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

    enum class Command(val value: String) {
        HELP("/?"),
        USER("/u"),
        RATING("/r"),
        TOP("/t"),
        EXIT("/e")
    }
}
