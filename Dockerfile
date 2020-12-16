FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-cp","app:app/lib/*", "com.zengc.ZCApplication"]