package ru.mclegendary.blockhunt.util

import ru.mclegendary.blockhunt.BlockHunt.Companion.instance

object Messages {

    val noPerm: String = instance.config.getString("NoPermission")
    val invalidCommand: String = instance.config.getString("InvalidCommand")
    val chatDisabled: String = instance.config.getString("ChatOffSuccess")
    val chatEnabled: String = instance.config.getString("ChatOnSuccess")
    val chatAlreadyEnabled: String = instance.config.getString("ChatAlreadyEnabled")
    val chatAlreadyDisabled: String = instance.config.getString("ChatAlreadyDisabled")
    val chatOn: String = instance.config.getString("ChatOn")
    val chatOff: String = instance.config.getString("ChatOff")
    val cfgRel: String = instance.config.getString("CfgReloaded")
    val noCoins: String = instance.config.getString("NoCoins")
    const val onlyCons: String = "&cТолько с консоли, зайчик"
    val playerOffline: String = instance.config.getString("PlayerOffline")


}