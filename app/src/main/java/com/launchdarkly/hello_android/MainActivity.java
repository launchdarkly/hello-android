package com.launchdarkly.hello_android;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.android.LDClient;
import com.launchdarkly.sdk.android.LDConfig;

// Set FLAG_KEY to the feature flag key you want to evaluate.
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LDMainActivity";
    private static final String FLAG_KEY = "my-boolean-flag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Set mobileKey to your LaunchDarkly mobile key.
        LDConfig ldConfig = new LDConfig.Builder()
                .mobileKey("MOBILE_KEY")
                .build();

// Set up the evaluation context. This context should appear on your LaunchDarkly contexts
// dashboard soon after you run the demo.
        LDContext context = LDContext.builder("example-user-key")
                .name("Sandy")
                .build();

        LDClient client = LDClient.init(getApplication(), ldConfig, context, 5);

        boolean flagValue = client.boolVariation(FLAG_KEY, false);
        Log.i(TAG, String.format("Feature flag [%s] is [%s] for this context", FLAG_KEY, flagValue));

        client.flush();
    }
}
