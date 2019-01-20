FROM java:8

MAINTAINER James Wang<jameswangx95@gmail.com>

ADD target/wxblog-0.0.1-SNAPSHOT.jar   /usr/app.jar

RUN bash -c 'touch /usr/app.jar'

RUN echo "Asia/Shanghai" > /etc/timezone && dpkg-reconfigure -f nointeractive tzdata

expose 80 443

CMD ["java","-jar","/usr/app.jar"]