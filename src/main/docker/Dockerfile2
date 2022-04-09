FROM openjdk:8-jdk-alpine

COPY quartz-0.0.1-SNAPSHOT.jar /app.jar
RUN apk --no-cache add curl bash bind-tools
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]