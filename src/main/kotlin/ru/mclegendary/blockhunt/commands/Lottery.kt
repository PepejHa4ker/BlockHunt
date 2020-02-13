package ru.mclegendary.blockhunt.commands

import me.wazup.hideandseek.HideAndSeek
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

import kotlin.random.Random

@Suppress("DEPRECATION")
class Lottery : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.isEmpty()) return false
        val cash = Random.nextInt(args[1].toInt(), args[2].toInt()) //Getting random number to give from command
        val player = Bukkit.getPlayer(args[0])

        if (sender !is ConsoleCommandSender) return false
        cashGive(player, cash)
        player.sendMessage("§aВы получили $cash коинов.")
        sender.sendMessage("§aВыдано $cash коинов игроку: §2${args[0]}")
        return true
    }
    
    private fun cashGive(player: Player, cash: Int) {
        HideAndSeek.api.getPlayerData(player).addCoins(player, cash)
    }
}


