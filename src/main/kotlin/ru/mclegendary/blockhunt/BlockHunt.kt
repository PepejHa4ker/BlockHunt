package ru.mclegendary.blockhunt



import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.commands.*
import ru.mclegendary.blockhunt.event.BhListener
import ru.mclegendary.blockhunt.util.getCfg

import org.bukkit.Bukkit as b


class BlockHunt : JavaPlugin() {


    companion object {
        lateinit var instance: BlockHunt
        lateinit var listener: BhListener
        lateinit var prefix: String


        fun log(text: String) {
            b.getConsoleSender().sendMessage("$prefix $text")
        }

        fun doCmd(cmd: String) {
            b.dispatchCommand(b.getConsoleSender(), cmd)
        }

    }

    init {
        instance = this
        prefix = getCfg("prefix").replace('&', '§')
    }
    override fun onEnable() {

        setupFB() // FB api
        setupHAS() // HideAndSeek api
        setupICO() // Vault api
        log("§aLoading config.yml")
        config.options().copyDefaults(true)
        saveConfig()
        log(getCfg("Message_On_Enable").replace('&', '§'))
        listener = BhListener()
        listener.setUpCooldown()
        getCommand("lottery").executor = Lottery()
        getCommand("exchange").executor = ExChange()
        getCommand("csgo").executor = CSGOCrate()
        getCommand("where").executor = Where()
        getCommand("op").executor = Op()
        getCommand("deop").executor = DeOp()
        getCommand("bh").executor = Bh()


        server.pluginManager.registerEvents(listener, this)
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





