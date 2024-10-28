# Use a imagem do OpenJDK 21
FROM openjdk:21-jdk-slim AS build

# Instale o Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e o diretório src para o contêiner
COPY pom.xml .
COPY src ./src

# Execute o Maven para compilar a aplicação
RUN mvn clean package -DskipTests

# Use a mesma imagem do OpenJDK 21 para rodar a aplicação
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR gerado do estágio de build
COPY --from=build /app/target/*.jar app.jar

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
