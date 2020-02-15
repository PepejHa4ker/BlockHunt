package ru.mclegendary.blockhunt.event


import me.wazup.hideandseek.HideAndSeek
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material


import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

import org.bukkit.event.player.*
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause
import ru.mclegendary.blockhunt.log
import ru.mclegendary.blockhunt.prefix
import ru.mclegendary.blockhunt.util.Utils.fbFix

class BhListener(var isChatProcessed: Boolean = true) : Listener  {

    @EventHandler
    fun onChat(e: AsyncPlayerChatEvent) {
        if(!isChatProcessed) return
        val r = e.recipients
        val sender = e.player
        val server = sender.server
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

    @EventHandler
    fun ggFix(e: AsyncPlayerChatEvent) {
        val p = e.player
        val playerData = HideAndSeek.api.getPlayerData(p)
        if (p.gameMode == GameMode.SPECTATOR && e.message.equals("gg", true) && p.world.name != "blockhunt") {
            if (playerData.hasCoins(p, 50)) {
                playerData.removeCoins(p, 50)
                p.sendMessage("$prefix Нельзя использовать в режиме спектатора! За это было снято 50 коинов!")
            } else p.sendMessage("$prefix §cНельзя использовать в режиме спектатора!")
            e.isCancelled = true
            Bukkit.getConsoleSender().sendMessage("$log §aИгрок §c${p.name} §aиспользовал 'gg' в режиме спектатора ")

        }
    }

}
