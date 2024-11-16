FROM openjdk:17-alpine AS builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /app/target/inix-0.0.1-SNAPSHOT.jar .

EXPOSE 8008

ENTRYPOINT ["java","-jar","inix-0.0.1-SNAPSHOT.jar"]