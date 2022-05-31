# FROM openjdk:8-jdk-alpine
FROM adoptopenjdk/openjdk8
EXPOSE 1097
VOLUME /tmp
USER root
COPY target/LoizRmiServer.jar target/LoizRmiServer.jar
COPY java.policy ${JAVA_HOME}/lib/security/java.policy
ENTRYPOINT ["java", "-Djava.rmi.server.hostname=hotermi", "-jar", "target/LoizRmiServer.jar"]