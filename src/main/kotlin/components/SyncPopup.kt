package org.example.components

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.example.pages.BasePage
import org.openqa.selenium.WebElement

class SyncPopup(driver: AppiumDriver) : BasePage(driver) {

    @AndroidFindBy(xpath = "//*[@text='No thanks']")
    private lateinit var noThanksButton: WebElement

    fun isVisible(): Boolean = noThanksButton.isDisplayed

    fun dismiss() {
        if (isVisible()) noThanksButton.click()
    }
}