package org.example.pages

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

class RegisterPage(driver: AppiumDriver) : BasePage(driver) {

    @AndroidFindBy(id = "org.wikipedia:id/create_account_login_button")
    private lateinit var loginButton: WebElement

    fun clickLoginButton() = loginButton.click()
}
