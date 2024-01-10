FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY build/libs/TeamViewerTechnical-0.0.1-SNAPSHOT.jar product.jar
ENTRYPOINT ["java","-jar","/product.jar"]