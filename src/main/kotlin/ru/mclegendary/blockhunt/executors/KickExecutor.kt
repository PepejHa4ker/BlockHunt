package ru.mclegendary.blockhunt.executors

import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.log



class KickExecutor(val sender: CommandSender,  args: Array<out String>) {

    private val server = sender.server
    private val target = server.getPlayer(args[1])
    private val targetWorld = server.getWorld(args[1])
    private val playerReason = args.drop(2).joinToString(" ")

    fun kick() {
        target ?: return sender.sendMessage(instance.config.getString("PlayerOffline")
            .replace('&', '§'))
        if(target.hasPermission("blockhunt.kick.bypass")){
            sender.sendMessage(instance.config.getString("TargetBypass")
                .replace('&', '§'))
            return
        }

        target.performCommand("has leave")
        if (playerReason.isEmpty()) {
            target.sendMessage(instance.config.getString("KickMessage")
                .replace('&', '§'))

        } else target.sendMessage(instance.config.getString("KickMessageAndReason")
            .replace('&', '§')
            .replace("%REASON%", playerReason))

        sender.sendMessage(instance.config.getString("SenderMessage")
            .replace('&', '§')
            .replace("%PLAYER%", target.name))
           log(instance.config.getString("KickLog")
               .replace('&', '§')
               .replace("%PLAYER%", target.name)
               .replace("%SENDER%", sender.name)
               .replace("%REASON%", playerReason))

    }

    fun kickAll() {
        targetWorld ?: return sender.sendMessage(instance.config.getString("WorldNotFound")
            .replace('&', '§'))
        for (players in targetWorld.players) {
            players.performCommand("has leave")
            if (playerReason.isEmpty()) {
                players.sendMessage(instance.config.getString("KickMessage")
                    .replace('&', '§'))

            } else players.sendMessage(instance.config.getString("KickMessageAndReason")
                .replace('&', '§')
                .replace("%REASON%", playerReason))
        }
        sender.sendMessage(instance.config.getString("KickSuccess")
            .replace('&','§')
            .replace("%WORLD%", targetWorld.name)
            .replace("%PLAYER%", sender.name)
            .replace("%SENDER%", sender.name))

            log(instance.config.getString("KickLog")
                .replace('&','§')
                .replace("%WORLD%", targetWorld.name)
                .replace("%SENDER%", sender.name)
                .replace("%REASON%", playerReason))


    }
}