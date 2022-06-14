# FROM openjdk:8-jdk-alpine
FROM adoptopenjdk/openjdk8
EXPOSE 1097
VOLUME /tmp
USER root
COPY target/LoizRmiServer.jar target/LoizRmiServer.jar
COPY java.policy ${JAVA_HOME}/lib/security/java.policy
COPY java.policy target/java.policy
ENTRYPOINT ["java", "-Djava.security.policy=java.policy", "-Djava.rmi.server.hostname=192.168.6.107", "-jar", "target/LoizRmiServer.jar"]