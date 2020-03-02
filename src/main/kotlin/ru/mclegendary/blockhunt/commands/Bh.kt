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
            args.isEmpty() -> return false

            args[0].equals("kick", true) -> {
                if (args.size < 2) {
                    sender.sendMessage("$prefix §e/bh kick <Игрок> [Причина] §c-§b выгнать игрока с арены. Причина может быть пустой")
                    return true
                }
                KE(sender, args).kick()
            }

            args[0].equals("kickall", true) -> {
                if (args.size < 2) {
                    sender.sendMessage("$prefix §e/bh kickall <Мир> [Причина] §c-§b выгнать всех игроков с арены в мире. Причина может быть пустой")
                    return true}

                KE(sender, args).kickAll()}



            args[0].equals("reload", true) -> {
                instance.reloadConfig()
                sender.sendMessage("$prefix ${instance.config.getString("CfgReloaded").replace('&', '§')}")}

            args[0].equals("help", true) -> {
                sender.sendMessage("$prefix §e/bh chat on §c-§b выключить чат на миры")
                sender.sendMessage("$prefix §e/bh chat off §c-§b включить чат на миры")
                sender.sendMessage("$prefix §e/bh chat info §c-§b узнать состояние чата")
                sender.sendMessage("$prefix §e/bh reload §c-§b перезагрузить файл конфигурации плагина")
                sender.sendMessage("$prefix §e/bh kick <Игрок> [Причина] §c-§b выгнать игрока с арены. Причина может быть пустой")
                sender.sendMessage("$prefix §e/bh kickall <Мир> [Причина] §c-§b выгнать всех игроков с арены в мире. Причина может быть пустой")}




            args[0].equals("chat", true)  -> {
                if (sender.hasPermission("blockhunt.togglechat")) {
                    if (args.size < 2){
                        sender.sendMessage("$prefix §e/bh chat on §c-§b выключить чат на миры")
                        sender.sendMessage("$prefix §e/bh chat off §c-§b включить чат на миры")
                        sender.sendMessage("$prefix §e/bh chat info §c-§b узнать состояние чата")
                        return true}


                    when {
                        args[1].equals("on", true) -> ChatSwitchExecutor(sender).chatEnable()

                        args[1].equals("off", true) -> ChatSwitchExecutor(sender).chatDisable()

                        args[1].equals("info", true) ->
                            if ((ChatSwitchExecutor(sender).isChatEnabled())) {
                                sender.sendMessage(
                                    "$prefix ${instance.config.getString("ChatOn").replace
                                        ('&', '§')}")}

                              else sender.sendMessage(
                                "$prefix ${instance.config.getString("ChatOff").replace
                                    ('&', '§')}")

                        else -> return false}

                } else sender.sendMessage(
                    "$prefix ${instance.config.getString("NoPermission").replace(
                        '&', '§'
                    )}")
                return true}

            else -> return false}

        return true}}



