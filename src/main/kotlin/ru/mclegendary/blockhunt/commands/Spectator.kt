package ru.mclegendary.blockhunt.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

import ru.mclegendary.blockhunt.executors.SpectatorExecutor.specOff
import ru.mclegendary.blockhunt.executors.SpectatorExecutor.specOn

class Spectator : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String?,
        args: Array<out String>
    ): Boolean {
        if (args.isEmpty()) return false
        if (sender is Player) {
            for (world in sender.server.worlds) {
                when {

                    args[0].equals("off", true) -> {
                        specOff(sender)
                    }

                    args[0].equals(world.name, true) -> {
                        if (sender.world.name != world.name) {
                            specOn(sender, args)
                        } else {
                            sender.sendMessage("§cТы уже тут!")
                        }
                    }

                }
            }
        } else {
            return false
        }

        return true
    }
}