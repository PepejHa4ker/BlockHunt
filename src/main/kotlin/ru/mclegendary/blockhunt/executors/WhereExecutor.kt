package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance


object WhereExecutor {

    fun whereAre(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[0])

        sender.sendMessage(instance.config.getString("PlayerInWorld")
            .replace('&', '§')
            .replace("%PLAYER%", player.name)
            .replace("%WORLD%", player.world.name))

    }
}

