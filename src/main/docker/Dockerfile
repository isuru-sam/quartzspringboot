FROM frolvlad/alpine-oraclejre8:slim
VOLUME /tmp
ADD quartz-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]