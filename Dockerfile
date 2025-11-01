FROM maven:3.9.9-eclipse-temurin-11

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests && \
    curl -L -o webapp-runner.jar https://repo1.maven.org/maven2/com/github/jsimone/webapp-runner/9.0.52.0/webapp-runner-9.0.52.0.jar

EXPOSE 8080

CMD ["sh", "-c", "java -jar webapp-runner.jar --port $PORT target/Back-IGesta.war"]
