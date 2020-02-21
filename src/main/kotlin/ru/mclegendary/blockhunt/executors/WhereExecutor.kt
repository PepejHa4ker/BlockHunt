package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix
import ru.mclegendary.blockhunt.BlockHunt.Companion.sendMsg

object WhereExecutor {

    fun whereAre(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[0])

        sendMsg("§aИгрок ${player.displayName}§a сейчас в мире: §c${player.world.name}", sender)
    }
}