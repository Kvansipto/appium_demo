package config

import utils.ConfigUtils

object CredentialsManager {

    private val props = ConfigUtils.loadProps("credentials.properties")

    fun getUsername(): String = ConfigUtils.getValue("WIKI_USER", "wikipedia.username", props)
    fun getPassword(): String = ConfigUtils.getValue("WIKI_PASS", "wikipedia.password", props)
}
