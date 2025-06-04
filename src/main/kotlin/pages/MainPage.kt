package org.example.pages

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

class MainPage(driver: AppiumDriver) : BasePage(driver) {

    @AndroidFindBy(accessibility = "More")
    private lateinit var moreButton: WebElement

    @AndroidFindBy(id = "org.wikipedia:id/main_drawer_account_container")
    private lateinit var loginOrJoinButton: WebElement

    @AndroidFindBy(id = "org.wikipedia:id/main_drawer_account_name")
    private lateinit var accountNameButton: WebElement

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Settings\")")
    private lateinit var settingsButton: WebElement

    fun getAccountName(): String = accountNameButton.text

    fun tapMore() = moreButton.click()

    fun tapLoginOrJoin() = loginOrJoinButton.click()

    fun tapSettings() = settingsButton.click()
}
