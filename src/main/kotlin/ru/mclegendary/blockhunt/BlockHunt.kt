package ru.mclegendary.blockhunt

import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.commands.*
import ru.mclegendary.blockhunt.event.BhListener

const val prefix = "§3[§6Прятки§3]"

class BlockHunt : JavaPlugin() {


    override fun onEnable() {
        server.consoleSender.sendMessage("$prefix §aGet out of my board!")
        setupFeatherBoard() // FB api
        setupHAS() // HideAndSeek api
        getCommand("lottery").executor = Lottery()
        getCommand("exchange").executor = ExChange()
        getCommand("csgo").executor = CSGOCrate()
        getCommand("where").executor = Where()
        getCommand("op").executor = Op()
        getCommand("deop").executor = DeOp()
        getCommand("bh").executor = Bh()
        //
        server.pluginManager.registerEvents(BhListener, this)
    }

    override fun onDisable(){ server.consoleSender.sendMessage("$prefix §aI'm sorry my black friend :(")}



    private fun setupHAS() : Boolean {
        if (server.pluginManager.getPlugin("HideAndSeek") == null) {
            server.consoleSender.sendMessage("$prefix §cCan't find HideAndSeek plugin!")
            return false
        } else server.consoleSender.sendMessage("$prefix §aHideAndSeek plugin was found! Good!")
        return true
    }

    private fun setupFeatherBoard() : Boolean {
        if (server.pluginManager.getPlugin("FeatherBoard") == null) {
            server.consoleSender.sendMessage("$prefix §cCan't find FeatherBoard plugin!")
            return false
        } else server.consoleSender.sendMessage("$prefix §aFeatherBoard plugin was found! Good!")
        return true
    }

    companion object {
        lateinit var instance: BlockHunt


    }

}