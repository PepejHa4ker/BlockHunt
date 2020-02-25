package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.listener
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix

class ChatSwitchExecutor(val sender: CommandSender) {


    fun chatEnable(){
        if(!isChatEnabled()) {
            listener.isChatProcessed = true
            sender.sendMessage("$prefix ${instance.config.getString("ChatOnSuccess").replace('&', '§')}")
        } else return sender.sendMessage("$prefix ${instance.config.getString("ChatAlreadyEnabled").replace('&', '§')}")

    }

    fun chatDisable() {
        if (isChatEnabled()) {
            listener.isChatProcessed = false
            sender.sendMessage("$prefix ${instance.config.getString("ChatOffSuccess").replace('&', '§')}")
        } else return sender.sendMessage("$prefix ${instance.config.getString("ChatAlreadyDisabled").replace('&', '§')}")
    }


    fun isChatEnabled() : Boolean {
        return listener.isChatProcessed

    }
}