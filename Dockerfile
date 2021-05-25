FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/*.jar

WORKDIR /myJavaDir

COPY ${JAR_FILE} library-app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","library-app.jar"]