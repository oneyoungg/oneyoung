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
