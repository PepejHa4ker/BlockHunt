package ru.mclegendary.blockhunt.util

import ru.mclegendary.blockhunt.BlockHunt.Companion.instance


fun getCfg(cfgString: String) : String {
    return instance.config.getString(cfgString).replace('&', '§')
}


object Messages {

    // Before
    val NO_PERM = getCfg("NoPermission")
    val INVALID_COMMAND = getCfg("InvalidCommand")
    val CHAT_DISABLED = getCfg("ChatOffSuccess")
    val CHAT_ENABLED = getCfg("ChatOnSuccess")
    val CHAT_ALREADY_ENABLED = getCfg("ChatAlreadyEnabled")
    val CHAT_ALREADY_DISABLED = getCfg("ChatAlreadyDisabled")
    val CHAT_ON = getCfg("ChatOn")
    val CHAT_OFF = getCfg("ChatOff")
    val CONFIG_RELOADED = getCfg("CfgReloaded")
    val NO_COINS = getCfg("NoCoins")
    val ONLY_CONSOLE = "&cТолько с консоли, зайчик"
    val PLAYER_OFFLINE = getCfg("PlayerOffline")

    /*
    After

    val noPerm: String = instance.config.getString("NoPermission")
    val invalidCommand: String = instance.config.getString("InvalidCommand")
    val chatDisabled: String = instance.config.getString("ChatOffSuccess")
    val chatEnabled: String = instance.config.getString("ChatOnSuccess")
    val chatAlreadyEnabled: String = instance.config.getString("ChatAlreadyEnabled")
    val chatAlreadyDisabled: String = instance.config.getString("ChatAlreadyDisabled")
    val chatOn: String = instance.config.getString("ChatAlreadyDisabled")
    val chatOff: String = instance.config.getString("ChatOff")
    val cfgRel: String = instance.config.getString("CfgReloaded")
    val noCoins: String = instance.config.getString("NoCoins")
    const val onlyCons: String = "&cТолько с консоли, зайчик"
    val playerOffline: String = instance.config.getString("PlayerOffline")

*/

}