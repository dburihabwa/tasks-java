FROM maven:3.6.3-jdk-14

COPY . /usr/src/task-java
WORKDIR /usr/src/task-java

RUN mvn clean install
