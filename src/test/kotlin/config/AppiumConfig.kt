package config

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import utils.ConfigUtils
import java.net.URI

object AppiumConfig {

    private val props = ConfigUtils.loadProps("test-config.properties")

    private val apkPath = ConfigUtils.getValue("APPIUM_APP", "appium.app", props)
    private val serverUrl = ConfigUtils.getValue("APPIUM_SERVER", "appium.server", props)

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
