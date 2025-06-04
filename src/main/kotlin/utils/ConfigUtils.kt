package utils

import java.util.*

object ConfigUtils {

    fun loadProps(fileName: String): Properties {
        val stream = javaClass.classLoader.getResourceAsStream(fileName)
            ?: throw IllegalStateException("$fileName not found in classpath")

        return Properties().apply { load(stream) }
    }

    fun getValue(envKey: String, propKey: String, props: Properties): String {
        val fromEnv = System.getenv(envKey)
        if (!fromEnv.isNullOrBlank()) return fromEnv

        val fromProps = props.getProperty(propKey)
        if (!fromProps.isNullOrBlank()) return fromProps

        throw IllegalStateException("Missing config: ENV=$envKey or property=$propKey")
    }
}
