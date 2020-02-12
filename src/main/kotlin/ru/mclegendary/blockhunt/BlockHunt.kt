package ru.mclegendary.blockhunt

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import ru.mclegendary.blockhunt.commands.*
import ru.mclegendary.blockhunt.event.BhListener

class BlockHunt : JavaPlugin() {


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
        this.getCommand("spec").executor = Spectator()
        //
        Bukkit.getPluginManager().registerEvents(BhListener, this)
    }

    override fun onDisable() {
        logger.info("I'm sorry my black friend :(")
    }


    private fun setupHAS() {
        if (Bukkit.getPluginManager().getPlugin("HideAndSeek") == null) {
            return
        }
    }

    private fun setupFeatherBoard() {
        if (Bukkit.getPluginManager().getPlugin("FeatherBoard") == null) {
            return
        }
    }

    companion object {
        lateinit var instance: BlockHunt

    }

}