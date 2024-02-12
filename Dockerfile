FROM amazoncorretto:11
COPY build/libs/*.jar education-project.jar
ENTRYPOINT ["java", "-jar", "/education-project.jar"]