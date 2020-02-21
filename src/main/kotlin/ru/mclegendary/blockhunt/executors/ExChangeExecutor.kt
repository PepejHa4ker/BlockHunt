package ru.mclegendary.blockhunt.executors

import com.iCo6.system.Accounts

import me.wazup.hideandseek.HideAndSeek
import me.wazup.hideandseek.HideAndSeekAPI

import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.BlockHunt.Companion.doCmd
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
            plMsg("§3$coins коинов снято с Вашего аккаунта.", player)

            doCmd("money give ${player.name} ${args[3]}")
        } else {
            plMsg("$prefix ix §cНедостаточно коинов для обмена!", player)
        }

    }


    fun toCoins(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        val money = args[2].toDouble()
        val bankAccount = Accounts().get(player.name).holdings.balance

        if (bankAccount >= money) {
            doCmd("money take ${player.name} $money")
            plMsg("§a$money монет снятно с Вашего аккаунта.", player)
            doCmd("has Coins Add ${player.name} ${args[3]}")
            plMsg("§aВы получили ${args[3]} коинов.", player)
            sendMsg("§3Выдано ${args[3]} коинов игроку: §2${player.name}", sender)
        } else plMsg("§cНедостаточно монет для обмена!", player)
    }
}