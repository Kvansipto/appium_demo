package org.example.pages

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class SettingsPage(driver: AppiumDriver) : BasePage(driver) {

    companion object {
        private val LOGOUT_BUTTON_LOCATOR = By.id("org.wikipedia:id/logoutButton")
    }

    @AndroidFindBy(xpath = "//*[@text='Log out']")
    private lateinit var confirmLogoutButton: WebElement

    fun scrollToLogoutButtonAndClick() {
        SwipeUtils.scrollToElement(driver, LOGOUT_BUTTON_LOCATOR, 5)
        driver.findElement(LOGOUT_BUTTON_LOCATOR).click()
    }

    fun clickConfirmLogoutButton() = confirmLogoutButton.click()
}
