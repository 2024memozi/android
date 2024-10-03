# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Uncomment this to preserve the line number information for debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# -----------------------------------------
# Hilt 관련 클래스 유지 규칙
# -----------------------------------------
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

# Dagger Hilt가 생성하는 모든 컴포넌트 유지
-keep class **_HiltComponents$ActivityC { *; }
-keep class **_HiltComponents$FragmentC { *; }
-keep class **_HiltComponents$ServiceC { *; }
-keep class **_HiltComponents$ViewModelC { *; }

# -----------------------------------------
# ViewModel 관련 클래스 유지 규칙
# -----------------------------------------
-keep class androidx.lifecycle.ViewModel { *; }
-keep class com.memozi.memo.category.CategoryViewModel { *; }

# -----------------------------------------
# AndroidX 라이브러리 관련 클래스 유지 규칙
# -----------------------------------------
-keep class androidx.hilt.** { *; }
-keep class androidx.lifecycle.** { *; }

# -----------------------------------------
# 주입에 필요한 javax.inject 패키지 관련 클래스 유지
# -----------------------------------------
-keep class javax.inject.** { *; }

# -----------------------------------------
# Butterknife 관련 규칙 (필요 시 사용)
# -----------------------------------------
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# -----------------------------------------
# 기타 규칙
# -----------------------------------------
# dagger.hilt.android.internal.managers 패키지에 있는 클래스 유지
-keep class dagger.hilt.android.internal.managers.ActivityComponentManager { *; }

# Dagger 내부 생성자 유지
-keep class dagger.Component { *; }
-keep class dagger.Subcomponent { *; }

# ViewModel 및 Fragment 관련 유지
-keepclassmembers class ** {
    @androidx.lifecycle.ViewModel *;
    @androidx.fragment.app.Fragment *;
}

-keepattributes SourceFile,LineNumberTable
