FROM maven:3.6.3-jdk-14

COPY . /usr/src/tasks-java
WORKDIR /usr/src/tasks-java

RUN mvn compile

#ENTRYPOINT ["cd", "/usr/src/tasks-java", "&&", "mvn", "-X", "-q", "spring-boot:run"]
ENTRYPOINT ["mvn", "-X", "-q", "spring-boot:run"]

