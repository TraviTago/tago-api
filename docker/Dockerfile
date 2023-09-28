FROM openjdk:17-ea-11-jdk-slim AS BUILDER
RUN mkdir /app_source
COPY .. /app_source
WORKDIR /app_source
RUN chmod +x ./gradlew
RUN ./gradlew :api:bootJar

FROM openjdk:17-ea-11-jdk-slim AS RUNNER
RUN mkdir /app
COPY --from=BUILDER /app_source/api/build/libs /app
WORKDIR /app
ENV TZ=Asia/Seoul
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/api-0.0.1-SNAPSHOT.jar"]