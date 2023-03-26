FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle --no-daemon

FROM openjdk:11
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/telegram-task-manager.jar /app/telegram-task-manager.jar
ENTRYPOINT ["java","-jar","/app/telegram-task-manager.jar"]
