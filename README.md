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


CMRespDto 를 사용하여 공통응답 DTO 사용

        CMrespDto, Script 비교

        1. 클라이언트에게 응답할 때는 Script 가 좋음
        2. Ajax 통신 - CMRespDto
        3. Android 통신 - CMRespDto

---
### 예외처리 (Exception)

스프링 부트의 예외 처리 방식 
   - @ControllerAdvice 를 통한 모든 Controller 에서 발생할 수 있는 예외 처리
   - @ExceptionHandler 를 통한 특정 Controller 의 예외처리  

@ControllerAdvice 로 모든 컨트롤러에서 발생할 예외를 정의하고,  
@ExceptionHandler 를 통해 발생하는 예외 마다 처리할 메소드를 정의

> ##### 예외 클래스
> 모든 예외 클래스는 Throwable 클래스를 상속 받고 있음  
Exception 은 수많은 자식 클래스가 있음  
`RuntimeException` 은 Unchecked Exception 이며, 그외 Exception 은 Checked Exception 이다.

###### @ControllerAdvice, @RestControllerAdvice
@Controller 나 @RestController 에서 발생하는 예외를 한 곳에서 관리하고 처리할 수 있게 하는 어노테이션  
설정을 통해 범위 지정이 가능하며, Default 값으로 모든 Controller 에 대해 예외 처리를 관리함
- @RestControllerAdvice(basePackages = "com.**.test")와 같이 패키지 범위를 지정하여 설정할 수 있다, 

###### @ExceptionHandler
예외 처리 상황이 발생하면 해당 Handler 로 처리하겠다고 명시하는 어노테이션  
어노테이션 뒤에 괄호를 붙여 어떤 ExceptionClass 를 처리할지 설정할 수 있음
- @ExceptionHandler(00Exception.class)  

Exception.class 는 최상위 클래스로 하위 세부 예외 처리 클래스로 설정한 핸들러가 존재하면,  
그 핸들러가 우선 처리하게 되며, 처리 되지 못하는 예외처리에 대해 ExceptionClass 에서 핸들링 한다.  

전역 설정(@ControllerAdvice) 보다 지역 설정(Controller) 으로 정의한 Handler 가 우선순위를 가진다.

---

#### 다운캐스팅 & 업캐스팅
캐스팅이란 `타입을 변환하는 것` 을 말하며 형변환 이라고도 한다. 자바의 상속관계에 있는 부모 자식 클래스 간에는 서로간의 형변환이 가능하다.
- **업캐스팅(Upcasting)**  
  자식 클래스의 객체가 부모 클래스 타입으로 형변환 되는 것을 말한다.
- **다운캐스팅(Downcasting)**


---
#### 시큐리티 태그라이브러리
JSP 같은 템플릿 엔진을 사용하는 경우에만 사용
```
<sec:authorize access = "isAuthenticated()" >
    <sec:authentication property = "principal" var="principal" />
</sec:authorize>
```
공통으로 사용되는 header 페이지 내부에 사용하여 `principal.user` 을 사용하여 정보를 간편하게 가져올 수 있다.


---

#### 연관관계

- 연관관계 FK 는 Many 가 가진다
- N : N 의 관계는 중간테이블이 생긴다.