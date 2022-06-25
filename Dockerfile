FROM maven:3.8.4-openjdk-17

COPY . /usr/src/tasks-java
WORKDIR /usr/src/tasks-java

RUN mvn clean install

ENTRYPOINT ["mvn", "-X", "-q", "spring-boot:run"]

