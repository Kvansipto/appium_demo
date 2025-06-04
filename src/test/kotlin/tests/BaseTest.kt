package tests

import config.CredentialsManager


open class BaseTest {

    protected val userName: String by lazy {
        CredentialsManager.getUsername()
    }

    protected val password: String by lazy {
        CredentialsManager.getPassword()
    }

}