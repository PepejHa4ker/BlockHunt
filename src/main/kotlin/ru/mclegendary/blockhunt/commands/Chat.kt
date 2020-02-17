package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.listener
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix

class Chat : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if(args.isEmpty()) return false
        when {
            args[0].equals("on", true) -> {
                if (!listener.isChatProcessed) {
                    listener.isChatProcessed = true
                    sender.sendMessage("$prefix §cЧат был успешно включен")
                } else {
                    sender.sendMessage("$prefix §cЧат уже включен!")
                    return true
                }
            }
            args[0].equals("off", true) -> {
                if (listener.isChatProcessed) {
                    listener.isChatProcessed = false
                    sender.sendMessage("$prefix §cЧат был успешно выключен")
                } else {
                    sender.sendMessage("$prefix §cЧат уже выключен!")
                    return true
                }


            }
            args[0].equals("toggle", true) -> {
                if (listener.isChatProcessed) {
                    listener.isChatProcessed = false
                    sender.sendMessage("$prefix §cЧат был успешно выключен")
                } else {
                    listener.isChatProcessed = true
                    sender.sendMessage("$prefix §cЧат был успешно включен")
                }
            }

            args[0].equals("info", true) -> {
                if(listener.isChatProcessed){
                    sender.sendMessage("$prefix §cЧат включен")
                } else sender.sendMessage("$prefix §cЧат выключен")
            }

            else -> return false

        }
        return true
    }

}