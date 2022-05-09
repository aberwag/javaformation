FROM openjdk:8-jdk-alpine
EXPOSE 1097
VOLUME /tmp
COPY target/LoizRmiServer.jar target/LoizRmiServer.jar
ENTRYPOINT ["java", "-Djava.rmi.server.hostname=127.0.0.1", "-jar", "target/LoizRmiServer.jar"]



