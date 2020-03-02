package ru.mclegendary.blockhunt.executors

import com.iCo6.system.Accounts

import me.wazup.hideandseek.HideAndSeek
import me.wazup.hideandseek.HideAndSeekAPI

import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.BlockHunt.Companion.doCmd
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix

object ExChangeExecutor {

    fun toMoney(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        val coins:Int
        val coins2:Double
        val has = HideAndSeekAPI(JavaPlugin.getPlugin(HideAndSeek::class.java))
        val hasPlayerData = has.getPlayerData(player)
        try {
            coins = args[2].toInt()
        } catch (ex:NumberFormatException){
            sender.sendMessage("§cNaN! ${args[2]} должно быть целочисленным значением! ");return}

        if (hasPlayerData.getCoins(player) >= coins) {
            try {
                coins2 = args[3].toDouble()
            } catch (ex:NumberFormatException) {
                sender.sendMessage("§cОшибка! ${args[3]} должно быть дробным значением! ")
                return
            }
            doCmd("has Coins Remove ${player.name} $coins")
            player.sendMessage("$prefix ${instance.config.getString("CoinsRemove")}"
                .replace('&', '§')
                .replace("%COINS%", "$coins"))

            doCmd("money give ${player.name} $coins2")
        } else sender.sendMessage("$prefix ${instance.config.getString("NoCoins").replace('&','§')}")}




    fun toCoins(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        val money: Double
        val money2: Int
        val bankAccount = Accounts().get(player.name).holdings.balance

        try {
            money = args[2].toDouble()
        } catch (ex: NumberFormatException){
            sender.sendMessage("§cОшибка! ${args[2]} должно быть дробным значением!");return}


        if (bankAccount >= money) {
            try {
                money2 = args[3].toInt()
            } catch (ex: NumberFormatException){
                sender.sendMessage("§cNaN! ${args[3]} должно быть целочисленным значением!");return}
            doCmd("money take ${player.name} $money")
            player.sendMessage("$prefix ${instance.config.getString("MoneyRemove")}"
                .replace('&', '§')
                .replace("%MONEY%", "$money"))

            doCmd("has Coins Add ${player.name} $money2")
            player.sendMessage("$prefix ${instance.config.getString("CashGive")}"
                .replace('&','§')
                .replace("%CASH%", args[3]))
            sender.sendMessage("$prefix ${instance.config.getString("SenderCoinsMsg")}"
                .replace('&','§')
                .replace("%COINS%", args[3])
                .replace("%PLAYER%", player.name))

        } else sender.sendMessage("$prefix ${instance.config.getString("NoCoins").replace('&','§')}")}}


