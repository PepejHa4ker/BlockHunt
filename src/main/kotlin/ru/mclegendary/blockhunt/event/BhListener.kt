package ru.mclegendary.blockhunt.event


import org.bukkit.GameMode
import org.bukkit.Material

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import ru.mclegendary.blockhunt.BlockHunt.Companion.doCmd
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance

import org.bukkit.event.player.AsyncPlayerChatEvent as ChatE
import org.bukkit.event.player.PlayerChangedWorldEvent as ChWoE
import org.bukkit.event.player.PlayerSwapHandItemsEvent as ISwapE
import org.bukkit.event.player.PlayerTeleportEvent as TPE
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause as cause

import ru.mclegendary.blockhunt.util.Utils.isChatEnabled
import ru.mclegendary.blockhunt.util.Utils.sendText
import ru.mclegendary.blockhunt.util.Utils.ggFix as gg
import ru.mclegendary.blockhunt.util.Utils.fbFix as fb

class BhListener : Listener {


    @EventHandler
    fun onChat(e: ChatE) {

        val r = e.recipients
        val sender = e.player
        val server = sender.server
        if (sender.gameMode == GameMode.SPECTATOR && (e.message.equals("gg", true) || e.message.equals("good game", true))  && sender.world.name != "blockhunt")
            gg(e, sender)
        if (isChatEnabled()) {
            for (player in r.iterator()) {
                if (sender.world != player.world) {
                    if (sender.hasPermission("blockhunt.user")) {
                        r.remove(player)
                        if (!sender.hasPermission("blockhunt.chat")) { //Sending message to admins
                            server.broadcast(
                                instance.config.getString("ChatPerWorldFormat")
                                    .replace('&', '§')
                                    .replace("%WORLD%", sender.world.name)
                                    .replace("%PLAYER_NAME%", sender.displayName)
                                    .replace("%MESSAGE%", e.message),
                                "blockhunt.chat"
                            )
                        } else return
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onTeleport(e: TPE) {
        val player = e.player

        if (!player.hasPermission("blockhunt.adm") && e.cause == cause.SPECTATE) {
            player.sendText("&cНизя!")
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onWorldChange(e: ChWoE) {
        val player = e.player

        fb(player) //In Utils

        if (player.world.name == "blockhunt") {
            if (player.gameMode != GameMode.SPECTATOR) {

                player.gameMode = GameMode.ADVENTURE
                doCmd("spawn ${player.name}")
            }
        } else player.gameMode = GameMode.SURVIVAL
    }

    @EventHandler
    fun onHandSwap(e: ISwapE) {
        if (e.offHandItem.data.itemType == Material.FIREWORK) {
            e.player.sendText(instance.config.getString("ItemChange"))
            e.isCancelled = true
        }
    }

}