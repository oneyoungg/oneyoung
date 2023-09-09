mkdir -p /home/data/mysql/master
mkdir -p /home/data/mysql/slave
touch /home/data/mysql/master/my.cnf
touch /home/data/mysql/slave/my.cnf

docker run \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=oneyoung123 \
    -v /home/data/mysql/master/data:/var/lib/mysql:rw \
    -v /etc/localtime:/etc/localtime:ro \
    --name mysql-master \
    --restart=always \
    -d mysql:8.0

docker run \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=oneyoung123 \
    -v /home/data/mysql/data:/var/lib/mysql:rw \
    -v /home/data/mysql/conf:/etc/mysql/conf.d \
    -v /home/data/mysql/my.cnf:/etc/mysql/my.cnf \
    -v /etc/localtime:/etc/localtime:ro \
    -v /home/data/mysql/mysql-files:/var/lib/mysql-files:rw \
    -v /home/data/mysql/logs:/logs:rw \
    --name mysql \
    --restart=always \
    -d mysql:8.0


docker run -d  --name rabbit1 \
--hostname rabbit_host1 \
-p 15672:15672 \
-p 5672:5672 \
-e RABBITMQ_NODENAME=rabbitnode1 \
-e RABBITMQ_ERLANG_COOKIE='cookie' \
 -e RABBITMQ_DEFAULT_USER=admin \
  -e RABBITMQ_DEFAULT_PASS=admin \
--privileged=true \
 rabbitmq:3.8.15-management

 docker run -d  --name rabbit2 \
 --hostname rabbit_host2 \
 --link rabbit1:rabbit_host1 \
 -p 15673:15672 \
 -p 5673:5672 \
 -e RABBITMQ_NODENAME=rabbitnode2 \
 -e RABBITMQ_ERLANG_COOKIE='cookie' \
  -e RABBITMQ_DEFAULT_USER=admin \
   -e RABBITMQ_DEFAULT_PASS=admin \
 --privileged=true \
  rabbitmq:3.8.15-management

 docker run -d  --name rabbit3 \
 --hostname rabbit_host3 \
  --link rabbit2:rabbit_host2 \
 -p 15674:15672 \
 -p 5674:5672 \
 -e RABBITMQ_NODENAME=rabbitnode3 \
 -e RABBITMQ_ERLANG_COOKIE='cookie' \
  -e RABBITMQ_DEFAULT_USER=admin \
   -e RABBITMQ_DEFAULT_PASS=admin \
 --privileged=true \
  rabbitmq:3.8.15-management

