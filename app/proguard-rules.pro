# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/marcel/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#Line numbers
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

#NetGuard
-keepnames class com.master.metehan.filtereagle.** { *; }

#JNI
-keepclasseswithmembernames class * {
    native <methods>;
}

#JNI callbacks
-keep class com.master.metehan.filtereagle.Allowed { *; }
-keep class com.master.metehan.filtereagle.Packet { *; }
-keep class com.master.metehan.filtereagle.ResourceRecord { *; }
-keep class com.master.metehan.filtereagle.Usage { *; }
-keep class com.master.metehan.filtereagle.ServiceSinkhole {
    void nativeExit(java.lang.String);
    void nativeError(int, java.lang.String);
    void logPacket(com.master.metehan.filtereagle.Packet);
    void dnsResolved(com.master.metehan.filtereagle.ResourceRecord);
    boolean isDomainBlocked(java.lang.String);
    com.master.metehan.filtereagle.Allowed isAddressAllowed(com.master.metehan.filtereagle.Packet);
    void accountUsage(com.master.metehan.filtereagle.Usage);
}

#Support library
-keep class android.support.v7.widget.** { *; }
-dontwarn android.support.v4.**

#Picasso
-dontwarn com.squareup.okhttp.**
