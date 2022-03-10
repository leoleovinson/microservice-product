FROM openjdk:17
EXPOSE 8091
ARG JAR_FILE=target/product-microservice.jar
ADD ${JAR_FILE} product-microservice.jar
ENTRYPOINT ["java","-jar","/product-microservice.jar"]