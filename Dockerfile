# Etapa de build com Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/Back-IGesta-1.0-SNAPSHOT.jar ./Back-IGesta.jar

# Usar a porta que o Render fornece
ENV PORT=10000
EXPOSE $PORT

# Start command usando a porta dinâmica
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar Back-IGesta.jar"]
