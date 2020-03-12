package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.util.getCfg
import ru.mclegendary.blockhunt.util.Utils.sendText


object WhereExecutor {

    fun whereAre(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[0])

        sender.sendText(
            getCfg("PlayerInWorld")
                .replace("%PLAYER%", player.name)
                .replace("%WORLD%", player.world.name))
    }
}



