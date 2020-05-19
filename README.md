# KXposedHelper
🔥XposedHlper for Kotlin, provide some awesome utilities for your xposed module development.

[![](https://jitpack.io/v/ikws4/KXposedHelper.svg)](https://jitpack.io/#ikws4/KXposedHelper)
![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/ikws4/KXposedHelper)

# Setup
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency
```gradle
dependencies {
    implementation 'com.github.ikws4:KXposedHelper:1.1'
}
```

# Catalogue
1. [How to hook method?](#How-to-hook-method?)
2. [How to use KXSharedPreferences?](#How-to-use-KXSharedPreferences?)
3. [How to use KXBroadcastReceiver?](#How-to-use-KXBroadcastReceiver?)

### How to hook method?

##### Simple to use
```kotlin
KXposedHelpers.findAndHookMethod(Activity::class, "onCreate", parameterTypes = arrayOf(Bundle::class), methodHook = MethodHook{param->
    // `this` for the Activity instance
    // So you can write code just like in your regular way on Activity
    // For exmaple
    Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show()
})
```

#### Before and After Hook
```kotlin
KXposedHelpers.findAndHookMethod(Activity::class, "onStart", methodHook = MethodHook(
    beforeHookedMethod = { param->
    },
    afterHookedMethod = { param->
    })
)
```

#### Replece method
```kotlin
KXposedHelpers.findAndHookMethod(Activity::class, "onStart", methodHook = MethodReplecement{param->
})
```

#### Change return value
```kotlin
KXposedHelpers.findAndHookMethod(TextView::class, "getText", methodHook = MethodHook{param->
    param.result = "Changed result value"
})
```

### How to use KXSharedPreferences?
In high Android version, because the security permission, and the  XSharedPreferences not working very well, so I created the utility for SharedPreferences called KXSharedPreferences, it's use the ContentProvider to shared preferences across the applications.

Step 1. Add the provider to your AndroidManifest.xml
```xml
<provider
    android:authorities="io.ikws4.kxposedhelper.utilities.KXSharedPreferencesProvider"
    android:name="io.ikws4.kxposedhelper.utilities.KXSharedPreferencesProvider"
    android:exported="true"/>
```

Step 2. Use the KXSharedPreferences object to get SharedPreferences value.
```kotlin
val sp = KXSharedPreferences(contentResolver,"your SharedPreferences name")
sp.getString("key","defValue")
```

### How to use KXBroadcastReceiver?
Sometimes you need to receive some broadcasts from your app, so this can help you easily to receiver broadcasts.
