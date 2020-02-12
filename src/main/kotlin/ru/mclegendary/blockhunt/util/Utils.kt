
package ru.mclegendary.blockhunt.util

import be.maximvdw.featherboard.api.FeatherBoardAPI

import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerSwapHandItemsEvent

import ru.mclegendary.blockhunt.BlockHunt.Companion.instance

object Utils {

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

    @EventHandler
    fun secondHandFix(e: PlayerSwapHandItemsEvent){
        if(e.player.world.name == "blockhunt" || e.player.isOp) return
        e.player.sendMessage("§cНе в этот раз, дружок")
        e.isCancelled = true


    }
}