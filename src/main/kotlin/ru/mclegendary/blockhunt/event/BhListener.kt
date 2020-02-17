package ru.mclegendary.blockhunt.event


import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause

import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix

import ru.mclegendary.blockhunt.util.Utils.fbFix
import ru.mclegendary.blockhunt.util.Utils.ggFix

class BhListener(var isChatProcessed: Boolean = true) : Listener  {

    @EventHandler
    fun onChat(e: AsyncPlayerChatEvent) {
        val r = e.recipients
        val sender = e.player
        val server = sender.server
        if (sender.gameMode == GameMode.SPECTATOR && e.message.equals("gg", true) && sender.world.name != "blockhunt")
            ggFix(e, sender)
        if(isChatProcessed){
            for (player in r.iterator()) {
                if (sender.world != player.world) {
                    if (sender.hasPermission("blockhunt.user")) {
                        r.remove(player)
                        if (!sender.hasPermission("blockhunt.chat")) { //Sending message to admins
                            server.broadcast(
                                "§5[${sender.world.name}] ${sender.displayName}§6: ${e.message}",
                                "blockhunt.chat"
                            )
                        } else return
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
            if (player.gameMode != GameMode.SPECTATOR) {

                player.gameMode = GameMode.ADVENTURE
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn ${player.name}")
            }
        } else player.gameMode = GameMode.SURVIVAL
    }

    @EventHandler
    fun onHandSwap(e: PlayerSwapHandItemsEvent) {
        if (e.offHandItem.data.itemType == Material.FIREWORK) {
            e.player.sendMessage("$prefix §cНе в этот раз, дружок")
            e.isCancelled = true
        }
    }
}
