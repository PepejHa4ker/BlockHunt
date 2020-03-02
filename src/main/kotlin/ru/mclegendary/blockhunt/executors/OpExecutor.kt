@file:Suppress("DEPRECATION")

package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender

class OpExecutor(private val sender: CommandSender, args: Array<out String>) {
    private val target = sender.server.getPlayer(args[0])
    private val offlineTarget = sender.server.getOfflinePlayer(args[0])
    private val success = "§a§lУспешно"

    fun op() {
        if (target != null && target.isOnline) {
            target.isOp = true
            sender.sendMessage(success)
        } else {
            offlineTarget.isOp = true
            sender.sendMessage(success)}}



    fun deOp() {
        if (target != null && target.isOnline) {
            target.isOp = false
            sender.sendMessage(success)
        }   else {
            offlineTarget.isOp = false
            sender.sendMessage(success)}}}
