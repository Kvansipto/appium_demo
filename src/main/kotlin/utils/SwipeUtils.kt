import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.interactions.PointerInput
import java.time.Duration

object SwipeUtils {
    fun swipeUp(driver: AppiumDriver) {
        val size: Dimension = driver.manage().window().size
        val startX = size.width / 2
        val startY = (size.height * 0.8).toInt()
        val endY = (size.height * 0.2).toInt()

        val finger = PointerInput(PointerInput.Kind.TOUCH, "finger")
        val swipe = org.openqa.selenium.interactions.Sequence(finger, 1).apply {
            addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
            addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
            addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY))
            addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
        }

        driver.perform(listOf(swipe))
    }

    fun scrollToElement(driver: AppiumDriver, locator: By, maxSwipes: Int) {
        var attempts = 0
        while (driver.findElements(locator).isEmpty() && attempts < maxSwipes) {
            swipeUp(driver)
            attempts++
        }

        if (driver.findElements(locator).isEmpty()) {
            throw NoSuchElementException("Element not found after $maxSwipes swipes: $locator")
        }
    }
}