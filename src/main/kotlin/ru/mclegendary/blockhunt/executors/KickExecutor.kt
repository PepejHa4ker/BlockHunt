package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix
import ru.mclegendary.blockhunt.BlockHunt.Companion.log
import ru.mclegendary.blockhunt.BlockHunt.Companion.sendMsg


class KickExecutor(val sender: CommandSender,  args: Array<out String>) {

    private val server = sender.server
    private val target = server.getPlayer(args[1])
    private val targetWorld = server.getWorld(args[1])
    private val playerReason = args.drop(2).joinToString(" ")

    fun kick() {
        target ?: return sendMsg("§cИгрок не найден или оффлайн.", sender)
        if(target.hasPermission("blockhunt.kick.bypass")){
            sendMsg("§cНельзя кикнуть этого игрока", sender)
            return
        }

        target.performCommand("has leave")
        if (playerReason.isEmpty()) {
            target.sendMessage("$prefix §cВас выкинули из игры!")

        } else target.sendMessage("$prefix §cВас выкинули из игры! \n§cПричина: §6$playerReason")

        sendMsg("§2Вы успешно кикнули игрока §a${target.name} §2из игры.", sender)
           log("§a${target.name} §2был исключен игроком: §a${sender.name} §2по причине: §a${playerReason}")

    }

    fun kickAll() {
        targetWorld ?: return sendMsg("§cМир не найден.", sender)
        for (players in targetWorld.players) {
            players.performCommand("has leave")
            if (playerReason.isEmpty()) {
                players.sendMessage("$prefix §cВас выкинули из игры!")

            } else players.sendMessage("$prefix §cВас выкинули из игры! \n§cПричина: §6$playerReason")
        }
        sendMsg("§2Все игроки успешно исключены из арены в мире: §a${targetWorld.name}§2.", sender)

            log("§2Все игроки из арены в мире: §a${targetWorld.name} §2успешно исключены игроком: §a${sender.name}")
    }
}