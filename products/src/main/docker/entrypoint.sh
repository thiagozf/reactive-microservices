#!/bin/sh

echo "The application will start in ${STARTUP_SLEEP}s..." && sleep ${STARTUP_SLEEP}
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar "${HOME}/app.jar" "$@"
