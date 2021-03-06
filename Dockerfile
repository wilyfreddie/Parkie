FROM adoptopenjdk/openjdk11
ADD target/Parkie-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]