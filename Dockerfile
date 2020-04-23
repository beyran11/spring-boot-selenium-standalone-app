FROM openjdk:8

COPY target/app-0.0.1.jar app.jar

CMD ["java","-jar","app.jar"]