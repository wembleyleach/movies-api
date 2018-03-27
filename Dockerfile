FROM openjdk:8-jdk-alpine

RUN apk upgrade --update && apk add --no-cache --virtual .build-deps

ADD target/demo-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-Xmx1g", "-jar", "-Djava.security.egd=file:/dev/./urandom", "/app.jar"]

VOLUME /tmp
