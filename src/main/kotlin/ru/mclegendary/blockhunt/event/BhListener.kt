package ru.mclegendary.blockhunt.event


import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.*
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause

import ru.mclegendary.blockhunt.prefix

import ru.mclegendary.blockhunt.util.Utils.fbFix

object BhListener : Listener {

    @EventHandler
    fun onChat(e: AsyncPlayerChatEvent) {
        val server = e.player.server
        val re = e.recipients
        val sender = e.player

        for (player in re.iterator()) {
            if (sender.world != player.world) {

                if (sender.hasPermission("blockhunt.user")) {
                    re.remove(player)
                    if (!sender.hasPermission("blockhunt.adm")) { //Sending message to admins
                        server.broadcast(
                            "§5[${sender.world.name}] ${sender.displayName}§6: ${e.message}",
                            "blockhunt.adm"
                        )
                    } else {
                        return
                    }
                }

            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onTeleport(e: PlayerTeleportEvent) {
        val player = e.player

        if (!player.hasPermission("blockhunt.adm") && e.cause == TeleportCause.SPECTATE) {
            player.sendMessage("$prefix §cНизя!")
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onWorldChange(e: PlayerChangedWorldEvent) {
        val player = e.player

        fbFix(player) //In Utils

        if (player.world.name == "blockhunt") {
            val server = player.server
            if (player.gameMode != GameMode.SPECTATOR) {

                player.gameMode = GameMode.ADVENTURE
                server.dispatchCommand(server.consoleSender, "spawn ${player.name}")
            }
        } else player.gameMode = GameMode.SURVIVAL

    }

    @EventHandler
    fun secondHandFix(e: PlayerSwapHandItemsEvent){
        if(e.player.world.name == "blockhunt" || e.player.isOp) return
        e.player.sendMessage("$prefix §cНе в этот раз, дружок")
        e.isCancelled = true
    }

}