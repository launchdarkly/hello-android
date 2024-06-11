package com.launchdarkly.hello_android

import android.app.Application
import android.content.Context
import android.util.Log
import com.launchdarkly.logging.LDLogLevel
import com.launchdarkly.sdk.ContextKind
import com.launchdarkly.sdk.LDContext
import com.launchdarkly.sdk.android.LDAndroidLogging
import com.launchdarkly.sdk.android.LDClient
import com.launchdarkly.sdk.android.LDConfig
import com.launchdarkly.sdk.android.LDConfig.Builder.AutoEnvAttributes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object LDClientModule {

    const val LAUNCHDARKLY_MOBILE_KEY = "Your key here"

    @Provides
    fun provideLDClient(@ApplicationContext appContext: Context): LDClient {
        // Set LAUNCHDARKLY_MOBILE_KEY to your LaunchDarkly mobile key found on the LaunchDarkly
        // dashboard in the start guide.
        // If you want to disable the Auto EnvironmentAttributes functionality.
        // Use AutoEnvAttributes.Disabled as the argument to the Builder
        val ldConfig = LDConfig.Builder(AutoEnvAttributes.Enabled)
            .logAdapter(LDAndroidLogging.adapter())
            .logLevel(LDLogLevel.DEBUG)
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

        Log.d("Todd", "Providing another instance.")

        return LDClient.init(appContext as Application, ldConfig, context).get()
    }

    private fun isUserLoggedIn(): Boolean = false

    private fun getUserKey(): String = "user-key-123abc"

    private fun getUserName(): String = "Sandy"
}