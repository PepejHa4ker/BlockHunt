
package ru.mclegendary.blockhunt.util

import be.maximvdw.featherboard.api.FeatherBoardAPI
import me.wazup.hideandseek.HideAndSeek

import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent


import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.log
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix


object Utils  {

    //Fix for FeatherBoard (restore scoreboard if player joined lobby)
    fun fbFix(player: Player) {
        val lobby = player.world.name == "blockhunt"

        if (!FeatherBoardAPI.isToggled(player)) return

        if (lobby) {
            FeatherBoardAPI.initScoreboard(player)
        }
    }


    fun ggFix( e: AsyncPlayerChatEvent,  sender: Player) {
        val playerData = HideAndSeek.api.getPlayerData(sender)
        if (sender.gameMode == GameMode.SPECTATOR && e.message.equals("gg", true) && sender.world.name != "blockhunt") {
            if (playerData.hasCoins(sender, 50)) {
                playerData.removeCoins(sender, 50)
               sender.sendMessage("$prefix ${instance.config.getString("GgInSpecWithMoney").replace('&', '§')}")
            } else sender.sendMessage("$prefix ${instance.config.getString("GgInSpec").replace('&', '§')}")
            e.isCancelled = true
          log("$prefix ${instance.config.getString("GgLog")}"
              .replace('&', '§')
              .replace("%PLAYER%", sender.name))

        }
    }
}
