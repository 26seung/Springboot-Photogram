## Photogram

---
#### 환경설정

##### DB연결
    docker run -p 3306:3306 --name photogram -e MARIADB_ROOT_PASSWORD=1234 -e MARIADB_DATABASE=photogram -e MARIADB_USER=seung -e MARIADB_PASSWORD=1234 -d mariadb

