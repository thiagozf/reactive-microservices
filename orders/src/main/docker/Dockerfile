FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    STARTUP_SLEEP=0 \
    JAVA_OPTS=""

# Add a 'topics' user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh microservices
WORKDIR /home/microservices

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown microservices:microservices entrypoint.sh
USER microservices

ADD *.jar app.jar

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080

