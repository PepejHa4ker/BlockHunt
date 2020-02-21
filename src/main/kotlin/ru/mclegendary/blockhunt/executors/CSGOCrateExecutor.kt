package ru.mclegendary.blockhunt.executors

import me.wazup.hideandseek.HideAndSeek
import me.wazup.hideandseek.HideAndSeekAPI

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.BlockHunt.Companion.doCmd
import ru.mclegendary.blockhunt.BlockHunt.Companion.plMsg
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix


object CSGOCrateExecutor {

    fun csgoGive(sender: CommandSender, args: Array<out String>) {
        val player = sender.server.getPlayer(args[1])
        val coins = args[4].toInt()


        val has = HideAndSeekAPI(JavaPlugin.getPlugin(HideAndSeek::class.java))
        val hasPlayerData = has.getPlayerData(player)

        if (!player.isOnline) return

        if (hasPlayerData.getCoins(player) >= coins) {
            doCmd("has coins remove ${player.name} $coins")

            plMsg("§3$coins коинов снято с Вашего аккаунта.", player)

            doCmd("crate give to ${player.name} ${args[2]} ${args[3].toInt()} online")

        } else plMsg("§cУ Вас недостаточно коинов для выполнения покупки!", player)


    }
}
