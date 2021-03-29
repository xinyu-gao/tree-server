### 1. 环境配置（Windows）

#### Java 下载

Java 11： https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

#### Maven 下载

Maven 3.6.3: https://maven.apache.org/download.cgi

下载 zip 压缩包后解压即可。

需要修改代理，打开 conf/settings.xml 文件，在 `<mirrors>` 标签内添加一下内容：

```xml
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
```

#### IDEA 下载：

下载地址：https://www.jetbrains.com/idea/download/#section=windows

导入项目后，先修改 maven 配置（分别是 maven 目录、settings 文件、依赖下载文件夹）：
![maven.png](md/maven.png)

识别项目为 maven 项目：

1. 左键选中项目文件夹，快捷键 Ctrl + Shift + A；
2. 输入 maven，选择 Add Maven Projects；
3. 选中本项目的 pom.xml 文件夹即可。

![maven2.png](md/maven2.png)

识别成功后，IDEA 右侧会出现 Maven 的标签，单击左键展开，如图：

![maven3.png](md/maven3.png)

点击左上角的 Reload 图标，下载依赖，直到 pom.xml 中没有标红的线则代表成功。
（如果之前不修改 maven 代理，还有 IDEA 中的配置，会下载失败）

双击 package，打包项目，在 target 目录下生成 jar 文件，后续会介绍怎么使用。

#### MySQL

安装：

在 application-*.yml 文件中修改 mysql 的地址、数据库、用户名和密码：

```yml
url: jdbc:mysql://IP地址:3306/数据库名?serverTimezone=GMT%2B8
username: ****
password: ****
```

#### Redis

```yml
  redis:
    # Redis服务器地址
    host: ****
    # Redis数据库索引（默认为0）
    database: 0
    # Redis服务器连接端口
    port: 6379
    # Redis服务器连接密码（默认为空）
    password:
```

#### Minio

```yml
minio:
  # minio 服务器地址
  endpoint: ****
  # minio 服务器端口号
  port: 9090
  # minio 账号名 默认为 minioadmin
  access-key: minioadmin
  # minio 密码
  secret-key: minioadmin
  # 存储桶的名字
  bucket: tree
```

#### 邮箱

```yml
mail:
# 采用 163 的邮箱
host: smtp.163.com
# 邮箱账号
username: ****
# 邮箱授权密码
password: ****
```

### 部署

#### docker 方式部署

docker 安装教程：https://yeasy.gitbook.io/docker_practice/install/ubuntu

将 mvn package 生成的 jar 文件和项目根目录下的 Dockerfile 文件放在一个目录下

如果是更新的话，需要先删除原有容器：
```
docker container rm -f tree-server
```

构建新容器：
```
docker build -t tree/server:0.0.1 .
```

运行新容器：
```
docker run -p 2399:2399 --name tree-server \
 -d tree/server:0.0.1
```