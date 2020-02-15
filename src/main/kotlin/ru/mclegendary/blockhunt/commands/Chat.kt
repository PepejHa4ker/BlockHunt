package ru.mclegendary.blockhunt.commands

import com.sun.deploy.security.CertType.PLUGIN
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.Plugin
import ru.mclegendary.blockhunt.BlockHunt.Companion.listener
import ru.mclegendary.blockhunt.event.BhListener

class Chat : CommandExecutor{
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        when{
            args[0].equals("on", true) -> {
                listener.isChatProcessed = true
            }
            args[0].equals("off", true) -> {
                listener.isChatProcessed = false
            }





        }
        return true
    }

}