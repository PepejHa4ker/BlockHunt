package ru.mclegendary.blockhunt

import org.bukkit.Server
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.commands.*
import ru.mclegendary.blockhunt.event.BhListener

const val prefix = "§3[§6Прятки§3]"

class BlockHunt : JavaPlugin() {
    companion object {
        lateinit var instance: BlockHunt
        lateinit var server: Server
        lateinit var console: ConsoleCommandSender
    }

    override fun onEnable() {
        logger.info("Get out of my board!")
        setupFeatherBoard() // FB api
        setupHAS() // HideAndSeek api

        instance = this

        this.getCommand("lottery").executor = Lottery()
        this.getCommand("exchange").executor = ExChange()
        this.getCommand("csgo").executor = CSGOCrate()
        this.getCommand("where").executor = Where()
        this.getCommand("op").executor = Op()
        this.getCommand("deop").executor = DeOp()
        this.getCommand("bh").executor = Bh()
        //
        server.pluginManager.registerEvents(BhListener, this)
    }

    override fun onDisable() {
        logger.info("I'm sorry my black friend :(")
    }


    private fun setupHAS() {
        if (server.pluginManager.getPlugin("HideAndSeek") == null) {
            return
        }
    }

    private fun setupFeatherBoard() {
        if (server.pluginManager.getPlugin("FeatherBoard") == null) {
            return
        }
    }

}