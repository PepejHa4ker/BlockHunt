
package ru.mclegendary.blockhunt.util

import be.maximvdw.featherboard.api.FeatherBoardAPI
import me.wazup.hideandseek.HideAndSeek
import org.bukkit.ChatColor

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import ru.mclegendary.blockhunt.BlockHunt
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance


import ru.mclegendary.blockhunt.BlockHunt.Companion.log
import ru.mclegendary.blockhunt.BlockHunt.Companion.prefix
import ru.mclegendary.blockhunt.util.Utils.sendText

object Utils {


    //Fix for FeatherBoard (restore scoreboard if player joined lobby)
    fun fbFix(player: Player) {
        val lobby = player.world.name == "blockhunt"

        if (!FeatherBoardAPI.isToggled(player)) return

        if (lobby) {
            FeatherBoardAPI.initScoreboard(player)
        }
    }


    fun ggFix(e: AsyncPlayerChatEvent, sender: Player) {
        val playerData = HideAndSeek.api.getPlayerData(sender)
        if (sender.gameMode == GameMode.SPECTATOR && e.message.equals("gg", true) && sender.world.name != "blockhunt") {
            if (playerData.hasCoins(sender, 50)) {
                playerData.removeCoins(sender, 50)
                sender.sendText(getCfg("GgInSpecWithMoney"))
            } else sender.sendText(getCfg("GgInSpec"))
            e.isCancelled = true
            log(
                "$prefix ${getCfg("GgLog")}"
                    .replace("%PLAYER%", sender.name)
            )


        }
    }

    fun CommandSender.sendText(message: String) {
        this.sendMessage(ChatColor.translateAlternateColorCodes('&', "$prefix $message"))
    }

}


fun onRightClickBlock2(e: BlockPlaceEvent) {
    val item = e.itemInHand
    val p = e.player
    val itemSlot = p.inventory.heldItemSlot
    if(instance.config.getBoolean("block_fix.isEnabled")) {
        if (e.hand == EquipmentSlot.HAND && !e.player.isOp && !e.player.world.name.equals("blockhunt", true)) {
            if (item != null) {
                p.inventory.remove(item)
                p.sendText("&cВы использовали блок, скоро он в к вам вернётся :)")
                blockPlacing(p, item, instance.config.getLong("block_fix.delay") * 20, itemSlot)
                e.isCancelled = true
            }
        }
    } else return
}


private fun blockPlacing(p: Player, block: ItemStack, delay: Long, itemSlot: Int) {
    instance.server.scheduler.scheduleSyncDelayedTask(instance, {
        val inv = p.inventory
        inv.setItem(itemSlot, block)
    }, delay)
}