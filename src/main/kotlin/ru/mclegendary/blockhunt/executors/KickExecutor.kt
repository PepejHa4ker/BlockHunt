package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix
import ru.mclegendary.blockhunt.util.Messages
import ru.mclegendary.blockhunt.util.Utils.sendText
import ru.mclegendary.blockhunt.util.getCfg


class KickExecutor(val sender: CommandSender, args: Array<out String>) {

    private val server = sender.server
    private val target = server.getPlayer(args[1])
    private val targetWorld = server.getWorld(args[1])
    private val playerReason = args.drop(2).joinToString(" ")

    fun kick() {
        target ?: return sender.sendText(Messages.PLAYER_OFFLINE)
        if (target.hasPermission("blockhunt.kick.bypass") && sender !is ConsoleCommandSender) {
            sender.sendText(getCfg("TargetBypass"))
            return
        }


        target.performCommand("has leave")
        if (playerReason.isEmpty()) {
            target.sendText(getCfg("KickMessage"))

        } else target.sendText(getCfg("KickMessageAndReason").replace("%REASON%", playerReason))

        sender.sendText(getCfg("SenderMessage").replace("%PLAYER%", target.name))

        server.broadcast(
            "$prefix ${instance.config.getString("KickLog")}".replace('&', '§')
                .replace("%PLAYER%", target.name)
                .replace("%SENDER%", sender.name)
                .replace("%REASON%", playerReason),
            "blockhunt.kick.other")
    }

    fun kickAll() {
        targetWorld ?: return sender.sendText(getCfg("WorldNotFound"))
        for (players in targetWorld.players) {
            players.performCommand("has leave")
            if (playerReason.isEmpty()) {
                players.sendText(getCfg("KickMessage"))

            } else players.sendText(getCfg("KickMessageAndReason").replace("%REASON%", playerReason))
        }
        sender.sendText(
            getCfg("KickSuccess")
                .replace("%WORLD%", targetWorld.name)
                .replace("%PLAYER%", sender.name)
                .replace("%SENDER%", sender.name))

        server.broadcast(
            "$prefix ${getCfg("KickLogWorld")}"
                .replace('&', '§')
                .replace("%WORLD%", targetWorld.name)
                .replace("%SENDER%", sender.name)
                .replace("%REASON%", playerReason),
            "blockhunt.kick.other")
    }
}


