FROM openjdk:8
MAINTAINER Pankaj Semwal <pnkjsmwl09@gmail.com>
ADD target/kube-user.jar kube-user.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "kube-user.jar"]