package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix

class ReloadExecutor(val sender: CommandSender, args: Array<out String>) {


    fun reloadCfg() {
        if(!sender.hasPermission("blockhunt.reloadconfig")) return sender.sendMessage("$prefix ${instance.config.getString("NoPermission").replace('&', '§')}")
        instance.reloadConfig()
        sender.sendMessage("$prefix ${instance.config.getString("CfgReloaded").replace('&', '§')}")
    }

}