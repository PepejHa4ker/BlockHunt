@file:Suppress("DEPRECATION")

package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.util.Utils.sendText

class OpExecutor(private val sender: CommandSender, args: Array<out String>) {
    private val target = sender.server.getPlayer(args[0])
    private val offlineTarget = sender.server.getOfflinePlayer(args[0])
    private val success = "§a§lУспешно"

    fun op() {
        if (target != null && target.isOnline) {
            target.isOp = true
            sender.sendText(success)
        } else {
            offlineTarget.isOp = true
            sender.sendText(success)
        }
    }


    fun deOp() {
        if (target != null && target.isOnline) {
            target.isOp = false
            sender.sendText(success)
        } else {
            offlineTarget.isOp = false
            sender.sendText(success)
        }
    }
}
