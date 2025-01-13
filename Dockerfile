FROM openjdk:17-jdk-slim
COPY . /app
WORKDIR /app
RUN javac FileServerExample.java
CMD ["java", "FileServerExample"]
