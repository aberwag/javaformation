FROM openjdk:8-jdk-alpine
EXPOSE 1097
VOLUME /tmp
USER root
COPY target/LoizRmiServer.jar target/LoizRmiServer.jar
COPY java.policy ${JAVA_HOME}/lib/security/java.policy
ENTRYPOINT ["java", "-Djava.rmi.server.hostname=0.0.0.0", "-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.local.only=false", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.rmi.port=1097",  "-Dcom.sun.management.jmxremote.authenticate=false", "-jar", "target/LoizRmiServer.jar"]