FROM maven:3-jdk-11
WORKDIR /freshfoods
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run