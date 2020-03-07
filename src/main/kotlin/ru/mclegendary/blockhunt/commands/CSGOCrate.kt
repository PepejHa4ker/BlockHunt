package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender

import ru.mclegendary.blockhunt.executors.CSGOCrateExecutor.csgoGive
import ru.mclegendary.blockhunt.util.Messages
import ru.mclegendary.blockhunt.util.Utils.sendText

class CSGOCrate : CommandExecutor {
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
        if (args.isEmpty()) return false

        when {
            (args[0].equals("give", true)) -> csgoGive(sender, args)

            else -> return false
        }

        return true
    }
}

