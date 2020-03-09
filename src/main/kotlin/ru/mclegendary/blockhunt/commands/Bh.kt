package ru.mclegendary.blockhunt.commands


import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.util.Messages
import ru.mclegendary.blockhunt.util.Utils.isChatEnabled
import ru.mclegendary.blockhunt.util.Utils.setChatEnabled
import ru.mclegendary.blockhunt.util.Utils.sendText
import ru.mclegendary.blockhunt.executors.KickExecutor as KickExecutor

class Bh : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: org.bukkit.command.Command,
        label: String,
        args: Array<out String>
    ): Boolean {


        when {

            args.isEmpty() -> {
                 return false
            }

            args[0].equals("kick", true) -> {
                if (args.size < 2) {
                    sender.sendText("&e/bh kick <Игрок> [Причина] &c-&b выгнать игрока с арены. Причина может быть пустой")
                    return true
                }
                KickExecutor(sender, args).kick()
            }

            args[0].equals("kickall", true) -> {
                if (args.size < 2) {
                    sender.sendText("&e/bh kickall <Мир> [Причина] &c-&b выгнать всех игроков с арены в мире. Причина может быть пустой")
                    return true
                }

                KickExecutor(sender, args).kickAll()
            }


            args[0].equals("reload", true) -> {
                instance.reloadConfig()
                sender.sendText(Messages.cfgRel)
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
                        args[1].equals("on", true) -> {
                            if(isChatEnabled()){
                                sender.sendText(Messages.chatAlreadyEnabled)
                            } else {
                                sender.sendText(Messages.chatEnabled)
                                setChatEnabled(true)
                            }
                        }

                        args[1].equals("off", true) -> {
                            if(!isChatEnabled()){
                                sender.sendText(Messages.chatAlreadyDisabled)
                            } else {
                                sender.sendText(Messages.chatDisabled)
                                setChatEnabled(false)
                            }
                        }

                        args[1].equals("info", true) ->
                            if (isChatEnabled()) {
                                sender.sendText(Messages.chatOn)
                            } else sender.sendText(Messages.chatOff)


                        else -> return false
                    }

                } else sender.sendText(Messages.noPerm)
                return true
            }

            else -> return false
        }

        return true
    }


}



