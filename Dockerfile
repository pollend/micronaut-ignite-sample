FROM openjdk:14-alpine
COPY build/libs/micronaut-ignite-sample-*-all.jar demo.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "demo.jar"]
