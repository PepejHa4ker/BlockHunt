package ru.mclegendary.blockhunt.executors

import org.bukkit.GameMode
import org.bukkit.entity.Player

import ru.mclegendary.blockhunt.util.Utils.delayedModeChange

object SpectatorExecutor {

    fun specOn(player: Player, args: Array<out String>) {
        val server = player.server

        if (player.world.name != "blockhunt") return player.sendMessage("§cЭто возможно сделать только в лобби!")

        delayedModeChange(player, 2, "SPECTATOR")
        server.dispatchCommand(server.consoleSender, "mw move ${player.name} ${args[0]}")
    }

    fun specOff(player: Player) {
        val server = player.server

        if (player.gameMode == GameMode.SPECTATOR) {
            player.gameMode = GameMode.ADVENTURE
            server.dispatchCommand(server.consoleSender, "spawn ${player.name}")
        }
    }
}