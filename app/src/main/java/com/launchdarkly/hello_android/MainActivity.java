package com.launchdarkly.hello_android;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.launchdarkly.android.LDClient;
import com.launchdarkly.android.LDConfig;
import com.launchdarkly.android.LDUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LDMainActivity";
    private static final String FLAG_KEY = "myBooleanFlag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LDConfig ldConfig = new LDConfig.Builder()
                .setMobileKey("MOBILE_KEY")
                .build();

        LDUser user = new LDUser.Builder("user key")
                .email("fake@example.com")
                .build();

        LDClient client = LDClient.init(getApplication(), ldConfig, user, 5);

        if (client.boolVariation(FLAG_KEY, false)) {
            Log.i(TAG, "Showing feature");
        } else {
            Log.i(TAG, "Not Showing feature");
        }

        client.flush();
    }
}
