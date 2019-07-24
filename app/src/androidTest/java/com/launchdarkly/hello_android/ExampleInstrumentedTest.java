package com.launchdarkly.hello_android;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();

        assertEquals("com.launchdarkly.hello_android", appContext.getPackageName());
    }
}
