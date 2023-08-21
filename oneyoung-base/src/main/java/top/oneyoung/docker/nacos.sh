docker run -d -p 8848:8848  -e NACOS_AUTH_ENABLE=true -e MODE=standalone -e JVM_XMS=128M -e JVM_XMX=128m -e JVM_XMn=128m -e SPRING_DATASOURCE_PLATFORM=mysql -e MYSQL_SERVICE_HOST=host.docker.internal -e MYSQL_SERVICE_PORT=3306 -e MYSQL_SERVICE_USER=root -e MYSQL_SERVICE_PASSWORD=root -e MYSQL_SERVICE_DB_NAME=nacos -e MYSQL_SERVICE_DB_PARAM='useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Shanghai' --restart=always --privileged=true --name nacos nacos/nacos-server:2.0.2

docker run -d --name rabbit_mq -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=123456 -p 15672:15672 -p 5672:5672 rabbitmq:3.8.15-management

docker run -itd --name oneyoung-redis -p6379:6379 redis:6.2.4 --requirepass 123456
