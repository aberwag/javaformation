FROM openjdk:8-jdk-alpine
EXPOSE 1099
VOLUME /tmp
COPY target/LoizRmiServer.jar target/LoizRmiServer.jar
ENTRYPOINT ["java", "-jar", "target/LoizRmiServer.jar"]

