package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix

import ru.mclegendary.blockhunt.executors.OpExecutor

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
                sender.sendMessage("$prefix ${instance.config.getString("InvalidCommand").replace('&', '§')}"); return false
            }
        } else {
            sender.sendMessage("$prefix ${instance.config.getString("NoPermission").replace('&', '§')}")
        }


        return true
    }
}