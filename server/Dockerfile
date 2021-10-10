FROM centos:7


#3、在构建镜像时，指定镜像的工作目录，之后的命令都是基于此工作目录，如果不存在，则会创建目录
WORKDIR /usr/local/docker

#4、一个复制命令，把jdk安装文件复制到镜像中，语法 ADD SRC DEST ,ADD命令具有自动解压功能
ADD jdk-8u202-linux-x64.tar.gz /usr/local/docker
# 复制运行包
ADD target/demo1-0.0.1-SNAPSHOT.jar /home/demo.jar

#5、配置环境变量，此处目录为tar.gz包解压后的名称，需提前解压知晓：
ENV JAVA_HOME=/usr/local/docker/jdk1.8.0_202
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH=$JAVA_HOME/bin:$PATH

EXPOSE 80
#6、设置启动命令
CMD ["java","-jar","/home/demo.jar"]