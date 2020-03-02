package ru.mclegendary.blockhunt


import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.commands.*
import ru.mclegendary.blockhunt.event.BhListener
import org.bukkit.Bukkit as b


class BlockHunt : JavaPlugin() {


    companion object {
        lateinit var instance: BlockHunt
        lateinit var listener: BhListener
        lateinit var prefix: String
            private set

        fun log(text: String){b.getConsoleSender().sendMessage(text)}

        fun doCmd(cmd: String){b.dispatchCommand(b.getConsoleSender(), cmd)}}






    override fun onEnable() {
        prefix = config.getString("prefix").replace('&', '§')
        setupFB() // FB api
        setupHAS() // HideAndSeek api
        setupICO() // Vault api
        log("$prefix §aLoading config.yml")
        config.options().copyDefaults(true)
        saveConfig()
        log("$prefix ${config.getString("Message_On_Enable").replace('&', '§')}")
        instance = this
        listener = BhListener()
        this.getCommand("lottery").executor = Lottery()
        this.getCommand("exchange").executor = ExChange()
        this.getCommand("csgo").executor = CSGOCrate()
        this.getCommand("where").executor = Where()
        this.getCommand("op").executor = Op()
        this.getCommand("deop").executor = DeOp()
        this.getCommand("bh").executor = Bh()
        //
        server.pluginManager.registerEvents(listener, this)}

    override fun onDisable() {
        log("$prefix §aI'm sorry my black friend :(")}



    private fun setupHAS(): Boolean {
        if (server.pluginManager.getPlugin("HideAndSeek") == null) {
            log("$prefix §cCan't find HideAndSeek plugin! Disabling plugin!")
            instance.pluginLoader.disablePlugin(this)
        } else log("$prefix §aHideAndSeek plugin was found! Good!"); return true}


    private fun setupFB(): Boolean {
        if (server.pluginManager.getPlugin("FeatherBoard") == null) {
            log("$prefix §cCan't find FeatherBoard plugin! Disabling plugin!")
            instance.pluginLoader.disablePlugin(this)
        } else log("$prefix §aFeatherBoard plugin was found! Good!"); return true}

    private fun setupICO(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            log("$prefix §cCan't find Vault plugin! Disabling plugin!")
            instance.pluginLoader.disablePlugin(this)
        } else log("$prefix §aVault plugin was found! Good!"); return true}}





