package tests

import config.AppiumConfig
import io.appium.java_client.AppiumDriver
import org.assertj.core.api.Assertions.assertThat
import org.example.steps.LoginSteps
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginTest : BaseTest() {

    private lateinit var driver: AppiumDriver
    private lateinit var loginSteps: LoginSteps

    @BeforeEach
    fun setUp() {
        driver = AppiumConfig.createDriver()
        loginSteps = LoginSteps(driver)
        loginSteps.goToLoginPage()
    }

    @Test
    @DisplayName("Successful login with valid credentials")
    fun testSuccessfulLogin() {
        loginSteps.loginAndHandlePopup(userName, password)

        val actualUserName = loginSteps.getUserName()
        assertThat(actualUserName)
            .withFailMessage("Expected logged-in username to be '%s', but was '%s'", userName, actualUserName)
            .isEqualTo(userName)
    }

    @Test
    @DisplayName("Login fails with invalid password")
    fun testLoginWithInvalidPassword() {
        loginSteps.login(userName, "WrongPassword123")

        assertTrue(
            loginSteps.isLoginErrorVisible(), "Error message should be displayed for an invalid password"
        )
        val error = loginSteps.getLoginErrorMessage()
        assertTrue(
            error.lowercase().contains("incorrect") || error.lowercase().contains("неверн"),
            "Error message should indicate incorrect password. Actual: $error"
        )
    }

    @Test
    @DisplayName("Login button is disabled until both fields are filled")
    fun testLoginButtonDisabledWithIncompleteInput() {
        loginSteps.checkLoginButtonDisabled("", "")
        loginSteps.checkLoginButtonDisabled("qa_user", "")
        loginSteps.checkLoginButtonDisabled("", "123456")
        loginSteps.checkLoginButtonEnabled("qa_user", "123456")
    }

    @Test
    @DisplayName("Login again after logging out")
    fun testReLoginAfterLogout() {
        loginSteps.loginAndHandlePopup(userName, password)

        loginSteps.logout()

        loginSteps.goToLoginPage()
        loginSteps.loginAndHandlePopup(userName, password)

        val actualUserName = loginSteps.getUserName()
        assertThat(actualUserName)
            .withFailMessage("Expected logged-in username to be '%s', but was '%s'", userName, actualUserName)
            .isEqualTo(userName)
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }
}
