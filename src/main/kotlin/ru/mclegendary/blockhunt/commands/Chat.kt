package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.listener
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix

class Chat : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if(args.isEmpty()) return false
        when {
            args[0].equals("on", true) -> {
                if (!listener.isChatProcessed) {
                   chatEnable()
                    sender.sendMessage("$prefix ${instance.config.getString("ChatOnSuccess").replace('&', '§')}")
                } else sender.sendMessage("$prefix ${instance.config.getString("ChatAlreadyEnabled").replace('&', '§')}"); return true

            }
            args[0].equals("off", true) -> {
                if (listener.isChatProcessed) {
                    chatDisable()
                    sender.sendMessage("$prefix ${instance.config.getString("ChatOffSuccess").replace('&', '§')}")
                } else sender.sendMessage("$prefix ${instance.config.getString("ChatAlreadyEnabled").replace('&', '§')}"); return true
            }

            args[0].equals("toggle", true) -> {
                if (listener.isChatProcessed) {
                    chatDisable()
                    sender.sendMessage("$prefix ${instance.config.getString("ChatOffSuccess").replace('&', '§')}")
                } else {
                    chatEnable()
                    sender.sendMessage("$prefix ${instance.config.getString("ChatOnSuccess").replace('&', '§')}")
                }

            }

            args[0].equals("info", true) -> {
                if(listener.isChatProcessed){
                    sender.sendMessage("$prefix ${instance.config.getString("ChatOn").replace('&', '§')}")
                } else sender.sendMessage("$prefix ${instance.config.getString("ChatOff").replace('&', '§')}")

            }



            else -> return false

        }

        return true
    }

    private fun chatEnable(){
        listener.isChatProcessed = true

    }

    private fun chatDisable(){
       listener.isChatProcessed = false

    }
}