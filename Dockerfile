FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

# Baixa o webapp-runner antes de copiar o código para usar cache (opcional)
RUN curl -L -o /usr/local/bin/webapp-runner.jar https://repo1.maven.org/maven2/com/github/jsimone/webapp-runner/9.0.52.0/webapp-runner-9.0.52.0.jar

# Copia o código do projeto
COPY . .

# Compila o projeto (package gera o .war)
RUN mvn clean package -DskipTests

EXPOSE 8080

# Executa usando o arquivo baixado
CMD ["sh", "-c", "java -jar /usr/local/bin/webapp-runner.jar --port $PORT target/Back-IGesta.war"]
