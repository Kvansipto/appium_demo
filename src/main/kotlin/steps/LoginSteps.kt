package org.example.steps

import io.appium.java_client.AppiumDriver
import io.qameta.allure.Step
import org.assertj.core.api.Assertions.assertThat
import org.example.pages.*
import java.time.Duration

class LoginSteps(driver: AppiumDriver) : BaseSteps(driver) {

    private val onboardingPage = OnboardingPage(driver)
    private val mainPage = MainPage(driver)
    private val loginPage = LoginPage(driver)
    private val registerPage = RegisterPage(driver)
    private val settingsPage = SettingsPage(driver)

    @Step("Navigate to Login page")
    fun goToLoginPage() {
        if (onboardingPage.isDisplayed()) onboardingPage.skip()
        mainPage.tapMore()
        mainPage.tapLoginOrJoin()
        registerPage.clickLoginButton()
    }

    @Step("Log in with username: {username} and password: {password}")
    fun login(username: String, password: String) {
        loginPage.enterUsername(username)
        loginPage.enterPassword(password)
        loginPage.tapSubmit()
    }

    fun loginAndHandlePopup(username: String, password: String) {
        login(username, password)
        handleSyncPopup()
    }

    @Step("Get userName")
    fun getUserName(): String {
        mainPage.tapMore()
        return mainPage.getAccountName()
    }

    @Step("Check if login error message is visible")
    fun isLoginErrorVisible(): Boolean =
        loginPage.isIncorrectCredentialsMessageVisible(Duration.ofSeconds(5))

    @Step("Get login error message text")
    fun getLoginErrorMessage(): String = loginPage.getLoginErrorMessageText()

    @Step("Verify login button is disabled for username: {username}, password: {password}")
    fun checkLoginButtonDisabled(username: String, password: String) {
        loginPage.enterUsername(username)
        loginPage.enterPassword(password)

        assertThat(loginPage.isSubmitButtonEnabled())
            .`as`("Login button should be disabled for username='%s', password='%s'", username, password)
            .isFalse()
    }

    @Step("Verify login button is enabled for username: {username}, password: {password}")
    fun checkLoginButtonEnabled(username: String, password: String) {
        loginPage.enterUsername(username)
        loginPage.enterPassword(password)

        assertThat(loginPage.isSubmitButtonEnabled())
            .`as`("Login button should be enabled for username='%s', password='%s'", username, password)
            .isTrue()
    }

    @Step("Log out")
    fun logout() {
        mainPage.tapMore()
        mainPage.tapSettings()
        handleSyncPopup()
        settingsPage.scrollToLogoutButtonAndClick()
        settingsPage.clickConfirmLogoutButton()
    }
}