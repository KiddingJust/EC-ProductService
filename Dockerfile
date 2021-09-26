FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/productservice-1.0.jar ProductService.jar
ENTRYPOINT ["java","-jar","ProductService.jar"]