package ru.mclegendary.blockhunt.commands


import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.executors.ChatSwitchExecutor
import ru.mclegendary.blockhunt.util.*
import ru.mclegendary.blockhunt.util.sendText



import ru.mclegendary.blockhunt.executors.KickExecutor as KE

class Bh : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: org.bukkit.command.Command,
        label: String,
        args: Array<out String>
    ): Boolean {

        when {

            args.isEmpty() -> {
                sender.sendText("&cЧто-то не так с аргументами:"); return false
            }

            args[0].equals("kick", true) -> {
                if (args.size < 2) {
                    sender.sendText("&e/bh kick <Игрок> [Причина] &c-&b выгнать игрока с арены. Причина может быть пустой")
                    return true
                }
                KE(sender, args).kick()
            }

            args[0].equals("kickall", true) -> {
                if (args.size < 2) {
                    sender.sendText("&e/bh kickall <Мир> [Причина] &c-&b выгнать всех игроков с арены в мире. Причина может быть пустой")
                    return true
                }

                KE(sender, args).kickAll()
            }


            args[0].equals("reload", true) -> {
                instance.reloadConfig()
                sender.sendText("$cfgRel")
            }

            args[0].equals("help", true) -> {
                sender.sendText("&e/bh chat on &c-&b выключить чат на миры")
                sender.sendText("&e/bh chat off &c-&b включить чат на миры")
                sender.sendText("&e/bh chat info &c-&b узнать состояние чата")
                sender.sendText("&e/bh reload &c-§b перезагрузить файл конфигурации плагина")
                sender.sendText("&e/bh kick <Игрок> [Причина] &c-&b выгнать игрока с арены. Причина может быть пустой")
                sender.sendText("&e/bh kickall <Мир> [Причина] &c-&b выгнать всех игроков с арены в мире. Причина может быть пустой")
            }


            args[0].equals("chat", true) -> {
                if (sender.hasPermission("blockhunt.togglechat")) {
                    if (args.size < 2) {
                        sender.sendText("&e/bh chat on &c-&b выключить чат на миры")
                        sender.sendText("&e/bh chat off &c-&b включить чат на миры")
                        sender.sendText("&e/bh chat info &c-&b узнать состояние чата")
                        return true
                    }


                    when {
                        args[1].equals("on", true) -> ChatSwitchExecutor(sender).chatEnable()

                        args[1].equals("off", true) -> ChatSwitchExecutor(sender).chatDisable()

                        args[1].equals("info", true) ->
                            if ((ChatSwitchExecutor(sender).isChatEnabled())) {
                                sender.sendText("$chatOn")
                            } else sender.sendText("$chatOff}")


                        else -> return false
                    }

                } else sender.sendText("$noPerm")
                return true
            }

            else -> return false
        }

        return true
    }

}



