package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender

import ru.mclegendary.blockhunt.executors.ExChangeExecutor.toCoins
import ru.mclegendary.blockhunt.executors.ExChangeExecutor.toMoney

class ExChange : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.isEmpty()) return false
        if (sender !is ConsoleCommandSender) return false

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