## Photogram

---
#### 환경설정

##### DB연결
도커를 이용하여 DB설정을 진행 MariaDB 이미지를 자동으로 설치하면서 DATABASE=photogram 을 생성하고 seung 계정도 생성   

    docker run -p 3306:3306 --name photogram -e MARIADB_ROOT_PASSWORD=1234 -e MARIADB_DATABASE=photogram -e MARIADB_USER=seung -e MARIADB_PASSWORD=1234 -d mariadb

#### 인텔리제이 코드템플릿
1. Lombok
   - `import lombok.AllArgsConstructor;
   import lombok.Builder;
   import lombok.Data;
   import lombok.NoArgsConstructor;
    
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   @Builder`

2. Logger
   - `private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger( $CLASSNAME$.class );`

#### 콘솔창 생상 이쁘게 꾸미기

- application.properties

```spring.output.ansi.enabled=ALWAYS```  

- application.yml
```
spring:
   output:
      ansi:
         enabled: ALWAYS
```

---
#### csrf
csrf 는 요청시마다 임의의 토큰값을 같이 전송하여 정상적인 사용자의 요청인지 확인하는 토큰이다.
시큐리티 사용시 자동으로 csrf 토큰을 전송하게 된다.

만약 JWT 같은 토큰 방식을 사용한다면 csrf토큰은 사용 하지 않아도 좋다.

---

#### Domain & DTO
DTO (Data Transfer Object) 를 사용하는 이유는   
도메인을 사용하게 되면 필요하지 않은 데이터까지 노출이 될 수 있는 우려가 있다.  
dto 를 사용하면 domain 을 캡슐화 할 수 있다.
---
### 전처리 & 후처리
전처리란 서버로 이동전에 Validation 등을 이용한 처리  
후처리란 서버-DB를 지나서 ExceptionHandler등을 이용하여 처리  

이러한 전처리&후처리 는 공통기능으로 `AOP` 라고 명칭한다.