#!/usr/bin/env bash


gradle assembleDebug ;
gradle uninstallAll;
adb install app/build/outputs/apk/app-debug.apk;