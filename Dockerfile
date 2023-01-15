FROM openJDK:8
EXPOSE 8080
ADD target/gitactions.jar gitactions.jar
ENTRYPOINT ["java", "-jar","/gitactions.jar"]