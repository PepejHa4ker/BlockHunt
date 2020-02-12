package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender

object WhereExecutor {

    fun whereAre(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[0])

        sender.sendMessage("§aИгрок ${player.displayName} §aсейчас в мире: §c${player.world.name}")
    }
}