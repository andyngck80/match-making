FROM maven:3.6.1-jdk-8 as build-step
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY pom.xml /usr/src/app
RUN mvn dependency:go-offline -U -V --batch-mode
COPY . ./
RUN mvn clean package -U -V --batch-mode

FROM xqdocker/ubuntu-openjdk:8
VOLUME /tmp
ARG JAR_FILE
COPY --from=build-step /usr/src/app/target/*.jar app.jar
CMD ["java","-jar","/app.jar"]