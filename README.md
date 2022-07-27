## Photogram

---
#### 환경설정

##### DB연결
    docker run -p 3306:3306 --name photogram -e MARIADB_ROOT_PASSWORD=1234 -e MARIADB_DATABASE=photogram -e MARIADB_USER=seung -e MARIADB_PASSWORD=1234 -d mariadb

---
#### csrf
csrf 는 요청시마다 임의의 토큰값을 같이 전송하여 정상적인 사용자의 요청인지 확인하는 토큰이다.
시큐리티 사용시 자동으로 csrf 토큰을 전송하게 된다.

만약 JWT 같은 토큰 방식을 사용한다면 csrf토큰은 사용 하지 않아도 좋다.

---
