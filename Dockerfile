# 使用OpenJDK作为基础镜像
FROM openjdk:21

# 将Spring Boot应用的JAR文件复制到容器中
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# 指定容器启动时执行的命令
CMD ["java", "-jar", "/app.jar"]