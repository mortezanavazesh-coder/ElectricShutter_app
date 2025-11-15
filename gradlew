#!/usr/bin/env sh
# Gradle Wrapper script for Unix-based systems
APP_NAME=gradle
DIRNAME=$(dirname "$0")

# اجرا با java -jar
exec java -jar "$DIRNAME/gradle/wrapper/gradle-wrapper.jar" "$@"
