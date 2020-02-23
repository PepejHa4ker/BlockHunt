package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix
import ru.mclegendary.blockhunt.executors.ChatSwitchExecutor

import ru.mclegendary.blockhunt.executors.KickExecutor as KE

class Bh : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>




    ): Boolean {
        when {
            args.isEmpty() -> {
                sender.sendMessage("§cЧто-то не так с аргументами:"); return false
            }

            args[0].equals("kick", true) -> {
                if (args.size < 2)  return false
                    KE(sender, args).kick()
            }

            args[0].equals("kickall", true) -> {
                if (args.size < 2)  return false
                    KE(sender, args).kickAll()
            }

            args[0].equals("reload", true) -> {
                instance.reloadConfig()
                sender.sendMessage("$prefix ${instance.config.getString("CfgReloaded").replace('&', '§')}")

            }

            args[0].equals("chat", true) -> {
                if (args.size < 2) return false

                when {

                    args[1].equals("on", true) -> ChatSwitchExecutor(sender).chatEnable()

                    args[1].equals("off", true) -> ChatSwitchExecutor(sender).chatDisable()

                    args[1].equals("info", true) ->
                        if ((ChatSwitchExecutor(sender).isChatEnabled())) {
                            sender.sendMessage("$prefix ${instance.config.getString("ChatOn").replace('&', '§')}")
                        } else sender.sendMessage("$prefix ${instance.config.getString("ChatOff").replace('&', '§')}")

                    else -> return false

                }
            }

            else -> return false

        } 
        return true
    }


}