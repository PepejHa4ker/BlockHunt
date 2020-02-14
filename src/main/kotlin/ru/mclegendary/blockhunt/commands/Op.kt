package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender

import ru.mclegendary.blockhunt.executors.OpExecutor

class Op : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender is ConsoleCommandSender) {
            if (args.isNotEmpty()) {

                OpExecutor(sender, args).op()

            } else  sender.sendMessage("§cКоманда неверно записана!"); return false
        } else  sender.sendMessage("§c§lТолько для консоли!")

        return true
    }
}