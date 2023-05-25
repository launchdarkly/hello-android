package com.launchdarkly.hello_android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.launchdarkly.hello_android.MainApplication.Companion.LAUNCHDARKLY_MOBILE_KEY
import com.launchdarkly.sdk.android.LDClient

class MainActivity : AppCompatActivity() {

    // Set BOOLEAN_FLAG_KEY to the feature flag key you want to evaluate.
    val BOOLEAN_FLAG_KEY = "my-boolean-flag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView : TextView = findViewById(R.id.textview)

        if (BOOLEAN_FLAG_KEY == "my-boolean-flag") {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("BOOLEAN_FLAG_KEY was not customized for this application.")
            builder.create().show()
        }

        if (LAUNCHDARKLY_MOBILE_KEY == "mobile-key-from-launch-darkly-website") {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("LAUNCHDARKLY_MOBILE_KEY was not customized for this application.")
            builder.create().show()
        }

        val client = LDClient.get()

        // to get the variation the SDK has cached
        textView.text = getString(
            R.string.flag_evaluated,
            BOOLEAN_FLAG_KEY,
            client.boolVariation(BOOLEAN_FLAG_KEY, false).toString()
        )

        // to register a listener to get updates in real time
        client.registerFeatureFlagListener(BOOLEAN_FLAG_KEY) {
            textView.text = getString(
                R.string.flag_evaluated,
                BOOLEAN_FLAG_KEY,
                client.boolVariation(BOOLEAN_FLAG_KEY, false).toString()
            )
        }

        // This call is just to make sure all evaluation events show up immediately for this demo.
        // Otherwise they will be sent at some point in the future.  You don't need to call this
        // in production, it is handled automatically at a interval (it is customizable).
        client.flush()
    }
}
