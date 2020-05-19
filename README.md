# KXposedHelper
🔥XposedHlper for Kotlin, provide awesome utilities for your xpoesd module develop.

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
    implementation 'com.github.ikws4:KXposedHelper:1.0'
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
    // `this` for the activity instance
    // So you can write code just like in your normal way on Activity
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

#### Change return value
```kotlin
KXposedHelpers.findAndHookMethod(TextView::class, "getText", methodHook = MethodHook{param->
    param.result = "Changed result value"
})
```

### How to use KXSharedPreferences?
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
