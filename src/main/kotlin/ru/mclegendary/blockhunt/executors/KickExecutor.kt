package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix


class KickExecutor(val sender: CommandSender,  args: Array<out String>) {

    private val server = sender.server
    private val target = server.getPlayer(args[1])
    private val targetWorld = server.getWorld(args[1])
    private val playerReason = args.drop(2).joinToString(" ")

    fun kick() {
        target ?: return sender.sendMessage("$prefix ${instance.config.getString("PlayerOffline")}"
            .replace('&', '§'))
        if(target.hasPermission("blockhunt.kick.bypass")){
            sender.sendMessage("$prefix ${instance.config.getString("TargetBypass")}"
                .replace('&', '§'))
            return}


        target.performCommand("has leave")
        if (playerReason.isEmpty()) {
            target.sendMessage("$prefix ${instance.config.getString("KickMessage")}"
                .replace('&', '§'))

        } else target.sendMessage("$prefix ${instance.config.getString("KickMessageAndReason")}"
            .replace('&', '§')
            .replace("%REASON%", playerReason))

        sender.sendMessage("$prefix ${instance.config.getString("SenderMessage")}"
            .replace('&', '§')
            .replace("%PLAYER%", target.name))
        server.broadcast("$prefix ${instance.config.getString("KickLog")}".replace('&', '§')
            .replace("%PLAYER%", target.name)
            .replace("%SENDER%", sender.name)
            .replace("%REASON%", playerReason),
            "blockhunt.kick.other")}




    fun kickAll() {
        targetWorld ?: return sender.sendMessage("$prefix ${instance.config.getString("WorldNotFound")}"
            .replace('&', '§'))
        for (players in targetWorld.players) {
            players.performCommand("has leave")
            if (playerReason.isEmpty()) {
                players.sendMessage("$prefix ${instance.config.getString("KickMessage")}"
                    .replace('&', '§'))

            } else players.sendMessage("$prefix ${instance.config.getString("KickMessageAndReason")}"
                .replace('&', '§')
                .replace("%REASON%", playerReason))
        }
        sender.sendMessage("$prefix ${instance.config.getString("KickSuccess")}"
            .replace('&','§')
            .replace("%WORLD%", targetWorld.name)
            .replace("%PLAYER%", sender.name)
            .replace("%SENDER%", sender.name))

        server.broadcast("$prefix ${instance.config.getString("KickLogWorld")}"
                .replace('&','§')
                .replace("%WORLD%", targetWorld.name)
                .replace("%SENDER%", sender.name)
                .replace("%REASON%", playerReason),
                "blockhunt.kick.other")}}


