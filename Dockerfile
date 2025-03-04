FROM eclipse-temurin:21-jdk
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copia todo o código-fonte para dentro do container
COPY src ./src

RUN mvn clean package -DskipTests

# Define a variável de ambiente para o JAR gerado
ARG JAR_FILE=target/listacompras-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "target/listacompras-0.0.1-SNAPSHOT.jar"]
