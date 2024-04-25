# LaunchDarkly sample Android application

We've built a simple mobile application written in Kotlin that demonstrates how LaunchDarkly's SDK works.

Below, you'll find the build procedure. For more comprehensive instructions, you can visit your [Quickstart page](https://app.launchdarkly.com/quickstart#/) or the [Android reference guide](https://docs.launchdarkly.com/sdk/client-side/android).

## Build instructions

1. Make sure you have [Android Studio](https://developer.android.com/studio/index.html) installed.
2. Edit `MainApplication.kt` and `MainActivity.kt` in the `src/main/java/com/launchdarkly/hello_android` directory and set the value of LAUNCHDARKLY_MOBILE_KEY to your LaunchDarkly mobile key. If there is an existing boolean feature flag in your LaunchDarkly project that you want to evaluate, set BOOLEAN_FLAG_KEY to the flag key.

```kotlin
    val LAUNCHDARKLY_MOBILE_KEY = "mobile-key-from-launch-darkly-website"

    val BOOLEAN_FLAG_KEY = "my-boolean-flag"
```

3. Run your application through the Emulator or on a real device.

You should see the message "The <flagKey> feature flag evaluates to <flagValue>.". The application will run continuously and react to the flag changes in LaunchDarkly.

## Looking for a Java example?

You can find an example mobile application written in Java in [this GitHub repository](https://github.com/launchdarkly/hello-android-java).
