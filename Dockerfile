FROM eclipse-temurin:25-jdk

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Copy source code
WORKDIR /app
COPY . .

# Build JAR from source
RUN mvn -B package -DskipTests

# Run Spring Boot
EXPOSE 8080

CMD sh -c "java -jar /app/target/*.jar"
