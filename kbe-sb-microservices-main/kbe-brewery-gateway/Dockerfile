FROM eclipse-temurin:11-jre AS builder
WORKDIR application
ADD target/kbe-brewery-gateway-1.0-SNAPSHOT.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:11-jre
LABEL PROJECT_NAME=kbe-brewery-gateway \
      PROJECT=kbe-brewery-gateway

EXPOSE 8080

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]
