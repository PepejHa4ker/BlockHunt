package ru.mclegendary.blockhunt.executors

import me.wazup.hideandseek.HideAndSeek
import me.wazup.hideandseek.HideAndSeekAPI

import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.BlockHunt.Companion.doCmd
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.util.Messages
import ru.mclegendary.blockhunt.util.Utils.sendText
import java.lang.NumberFormatException


object CSGOCrateExecutor {

    fun csgoGive(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        if (!player.isOnline) return sender.sendText("&сИгрок $player&c не найен или оффлайн")

        val coins: Int
        try {
            coins = args[4].toInt()
        } catch (ex: NumberFormatException) {
            sender.sendText("&cNaN! ${args[4]} должно быть целочисленным значением!")
            return
        }


        val has = HideAndSeekAPI(JavaPlugin.getPlugin(HideAndSeek::class.java))
        val hasPlayerData = has.getPlayerData(player)

        if (hasPlayerData.getCoins(player) >= coins) {
            doCmd("has coins remove ${player.name} $coins")

            player.sendText(instance.config.getString("CoinsRemove")
                    .replace("%COINS%", "$coins"))

            doCmd("crate give to ${player.name} ${args[2]} ${args[3].toInt()} online")

        } else player.sendText("{$Messages.noCoins}")
    }
}




