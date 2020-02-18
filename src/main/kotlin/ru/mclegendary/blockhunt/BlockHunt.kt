package ru.mclegendary.blockhunt

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.commands.*
import ru.mclegendary.blockhunt.event.BhListener


class BlockHunt : JavaPlugin() {
    companion object {
        lateinit var instance: BlockHunt
        lateinit var listener: BhListener
        lateinit var prefix: String

        fun log(text: String){Bukkit.getConsoleSender().sendMessage("§3[§6BlockHunt§3] $text")}

    }

    override fun onEnable() {
        log("§aGet out of my board!")
        setupFB() // FB api
        setupHAS() // HideAndSeek api
        setupICO() // Vault api
        prefix = "§3[§6Прятки§3]"
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
        log("§aI'm sorry my black friend :(")
    }


    private fun setupHAS(): Boolean {
        if (server.pluginManager.getPlugin("HideAndSeek") == null) {
            log("§cCan't find HideAndSeek plugin! Disabling plugin!")
            instance.pluginLoader.disablePlugin(this)
        } else log("§aHideAndSeek plugin was found! Good!"); return true
    }

    private fun setupFB(): Boolean {
        if (server.pluginManager.getPlugin("FeatherBoard") == null) {
            log("§cCan't find FeatherBoard plugin! Disabling plugin!")
            instance.pluginLoader.disablePlugin(this)
        } else log("§aFeatherBoard plugin was found! Good!"); return true

    }

    private fun setupICO(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            log("§cCan't find Vault plugin! Disabling plugin!")
            instance.pluginLoader.disablePlugin(this)
        } else log("§aVault plugin was found! Good!"); return true

    }

}