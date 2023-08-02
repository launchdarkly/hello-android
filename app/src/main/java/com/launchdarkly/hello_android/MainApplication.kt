package com.launchdarkly.hello_android

import android.app.Application
import com.launchdarkly.sdk.ContextKind
import com.launchdarkly.sdk.LDContext
import com.launchdarkly.sdk.android.LDClient
import com.launchdarkly.sdk.android.LDConfig
import com.launchdarkly.sdk.android.LDConfig.Builder.AutoEnvAttributes

class MainApplication : Application() {

    companion object {

        // Set LAUNCHDARKLY_MOBILE_KEY to your LaunchDarkly SDK mobile key.
        const val LAUNCHDARKLY_MOBILE_KEY = "mobile-key-from-launch-darkly-website"
    }

    override fun onCreate() {
        super.onCreate()

        // Set LAUNCHDARKLY_MOBILE_KEY to your LaunchDarkly mobile key found on the LaunchDarkly
        // dashboard in the start guide.
        // If you want to disable the Auto EnvironmentAttributes functionality.
        // Use AutoEnvAttributes.Disabled as the argument to the Builder
        val ldConfig = LDConfig.Builder(AutoEnvAttributes.Enabled)
            .mobileKey(LAUNCHDARKLY_MOBILE_KEY)
            .build()

        // Set up the context properties. This context should appear on your LaunchDarkly context
        // dashboard soon after you run the demo.
        val context = if (isUserLoggedIn()) {
            LDContext.builder(ContextKind.DEFAULT, getUserKey())
                .name(getUserName())
                .build()
        } else {
            LDContext.builder(ContextKind.DEFAULT, "example-user-key")
                .anonymous(true)
                .build()
        }

        LDClient.init(this@MainApplication, ldConfig, context)
    }

    private fun isUserLoggedIn(): Boolean = false

    private fun getUserKey(): String = "user-key-123abc"

    private fun getUserName(): String = "Sandy"

}