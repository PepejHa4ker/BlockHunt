package ru.mclegendary.blockhunt

import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.commands.*
import ru.mclegendary.blockhunt.event.BhListener



class BlockHunt : JavaPlugin() {
    companion object {
        lateinit var instance: BlockHunt
        lateinit var listener: BhListener
        lateinit var prefix: String
        lateinit var log: String
    }


    override fun onEnable() {
        server.consoleSender.sendMessage("§3[§6BlockHunt§3] §aGet out of my board!")
        setupFB() // FB api
        setupHAS() // HideAndSeek api
        setupICO() // Vault api
        prefix = "§3[§6Прятки§3]"
        log = "§3[§6BlockHunt§3]"
        instance = this
        listener = BhListener()
        this.getCommand("chat").executor = Chat()
        this.getCommand("lottery").executor = Lottery()
        this.getCommand("exchange").executor = ExChange()
        this.getCommand("csgo").executor = CSGOCrate()
        this.getCommand("where").executor = Where()
        this.getCommand("op").executor = Op()
        this.getCommand("deop").executor = DeOp()
        this.getCommand("bh").executor = Bh()
        //
        if(isEnabled) server.pluginManager.registerEvents(listener, this)

    }

    override fun onDisable() {
        server.consoleSender.sendMessage("§3[§6BlockHunt§3] §aI'm sorry my black friend :(")
    }


    private fun setupHAS(): Boolean {
        if (server.pluginManager.getPlugin("HideAndSeek") == null) {
            server.consoleSender.sendMessage("§3[§6BlockHunt§3] §cCan't find HideAndSeek plugin! Disabling plugin!")
            this.pluginLoader.disablePlugin(this)
        } else server.consoleSender.sendMessage("§3[§6BlockHunt§3] §aHideAndSeek plugin was found! Good!"); return true
    }

    private fun setupFB(): Boolean {
        if (server.pluginManager.getPlugin("FeatherBoard") == null) {
            server.consoleSender.sendMessage("§3[§6BlockHunt§3] §cCan't find FeatherBoard plugin! Disabling plugin!")
            this.pluginLoader.disablePlugin(this)
        } else server.consoleSender.sendMessage("§3[§6BlockHunt§3] §aFeatherBoard plugin was found! Good!"); return true

    }

    private fun setupICO(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            server.consoleSender.sendMessage("§3[§6BlockHunt§3] §cCan't find Vault plugin! Disabling plugin!")
            this.pluginLoader.disablePlugin(this)
        } else server.consoleSender.sendMessage("§3[§6BlockHunt§3] §aVault plugin was found! Good!"); return true

    }

}