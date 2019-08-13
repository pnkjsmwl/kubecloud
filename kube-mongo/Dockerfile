FROM openjdk:8
MAINTAINER Pankaj Semwal <pnkjsmwl09@gmail.com>
ADD target/kube-mongo.jar kube-mongo.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "kube-mongo.jar"]