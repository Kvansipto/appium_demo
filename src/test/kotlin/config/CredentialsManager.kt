package tests.config

import java.util.*

object CredentialsManager {

    private val props: Properties by lazy {
        val inputStream = javaClass.classLoader.getResourceAsStream("credentials.properties")
            ?: throw IllegalStateException("credentials.properties file not found in classpath")

        Properties().apply {
            load(inputStream)
        }
    }

    fun getUsername(): String = get("WIKI_USER", "wikipedia.username")
    fun getPassword(): String = get("WIKI_PASS", "wikipedia.password")

    private fun get(envKey: String, propKey: String): String {
        val envValue = System.getenv(envKey)
        if (!envValue.isNullOrBlank()) return envValue

        val propValue = props.getProperty(propKey)
        if (!propValue.isNullOrBlank()) return propValue

        throw IllegalStateException("Missing credentials: ENV=$envKey or properties key=$propKey")
    }
}
