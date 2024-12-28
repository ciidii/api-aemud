set APP_PROFILE=dev

set DBMS=mysql
set DB_HOST=host.docker.internal
set DB_PORT=3306
set DB_NAME=aemud
set DB_PASSWORD=
set DB_USERNAME=root
set SEV_PORT=8080
set ENV_FILE=.env

docker-compose down
docker-compose build
docker-compose up