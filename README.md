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
    implementation 'com.github.ikws4:KXposedHelper:1.2'
}
```

# Catalogue
1. [How to hook method?](#How-to-hook-method?)
    1. [Simple to use](#Simple-to-use)
    2. [Before and after hook](#Before-and-after-hook)
    3. [Replece method](#Replece-method)
    4. [Change return value](#Change-return-value)
    5. [Some utilities in hook](#Some-utilities-in-hook)
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

#### Before and after hook
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

#### Some utilities in hook
```kotlin
// ActivityHookHelper for Activity lifecycle hook
ActivityHookHelper.onCreate {  }
ActivityHookHelper.onDestroy {  }
...

// ApplicationHookHelper
ApplicationHookHelper.onCreate {  }
ApplicationHookHelper.attach {  }
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

```kotlin
val broadReceiver = KXBroadcastReceiver(
    IntentFilter().apply {
        addAction("Action1")
        addAction("Action2")
    })

broadReceiver.setOnReceiveListener { context, intent ->
    when (intent.action) {
        "Action1" -> {
        }
        "Action2" -> {
        }
    }
}

ActivityHookHelper.onCreate {
    broadReceiver.register(this)
}

ActivityHookHelper.onDestroy {
    broadReceiver.unRegister(this)
}
```

## LICENSE
```
MIT License

Copyright (c) 2020 zhiping

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
