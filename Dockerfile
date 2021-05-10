FROM openjdk:8
ADD target/restapi-0.1.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]