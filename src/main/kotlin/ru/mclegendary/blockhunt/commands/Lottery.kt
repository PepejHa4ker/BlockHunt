package ru.mclegendary.blockhunt.commands

import me.wazup.hideandseek.HideAndSeek
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.util.onlyCons
import ru.mclegendary.blockhunt.util.sendText

import kotlin.random.Random

@Suppress("DEPRECATION")
class Lottery : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is ConsoleCommandSender) {
            sender.sendText(onlyCons)
            return true
        }
        if (args.isEmpty()) return false
        val cash = Random.nextInt(args[1].toInt(), args[2].toInt()) //Getting random number to give from command
        val player = Bukkit.getPlayer(args[0])
        if (!player.isOnline) {
            sender.sendText("&сИгрок $player&c не найден или оффлайн")
            return true
        }



        cashGive(player, cash)
        player.sendText(instance.config.getString("CashGive").replace("%CASH%", "$cash"))


        sender.sendText(
            instance.config.getString("CashGiven")
                .replace("%CASH%", "$cash")
                .replace("%PLAYER%", args[0]))

        return true
    }

    private fun cashGive(player: Player, cash: Int) {
        HideAndSeek.api.getPlayerData(player).addCoins(player, cash)
    }
}




