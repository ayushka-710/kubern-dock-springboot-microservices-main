FROM eclipse-temurin:11-jre AS builder
WORKDIR application
ADD target/kbe-brewery-inventory-failover-1.0-SNAPSHOT.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:11-jre
LABEL PROJECT_NAME=kbe-brewery-inventory-failover \
      PROJECT=kbe-brewery-inventory-failover
LABEL "collect_logs_with_filebeat"="true"
LABEL "decode_log_event_to_json_object"="true"

EXPOSE 8080

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]
