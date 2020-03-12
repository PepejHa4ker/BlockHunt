package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender

import ru.mclegendary.blockhunt.util.*
import ru.mclegendary.blockhunt.executors.ExChangeExecutor.toCoins
import ru.mclegendary.blockhunt.executors.ExChangeExecutor.toMoney
import ru.mclegendary.blockhunt.util.Utils.sendText

class ExChange : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is ConsoleCommandSender) {
            sender.sendText(Messages.ONLY_CONSOLE)
            return true
        }

        if (args.isEmpty() || args.size < 4) return false


        when {
            args[0].equals("coins", true) -> {
                toCoins(sender, args)
            }


            args[0].equals("money", true) -> {
                toMoney(sender, args)
            }
        }



        return true
    }
}
