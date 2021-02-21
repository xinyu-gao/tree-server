# 该镜像需要依赖的基础镜像
FROM openjdk:11
# 将当前目录下的 jar 包复制到 docker 容器的/目录下
ADD tree-0.0.1-SNAPSHOT.jar /tree-0.0.1-SNAPSHOT.jar
# 运行过程中创建一个 tree.jar 文件
RUN bash -c 'touch /tree-0.0.1-SNAPSHOT.jar'
# 声明服务运行端口
EXPOSE 2399
# 指定 docker 容器启动时运行 jar 包以及运行环境
ENTRYPOINT ["java", "-jar", "/tree.jar", "--spring.profiles.active=prod"]
# 指定维护者的名字
MAINTAINER xinyu