- contenu du docker file 
FROM openjdk:8-jdk-alpine
EXPOSE 1097
VOLUME /tmp
USER root
COPY target/LoizRmiServer.jar target/LoizRmiServer.jar
COPY java.policy ${JAVA_HOME}/lib/security/java.policy
ENTRYPOINT ["java", "-Djava.rmi.server.hostname=0.0.0.0", "-jar", "target/LoizRmiServer.jar"]



- commande docker

mvn package && docker build -t loiz_rmi_server . && docker run -it  -p 1097:1097 loiz_rmi_server