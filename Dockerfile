FROM maven:3.6.3-jdk-14

COPY . /usr/src/tasks-java
WORKDIR /usr/src/tasks-java

RUN mvn install

ENTRYPOINT ["mvn", "-X", "-q", "spring-boot:run"]

