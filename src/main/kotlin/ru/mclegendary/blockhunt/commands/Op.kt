package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender


import ru.mclegendary.blockhunt.executors.OpExecutor
import ru.mclegendary.blockhunt.util.Messages
import ru.mclegendary.blockhunt.util.Utils.sendText

class Op : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is ConsoleCommandSender) {
            sender.sendText(Messages.onlyCons)
            return true
        }
            if (args.size == 1) {
                OpExecutor(sender, args).op()

            } else return false

        return true
    }
}


