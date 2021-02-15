FROM openjdk:7
COPY src WorldNavigator
WORKDIR WorldNavigator

RUN javac -d bin WorldNavigator.GameApplet.java

CMD ["java", "WorldNavigator.GameApplet"]



