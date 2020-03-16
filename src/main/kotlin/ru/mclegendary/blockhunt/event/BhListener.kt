package ru.mclegendary.blockhunt.event


import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import ru.mclegendary.blockhunt.BlockHunt.Companion.doCmd
import ru.mclegendary.blockhunt.BlockHunt.Companion.instance
import ru.mclegendary.blockhunt.util.Utils.sendText
import ru.mclegendary.blockhunt.util.getCfg
import java.util.*
import kotlin.math.roundToInt
import org.bukkit.event.player.AsyncPlayerChatEvent as ChatE
import org.bukkit.event.player.PlayerChangedWorldEvent as ChWoE
import org.bukkit.event.player.PlayerSwapHandItemsEvent as ISwapE
import org.bukkit.event.player.PlayerTeleportEvent as TPE
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause as cause
import ru.mclegendary.blockhunt.util.Utils.fbFix as fb
import ru.mclegendary.blockhunt.util.Utils.ggFix as gg

class BhListener : Listener {


    @EventHandler
    fun onChat(e: ChatE) {

        val r = e.recipients
        val sender = e.player
        val server = sender.server
        if (sender.gameMode == GameMode.SPECTATOR && (e.message.equals("gg", true) || e.message.equals(
                "good game",
                true
            )) && (!sender.world.name.equals("blockhunt", true))
        )
            gg(e, sender)
        try {
            for (player in r.iterator()) {
                if (sender.world != player.world) {
                    if (sender.hasPermission("blockhunt.user")) {
                        r.remove(player)
                        if (!sender.hasPermission("blockhunt.chat")) { //Sending message to admins
                            server.broadcast(
                                getCfg("ChatPerWorldFormat")
                                    .replace("%WORLD%", sender.world.name)
                                    .replace("%PLAYER_NAME%", sender.displayName)
                                    .replace("%MESSAGE%", e.message),
                                "blockhunt.chat"
                            )
                        } else return
                    }
                }
            }
        } catch (e: ConcurrentModificationException) {

        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    fun onTeleport(e: TPE) {
        val player = e.player

        if (!player.hasPermission("blockhunt.adm") && e.cause == cause.SPECTATE) {
            player.sendText("&cНизя!")
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onWorldChange(e: ChWoE) {
        val player = e.player

        fb(player) //In Utils

        if (player.world.name.equals("blockhunt", true)) {
            if (player.gameMode != GameMode.SPECTATOR) {

                player.gameMode = GameMode.ADVENTURE
                doCmd("spawn ${player.name}")
            }
        } else player.gameMode = GameMode.SURVIVAL
    }

    @EventHandler
    fun onHandSwap(e: ISwapE) {
        if (e.offHandItem.data.itemType == Material.FIREWORK) {
            e.player.sendText(getCfg("ItemChange"))
            e.isCancelled = true
        }
    }

    private var cooldowns: HashMap<UUID, Double?>? = null

    fun setUpCooldown() {
        cooldowns = HashMap()
    }

    private fun setCooldown(p: Player, seconds: Int) {
        val delay = System.currentTimeMillis() + (seconds * 1000).toDouble()
        cooldowns!![p.uniqueId] = delay
    }

    private fun getCooldown(p: Player): Int {
        return Math.toIntExact(((cooldowns!![p.uniqueId]!! - System.currentTimeMillis()) / 1000).roundToInt().toLong())
    }

    private fun checkCooldown(p: Player): Boolean {
        return !cooldowns!!.containsKey(p.uniqueId) || cooldowns!![p.uniqueId]!! <= System.currentTimeMillis()
    }

    @EventHandler
    fun onRightClickBlock(e: PlayerInteractEvent) {
        if (e.action == Action.RIGHT_CLICK_BLOCK && e.hand == EquipmentSlot.HAND && !e.player.isOp && !e.player.world.name.equals("blockhunt", true)) {
            if (instance.config.getBoolean("block_cooldown.isEnabled")) {
                if (checkCooldown(e.player)) {
                    setCooldown(e.player, instance.config.getInt("block_cooldown.delay"))
                } else {
                    e.player.sendText("&cК сожалению, Вы не можете делать это ещё &e${getCooldown(e.player) + 1} &cсекунд")
                    e.isCancelled = true
                }
            } else return
        }
    }
}