package org.example.pages

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class OnboardingPage(driver: AppiumDriver) : BasePage(driver) {

    @FindBy(id = "org.wikipedia:id/fragment_onboarding_skip_button")
    private lateinit var skipButton: WebElement

    fun skip() = skipButton.click()

    fun isDisplayed(): Boolean =
        try {
            skipButton.isDisplayed
        } catch (e: Exception) {
            false
        }
}
