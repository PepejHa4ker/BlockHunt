package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.chatDisable
import ru.mclegendary.blockhunt.BlockHunt.Companion.chatEnable
import ru.mclegendary.blockhunt.BlockHunt.Companion.isChatEnabled
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
                if (!isChatEnabled()) {
                   chatEnable(sender)
                } else
                    sender.sendMessage("$prefix §cЧат уже включен!"); return true

            }
            args[0].equals("off", true) -> {
                if (isChatEnabled()) {
                    chatDisable(sender)
                } else sender.sendMessage("$prefix §cЧат уже выключен!"); return true
            }

            args[0].equals("toggle", true) -> {
                if (isChatEnabled()) {
                    chatDisable(sender)
                } else chatDisable(sender)

            }

            args[0].equals("info", true) -> {
                if(isChatEnabled()){
                    sender.sendMessage("$prefix §cЧат включен")
                } else sender.sendMessage("$prefix §cЧат выключен")
            }


            else -> return false

        }

        return true
    }

}