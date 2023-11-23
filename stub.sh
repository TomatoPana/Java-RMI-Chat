#!/bin/sh
MYSELF=`which "$0" 2>/dev/null`
[ $? -gt 0 -a -f "$0" ] && MYSELF="./$0"
java=java
if test -n "$JAVA_HOME"; then
    java="$JAVA_HOME/bin/java"
fi
java_args="--module-path /home/mdlb/Downloads/openjfx-21.0.1_linux-x64_bin-sdk/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml"
exec "$java" $java_args -jar $MYSELF "$@"
exit 1