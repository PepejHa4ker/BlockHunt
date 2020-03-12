
package ru.mclegendary.blockhunt.util

import be.maximvdw.featherboard.api.FeatherBoardAPI
import me.wazup.hideandseek.HideAndSeek
import org.bukkit.ChatColor

import org.bukkit.GameMode
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent


import ru.mclegendary.blockhunt.BlockHunt.Companion.log
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix
object Utils {


    //Fix for FeatherBoard (restore scoreboard if player joined lobby)
    fun fbFix(player: Player) {
        val lobby = player.world.name == "blockhunt"

        if (!FeatherBoardAPI.isToggled(player)) return

        if (lobby) {
            FeatherBoardAPI.initScoreboard(player)
        }
    }


    fun ggFix(e: AsyncPlayerChatEvent, sender: Player) {
        val playerData = HideAndSeek.api.getPlayerData(sender)
        if (sender.gameMode == GameMode.SPECTATOR && e.message.equals("gg", true) && sender.world.name != "blockhunt") {
            if (playerData.hasCoins(sender, 50)) {
                playerData.removeCoins(sender, 50)
                sender.sendText(getCfg("GgInSpecWithMoney"))
            } else sender.sendText(getCfg("GgInSpec"))
            e.isCancelled = true
            log(
                "$prefix ${getCfg("GgLog")}"
                    .replace("%PLAYER%", sender.name)
            )


        }
    }

    fun CommandSender.sendText(message: String) {
        this.sendMessage(ChatColor.translateAlternateColorCodes('&', "$prefix $message"))
    }

}