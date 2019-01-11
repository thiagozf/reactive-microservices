#!/bin/sh
chmod +x mvnw
./mvnw clean package -Ddockerfile.skip
./mvnw sonar:sonar -Dsonar.projectKey=thiagozf_reactive-microservices-products -Dsonar.organization=thiagozf-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=$SONAR_TOKEN