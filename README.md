删除原有容器：
```
docker cotainer rm -f tree-server
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