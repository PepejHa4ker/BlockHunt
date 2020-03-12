package ru.mclegendary.blockhunt.util

import ru.mclegendary.blockhunt.BlockHunt.Companion.instance


fun getCfg(cfgString: String) : String {
    return instance.config.getString(cfgString).replace('&', '§')
}


object Messages {

    val NO_PERM = getCfg("NoPermission")
    val INVALID_COMMAND = getCfg("InvalidCommand")
    val CONFIG_RELOADED = getCfg("CfgReloaded")
    val NO_COINS = getCfg("NoCoins")
    const val ONLY_CONSOLE = "&cТолько с консоли, зайчик"
    val PLAYER_OFFLINE = getCfg("PlayerOffline")


}