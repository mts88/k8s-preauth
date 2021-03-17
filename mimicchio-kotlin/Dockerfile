FROM openjdk:8-jre

COPY /target/*.jar /mimicchio.jar

RUN echo "Europe/Rome" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata

CMD [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "-Xms256m" , "-Xmx512m" , "mimicchio.jar"]
