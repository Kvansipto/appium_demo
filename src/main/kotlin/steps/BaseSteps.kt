package org.example.steps

import io.appium.java_client.AppiumDriver
import io.qameta.allure.Step
import org.example.components.SyncPopup

abstract class BaseSteps(protected val driver: AppiumDriver){
    private val syncPopup = SyncPopup(driver)

    @Step("Handle sync popup if present")
    fun handleSyncPopup() = syncPopup.dismiss()
}