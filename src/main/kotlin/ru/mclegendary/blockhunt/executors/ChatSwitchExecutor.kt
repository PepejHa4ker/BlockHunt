package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.listener
import ru.mclegendary.blockhunt.util.*
import ru.mclegendary.blockhunt.util.sendText

class ChatSwitchExecutor(val sender: CommandSender) {

    fun chatEnable() {
        if (!isChatEnabled()) {
            listener.isChatProcessed = true
            sender.sendText("$chatEnabled")
        } else return sender.sendText("$chatAlreadyEnabled")
    }


    fun chatDisable() {
        if (isChatEnabled()) {
            listener.isChatProcessed = false
            sender.sendText("$chatDisabled")
        } else return sender.sendText("$chatAlreadyDisabled")


    }

    fun isChatEnabled(): Boolean = listener.isChatProcessed
}




