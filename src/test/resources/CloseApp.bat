@echo off
:: Get the package name of the current running app
for /f "delims=/ tokens=1" %%a in ('adb shell dumpsys activity activities ^| find "mResumedActivity"') do set "package=%%a"
set "package=%package:~19%"
:: Force stop the current running app
adb shell am force-stop %package%
echo The app %package% has been closed.
:: Uninstall the app
adb uninstall %package%
echo The app %package% has been uninstalled.
:: Navigate to the home screen
adb shell input keyevent KEYCODE_HOME
echo Navigated to the home screen.
