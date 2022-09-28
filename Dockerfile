FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app

COPY build/libs/transportCH-*-SNAPSHOT.war /transportch.war

ENTRYPOINT ["java","-jar","/transportch.war"]