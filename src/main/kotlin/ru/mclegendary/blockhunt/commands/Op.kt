package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender


import ru.mclegendary.blockhunt.executors.OpExecutor
import ru.mclegendary.blockhunt.util.onlyCons
import ru.mclegendary.blockhunt.util.sendText

class Op : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is ConsoleCommandSender) {
            sender.sendText(onlyCons)
            return true
        }
            if (args.isNotEmpty())
                OpExecutor(sender, args).op()

        return true
    }
}


