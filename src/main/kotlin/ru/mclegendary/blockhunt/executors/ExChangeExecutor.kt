package ru.mclegendary.blockhunt.executors

import com.iCo6.system.Accounts

import me.wazup.hideandseek.HideAndSeek
import me.wazup.hideandseek.HideAndSeekAPI

import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.BlockHunt.Companion.doCmd
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.plMsg
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix
import ru.mclegendary.blockhunt.BlockHunt.Companion.sendMsg

object ExChangeExecutor {

    fun toMoney(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        val coins = args[2].toInt()

        val has = HideAndSeekAPI(JavaPlugin.getPlugin(HideAndSeek::class.java))
        val hasPlayerData = has.getPlayerData(player)

        if (hasPlayerData.getCoins(player) >= coins) {
            doCmd("has Coins Remove ${player.name} $coins")
            player.sendMessage(instance.config.getString("CoinsRemove")
                .replace('&', '§')
                .replace("%COINS%", "$coins"))


            doCmd("money give ${player.name} ${args[3]}")
        } else {
            player.sendMessage(instance.config.getString("NoCoins").replace('&','§'))
        }

    }


    fun toCoins(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        val money = args[2].toDouble()
        val bankAccount = Accounts().get(player.name).holdings.balance

        if (bankAccount >= money) {
            doCmd("money take ${player.name} $money")
            player.sendMessage(instance.config.getString("MoneyRemove")
                .replace('&', '§')
                .replace("%MONEY%", "$money"))

            doCmd("has Coins Add ${player.name} ${args[3]}")
            player.sendMessage(instance.config.getString("CoinsGot")
                .replace('&','§')
                .replace("%COINS%", args[3]))
            sender.sendMessage(instance.config.getString("SenderCoinsMsg")
                .replace('&','§')
                .replace("%COINS%", args[3])
                .replace("%PLAYER%", "$player"))


        } else player.sendMessage(instance.config.getString("NoCoins").replace('&','§'))
    }
}