package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.executors.ReloadExecutor

import ru.mclegendary.blockhunt.executors.KickExecutor as KE

class Bh : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        when {
            args.isEmpty() || args.size == 1 -> {
                sender.sendMessage("§cЧто-то не так с аргументами:"); return false
            }

            args[0].equals("kick", true) -> {
               KE(sender, args).kick()
            }

            args[0].equals("kickall", true) -> {
                KE(sender, args).kickAll()
            }

            args[0].equals("reload", true) -> {
                ReloadExecutor(sender, args).reloadCfg()

            }

            else -> return false
        } 
        return true
    }
}