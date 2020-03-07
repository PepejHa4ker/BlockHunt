package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

import ru.mclegendary.blockhunt.executors.WhereExecutor.whereAre
import ru.mclegendary.blockhunt.util.playerOffline
import ru.mclegendary.blockhunt.util.sendText

class Where : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {

        if (args.isEmpty()) return false
        val player = sender.server.getPlayer(args[0])
        if(player == null){
            sender.sendText("$playerOffline")
            return true
        }
        if(args[0].equals(player.name, true)) whereAre(sender,args) else return false


        return true
    }
}

