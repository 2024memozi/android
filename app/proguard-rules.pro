# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# Hilt 관련 클래스 유지
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class dagger.** { *; }
-keep class dagger.hilt.internal.** { *; }

-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponentManager { *; }
-keep class * extends dagger.hilt.EntryPoints { *; }

# Hilt가 생성하는 클래스 유지
-keep class **_HiltModules { *; }
-keep class **_HiltComponents { *; }

# ViewModel 관련 클래스 유지
-keep class androidx.lifecycle.ViewModel { *; }
-keep class com.memozi.memo.category.CategoryViewModel { *; }

-keepattributes SourceFile,LineNumberTable

# 주입에 필요한 javax.inject 패키지 관련 클래스 유지
-keep class javax.inject.** { *; }

# AndroidX 라이브러리 관련 코드 유지 (Hilt와 연동되는 부분)
-keep class androidx.hilt.** { *; }