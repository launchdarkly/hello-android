package com.launchdarkly.hello_android

import android.app.Application
import com.launchdarkly.logging.LDLogLevel
import com.launchdarkly.sdk.ContextKind
import com.launchdarkly.sdk.LDContext
import com.launchdarkly.sdk.android.LDAndroidLogging
import com.launchdarkly.sdk.android.LDClient
import com.launchdarkly.sdk.android.LDConfig
import com.launchdarkly.sdk.android.LDConfig.Builder.AutoEnvAttributes
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}