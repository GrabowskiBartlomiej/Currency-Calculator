FROM openjdk:8
ADD target/Currency-Converter-0.0.1-SNAPSHOT.jar Currency-Converter-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Currency-Converter-0.0.1-SNAPSHOT.jar"]