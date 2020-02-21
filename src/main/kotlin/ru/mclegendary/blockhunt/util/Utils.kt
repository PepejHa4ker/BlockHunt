
package ru.mclegendary.blockhunt.util

import be.maximvdw.featherboard.api.FeatherBoardAPI
import me.wazup.hideandseek.HideAndSeek

import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent


import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.log
import ru.mclegendary.blockhunt.BlockHunt.Companion.sendMsg

object Utils  {

    //Fix for FeatherBoard (restore scoreboard if player joined lobby)
    fun fbFix(player: Player) {
        val lobby = player.world.name == "blockhunt"

        if (!FeatherBoardAPI.isToggled(player)) return

        if (lobby) {
            FeatherBoardAPI.initScoreboard(player)
        }
    }

    //Sleeping command >_>
    fun delayedModeChange(player: Player, delay: Int, gamemode: String) {
        instance.server.scheduler.scheduleSyncDelayedTask(instance, {
            player.gameMode = GameMode.valueOf(gamemode)
        }, delay.toLong() * 20) // time in ticks
    }

    fun ggFix( e: AsyncPlayerChatEvent,  sender: Player) {
        val playerData = HideAndSeek.api.getPlayerData(sender)
        if (sender.gameMode == GameMode.SPECTATOR && e.message.equals("gg", true) && sender.world.name != "blockhunt") {
            if (playerData.hasCoins(sender, 50)) {
                playerData.removeCoins(sender, 50)
               sendMsg("§cНельзя использовать в режиме спектатора! За это было снято 50 коинов!", sender)
            } else sendMsg("§cНельзя использовать в режиме спектатора!", sender)
            e.isCancelled = true
          log("§aИгрок §c${sender.name} §aиспользовал 'gg' в режиме спектатора ")

        }
    }
}
