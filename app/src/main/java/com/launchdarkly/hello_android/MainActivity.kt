package com.launchdarkly.hello_android

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.launchdarkly.sdk.android.LDClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Set BOOLEAN_FLAG_KEY to the feature flag key you want to evaluate.
    val BOOLEAN_FLAG_KEY = "sample-feature"

    @Inject
    lateinit var client: LDClient;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView : TextView = findViewById(R.id.textview)
        val fullView : View = window.decorView

        val flagValue = client.boolVariation(BOOLEAN_FLAG_KEY, false)

        // to get the variation the SDK has cached
        textView.text = getString(
            R.string.flag_evaluated,
            BOOLEAN_FLAG_KEY,
            flagValue.toString()
        )

        // Style the display
        textView.setTextColor(resources.getColor(R.color.colorText))
        if(flagValue) {
            fullView.setBackgroundColor(resources.getColor(R.color.colorBackgroundTrue))
        } else {
            fullView.setBackgroundColor(resources.getColor(R.color.colorBackgroundFalse))
        }

        // to register a listener to get updates in real time
        client.registerFeatureFlagListener(BOOLEAN_FLAG_KEY) {
            val changedFlagValue = client.boolVariation(BOOLEAN_FLAG_KEY, false)
            textView.text = getString(
                R.string.flag_evaluated,
                BOOLEAN_FLAG_KEY,
                changedFlagValue.toString()
            )
            if(changedFlagValue) {
                fullView.setBackgroundColor(resources.getColor(R.color.colorBackgroundTrue))
            } else {
                fullView.setBackgroundColor(resources.getColor(R.color.colorBackgroundFalse))
            }
        }
    }
}
