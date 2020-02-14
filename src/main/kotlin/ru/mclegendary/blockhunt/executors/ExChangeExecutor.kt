package ru.mclegendary.blockhunt.executors

import com.iCo6.system.Accounts

import me.wazup.hideandseek.HideAndSeek
import me.wazup.hideandseek.HideAndSeekAPI
import org.bukkit.Bukkit

import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

object ExChangeExecutor {

    fun toMoney(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        val coins = args[2].toInt()

        val has = HideAndSeekAPI(JavaPlugin.getPlugin(HideAndSeek::class.java))
        val hasPlayerData = has.getPlayerData(player)

        if (hasPlayerData.getCoins(player) >= coins) {
            Bukkit.dispatchCommand(sender, "has Coins Remove ${player.name} $coins")
            player.sendMessage("§3$coins коинов снято с Вашего аккаунта.")

            Bukkit.dispatchCommand(sender, "money give ${player.name} ${args[3]}")
        } else {
            player.sendMessage("§cНедостаточно коинов для обмена!")
        }

    }


    fun toCoins(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        val money = args[2].toDouble()
        val bankAccount = Accounts().get(player.name).holdings.balance

        if (bankAccount >= money) {
            Bukkit.dispatchCommand(sender, "money take ${player.name} $money")
            player.sendMessage("§a$money монет снятно с Вашего аккаунта.")

            Bukkit.dispatchCommand(sender, "has Coins Add ${player.name} ${args[3]}")
            player.sendMessage("§aВы получили ${args[3]} коинов.")
            sender.sendMessage("§3Выдано ${args[3]} коинов игроку: §2${player.name}")
        } else player.sendMessage("§cНедостаточно монет для обмена!")
    }
}