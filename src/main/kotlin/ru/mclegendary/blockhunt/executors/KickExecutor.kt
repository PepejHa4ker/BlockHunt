package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix
import ru.mclegendary.blockhunt.util.playerOffline
import ru.mclegendary.blockhunt.util.sendText


class KickExecutor(val sender: CommandSender, args: Array<out String>) {

    private val server = sender.server
    private val target = server.getPlayer(args[1])
    private val targetWorld = server.getWorld(args[1])
    private val playerReason = args.drop(2).joinToString(" ")

    fun kick() {
        target ?: return sender.sendText("$playerOffline")
        if (target.hasPermission("blockhunt.kick.bypass") && sender !is ConsoleCommandSender) {
            sender.sendText(instance.config.getString("TargetBypass"))
            return
        }


        target.performCommand("has leave")
        if (playerReason.isEmpty()) {
            target.sendText(instance.config.getString("KickMessage"))

        } else target.sendText(instance.config.getString("KickMessageAndReason").replace("%REASON%", playerReason))

        sender.sendText(instance.config.getString("SenderMessage").replace("%PLAYER%", target.name))

        server.broadcast(
            "$prefix ${instance.config.getString("KickLog")}".replace('&', '§')
                .replace("%PLAYER%", target.name)
                .replace("%SENDER%", sender.name)
                .replace("%REASON%", playerReason),
            "blockhunt.kick.other")
    }

    fun kickAll() {
        targetWorld ?: return sender.sendText(instance.config.getString("WorldNotFound"))
        for (players in targetWorld.players) {
            players.performCommand("has leave")
            if (playerReason.isEmpty()) {
                players.sendText(instance.config.getString("KickMessage"))

            } else players.sendText(instance.config.getString("KickMessageAndReason").replace("%REASON%", playerReason))
        }
        sender.sendText(
            instance.config.getString("KickSuccess")
                .replace("%WORLD%", targetWorld.name)
                .replace("%PLAYER%", sender.name)
                .replace("%SENDER%", sender.name))

        server.broadcast(
            "$prefix ${instance.config.getString("KickLogWorld")}"
                .replace('&', '§')
                .replace("%WORLD%", targetWorld.name)
                .replace("%SENDER%", sender.name)
                .replace("%REASON%", playerReason),
            "blockhunt.kick.other")
    }
}


