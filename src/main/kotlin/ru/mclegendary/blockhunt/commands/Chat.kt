package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.listener
import ru.mclegendary.blockhunt.prefix

class Chat : CommandExecutor{
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
       if(args.isEmpty()) return false
        when {
            args[0].equals("on", true) -> {
                listener.isChatProcessed = true
                sender.sendMessage("$prefix §cЧат был успешно включен")
            }
            args[0].equals("off", true) -> {
                listener.isChatProcessed = false
                sender.sendMessage("$prefix §cЧат был успешно выключен")
            }
            else -> return false

        }
        return true
    }

}