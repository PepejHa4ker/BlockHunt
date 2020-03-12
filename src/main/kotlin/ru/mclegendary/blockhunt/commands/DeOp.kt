package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender


import ru.mclegendary.blockhunt.executors.OpExecutor
import ru.mclegendary.blockhunt.util.Messages
import ru.mclegendary.blockhunt.util.Utils.sendText

class DeOp : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender is ConsoleCommandSender) {
            if (args.isNotEmpty()) {

                OpExecutor(sender, args).deOp()

            } else {
                sender.sendText(Messages.INVALID_COMMAND)
                 return false
            }
        } else {
            sender.sendText(Messages.NO_PERM)
        }

        return true
    }
}

