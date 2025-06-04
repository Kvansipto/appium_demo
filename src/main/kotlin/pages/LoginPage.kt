package org.example.pages

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class LoginPage(driver: AppiumDriver) : BasePage(driver) {

    @AndroidFindBy(xpath = "//*[@resource-id='org.wikipedia:id/login_username_text']//android.widget.EditText")
    private lateinit var usernameField: WebElement

    @AndroidFindBy(xpath = "//*[@resource-id='org.wikipedia:id/login_password_input']//android.widget.EditText")
    private lateinit var passwordField: WebElement

    @AndroidFindBy(id = "org.wikipedia:id/login_button")
    private lateinit var submitButton: WebElement

    @AndroidFindBy(id = "org.wikipedia:id/snackbar_text")
    private lateinit var loginErrorMessage: WebElement

    fun enterUsername(username: String) {
        usernameField.clear()
        if (username.isNotBlank()) usernameField.sendKeys(username)
    }

    fun enterPassword(password: String) {
        passwordField.clear()
        if (password.isNotBlank()) passwordField.sendKeys(password)
    }

    fun tapSubmit() {
        submitButton.click()
    }

    fun isSubmitButtonEnabled(): Boolean = submitButton.isEnabled

    fun isIncorrectCredentialsMessageVisible(timeout: Duration): Boolean =
        try {
            WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(loginErrorMessage))
            loginErrorMessage.isDisplayed
        } catch (e: Exception) {
            false
        }

    fun getLoginErrorMessageText(): String = loginErrorMessage.text
}
