package ru.mclegendary.blockhunt.executors

import com.iCo6.system.Accounts

import me.wazup.hideandseek.HideAndSeek

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.doCmd
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.util.Messages
import ru.mclegendary.blockhunt.util.Utils.sendText
import ru.mclegendary.blockhunt.util.getCfg

object ExChangeExecutor {

    fun toMoney(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        val coins: Int
        val coins2: Double
        val has = HideAndSeek.api
        val hasPlayerData = has.getPlayerData(player)
        try {
            coins = args[2].toInt()
        } catch (ex: NumberFormatException) {
            sender.sendText("&cNaN! ${args[2]} должно быть целочисленным значением! ")
            return
        }

        if (hasPlayerData.getCoins(player) < coins) {
            sender.sendText(Messages.NO_COINS)
            return
        }
        try {
            coins2 = args[3].toDouble()
        } catch (ex: NumberFormatException) {
            sender.sendText("&cОшибка! ${args[3]} должно быть дробным значением! ")
            return
        }
        doCmd("has Coins Remove ${player.name} $coins")
        player.sendText(getCfg("CoinsRemove").replace("%COINS%", "$coins"))

        doCmd("money give ${player.name} $coins2")
    }


    fun toCoins(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        val money: Double
        val money2: Int
        val bankAccount = Accounts().get(player.name).holdings.balance

        try {
            money = args[2].toDouble()
        } catch (ex: NumberFormatException) {
            sender.sendText("&cОшибка! ${args[2]} должно быть дробным значением!")
            return
        }


        if (bankAccount < money) {
            sender.sendText(Messages.NO_COINS)
            return
        }
        try {
            money2 = args[3].toInt()
        } catch (ex: NumberFormatException) {
            sender.sendText("&cNaN! ${args[3]} должно быть целочисленным значением!")
            return
        }
        doCmd("money take ${player.name} $money")
        player.sendText(getCfg("MoneyRemove").replace("%MONEY%", "$money"))

        doCmd("has Coins Add ${player.name} $money2")
        player.sendText(getCfg("CashGive").replace("%CASH%", args[3]))

        sender.sendText(
            getCfg("SenderCoinsMsg")
                .replace("%COINS%", args[3])
                .replace("%PLAYER%", player.name))

    }
}


