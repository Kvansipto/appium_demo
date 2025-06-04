package config

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URI
import java.util.*

object AppiumConfig {

    private val props = Properties()

    init {
        val configStream = this::class.java.classLoader.getResourceAsStream("test-config.properties")
            ?: throw RuntimeException("Cannot find test-config.properties in resources.")
        props.load(configStream)
    }

    private val apkPath = System.getProperty("appium.app") ?: props.getProperty("appium.app")
    private val serverUrl = System.getProperty("appium.server") ?: props.getProperty("appium.server")

    private val capabilities: DesiredCapabilities
        get() = DesiredCapabilities().apply {
            setCapability("platformName", "Android")
            setCapability("appium:deviceName", "emulator-5554")
            setCapability("appium:automationName", "UiAutomator2")
            setCapability("appium:app", apkPath)
            setCapability("appium:appWaitActivity", "*")
            setCapability("appium:noReset", false)
            setCapability("appium:fullReset", true)
        }

    fun createDriver(): AppiumDriver = AndroidDriver(URI.create(serverUrl).toURL(), capabilities)
}
