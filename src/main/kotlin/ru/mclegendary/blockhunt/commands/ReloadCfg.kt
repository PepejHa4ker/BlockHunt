package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix

class ReloadCfg : CommandExecutor{
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {

        instance.reloadConfig()
        sender.sendMessage("$prefix ${instance.config.getString("CfgReloaded").replace('&', '§')}")

        return true
    }

}