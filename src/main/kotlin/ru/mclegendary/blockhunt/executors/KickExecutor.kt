package ru.mclegendary.blockhunt.executors

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.log
import ru.mclegendary.blockhunt.prefix


class KickExecutor(val sender: CommandSender, val args: Array<out String>) {
    private val server = sender.server

    private val target = server.getPlayer(args[1])
    private val targetWorld = server.getWorld(args[1])
    private val playerReason = args.drop(2).joinToString(" ")


    fun kick() {
        target ?: return sender.sendMessage("§cИгрок не найден или оффлайн.")
        if(target.hasPermission("blockhunt.kick.bypass")){
            sender.sendMessage("$prefix §cНельзя кикнуть этого игрока")
            return
        }

        target.performCommand("has leave")
        if (playerReason.isEmpty()) {
            target.sendMessage("§cВас выкинули из игры!")

        } else target.sendMessage("§cВас выкинули из игры! \n§cПричина: §6$playerReason")

        sender.sendMessage("§2Вы успешно кикнули игрока §a${target.name} §2из игры.")
        Bukkit.getConsoleSender()
            .sendMessage("$log §a${target.name} §2был исключен игроком: §a${sender.name} §2по причине: §a${playerReason}")
    }

    fun kickAll() {
        targetWorld ?: return sender.sendMessage("§cМир не найден.")
        for (players in targetWorld.players) {
            players.performCommand("has leave")
            if (playerReason.isEmpty()) {
                players.sendMessage("§cВас выкинули из игры!")

            } else players.sendMessage("§cВас выкинули из игры! \n§cПричина: §6$playerReason")
        }
        sender.sendMessage("§2Все игроки успешно исключены из арены в мире: §a${targetWorld.name}§2.")

        Bukkit.getConsoleSender().
            sendMessage("$log §2Все игроки из арены в мире: §a${targetWorld.name} §2успешно исключены игроком: §a${sender.name}")
    }
}