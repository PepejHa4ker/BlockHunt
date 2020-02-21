@file:Suppress("DEPRECATION")

package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.sendMsg

class OpExecutor(private val sender: CommandSender, args: Array<out String>) {
    private val target = sender.server.getPlayer(args[0])
    private val offlineTarget = sender.server.getOfflinePlayer(args[0])
    private val success = "§a§lУспешно"

    fun op() {
        if (target != null && target.isOnline) {
            target.isOp = true
            sendMsg(success, sender)
        } else {
            offlineTarget.isOp = true
            sendMsg(success, sender)
        }
    }

    fun deOp() {
        if (target != null && target.isOnline) {
            target.isOp = false
            sendMsg(success, sender)
        } else {
            offlineTarget.isOp = false
            sendMsg(success, sender)
        }
    }
}