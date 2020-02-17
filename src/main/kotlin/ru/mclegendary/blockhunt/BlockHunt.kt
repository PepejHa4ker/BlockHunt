package ru.mclegendary.blockhunt

import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.commands.*
import ru.mclegendary.blockhunt.event.BhListener

const val prefix = "§3[§6Прятки§3]"
const val log = "§3[§6BlockHunt§3]"

class BlockHunt : JavaPlugin() {
    companion object {
        lateinit var instance: BlockHunt
        lateinit var listener: BhListener

    }


    override fun onEnable() {
        server.consoleSender.sendMessage("$log §aGet out of my board!")
        setupFB() // FB api
        setupHAS() // HideAndSeek api
        listener = BhListener()
        instance = this
        this.getCommand("chat").executor = Chat()
        this.getCommand("lottery").executor = Lottery()
        this.getCommand("exchange").executor = ExChange()
        this.getCommand("csgo").executor = CSGOCrate()
        this.getCommand("where").executor = Where()
        this.getCommand("op").executor = Op()
        this.getCommand("deop").executor = DeOp()
        this.getCommand("bh").executor = Bh()
        //
        server.pluginManager.registerEvents(listener, this)

    }

    override fun onDisable() {
        server.consoleSender.sendMessage("$log §aI'm sorry my black friend :(")
    }


    private fun setupHAS(): Boolean {
        if (server.pluginManager.getPlugin("HideAndSeek") == null) {
            server.consoleSender.sendMessage("$log §cCan't find HideAndSeek plugin!")
            this.pluginLoader.disablePlugin(this)
        } else server.consoleSender.sendMessage("$log §aHideAndSeek plugin was found! Good!"); return true
    }

    private fun setupFB(): Boolean {
        if (server.pluginManager.getPlugin("FeatherBoard") == null) {
            server.consoleSender.sendMessage("$log §cCan't find FeatherBoard plugin!")
            this.pluginLoader.disablePlugin(this)
        } else server.consoleSender.sendMessage("$log §aFeatherBoard plugin was found! Good!"); return true

    }


}