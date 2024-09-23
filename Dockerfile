FROM openjdk:21
WORKDIR /app

COPY /target/fiap-lanchonete-1.0.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
