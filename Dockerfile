FROM amazoncorretto:11
LABEL authors="allaslyu"
ARG JAR_FILE=education-project.jar
WORKDIR /opt/education-project
COPY build/libs/*.jar /opt/education-project/$JAR_FILE
RUN mkdir -p /opt/education-project/src/main/resources/animals
ENTRYPOINT ["java", "-jar", "/opt/education-project/education-project.jar"]