package io.github.t2penbix99wcoxkv3a4g.polymerServerUtils

import org.slf4j.LoggerFactory

object Utils {
    @JvmField
    val MOD_ID: String = "polymer-server-utils"

    @JvmField
    val logger = LoggerFactory.getLogger("PolymerServerUtils")
    
    @JvmField
    val version: Int = 1

    private val _cuddlyItemList = mutableListOf<String>()

    @JvmStatic
    fun addCuddlyItem(name: String) {
        _cuddlyItemList.add("${MOD_ID}:${name}")
    }

    @JvmStatic
    val CuddlyItemList: MutableList<String>
        get() {
            return _cuddlyItemList
        }
}