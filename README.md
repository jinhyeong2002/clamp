# Clamp

Spring Boot 기반의 간단한 사용자 관리 API 서버입니다.

PostgreSQL 데이터베이스와 연동하여 사용자 정보를 생성, 조회, 수정, 삭제할 수 있으며, `/health` 엔드포인트를 통해 서버 상태를 확인할 수 있습니다.

## 기술 스택

- Java 17
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- Spring Boot Actuator
- PostgreSQL
- Gradle
- Lombok

## 프로젝트 구조

```text
.
├── src/
│   ├── main/
│   │   ├── java/com/example/cltmp/
│   │   │   ├── CltmpApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── HealthCheckController.java
│   │   │   │   └── UserController.java
│   │   │   ├── model/
│   │   │   │   └── User.java
│   │   │   └── repository/
│   │   │       └── UserRepository.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── application.properties
│   └── test/
│       └── java/com/example/cltmp/
│           └── CltmpApplicationTests.java
├── build.gradle
├── settings.gradle
├── compose.yaml
├── gradlew
└── gradlew.bat
주요 기능
사용자 생성
전체 사용자 조회
단일 사용자 조회
사용자 정보 수정
사용자 삭제
서버 헬스 체크
PostgreSQL 연동
JPA 기반 데이터 관리
환경 변수
애플리케이션 실행을 위해 아래 환경 변수가 필요합니다.
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/clamp
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=password
PORT=8080
REGION_NAME=local
INSTANCE_NAME=local
실행 방법
1. 저장소 클론
git clone https://github.com/jinhyeong2002/clamp.git
cd clamp
2. 환경 변수 설정
macOS/Linux 기준:
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/clamp
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=password
export PORT=8080
export REGION_NAME=local
export INSTANCE_NAME=local
3. 애플리케이션 실행
./gradlew bootRun
서버는 기본적으로 아래 주소에서 실행됩니다.
http://localhost:8080
빌드
./gradlew build
빌드 결과물은 build/libs/app.jar로 생성됩니다.
테스트
./gradlew test
API 명세
Health Check
GET /health
응답 예시:
{
  "status": "OK",
  "region": "local",
  "instance": "local"
}
사용자 생성
POST /users
Content-Type: application/json
요청 예시:
{
  "name": "홍길동",
  "email": "hong@example.com"
}
전체 사용자 조회
GET /users
단일 사용자 조회
GET /users/{id}
사용자 수정
PUT /users/{id}
Content-Type: application/json
요청 예시:
{
  "name": "김철수",
  "email": "kim@example.com"
}
사용자 삭제
DELETE /users/{id}
User 모델
필드	타입	설명
id	Long	사용자 고유 ID
name	String	사용자 이름
email	String	사용자 이메일


데이터베이스 설정
application.yml에서 PostgreSQL 연결 정보를 환경 변수로 주입받습니다.
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
JPA 설정은 다음과 같습니다.
spring:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
ddl-auto: update 설정으로 애플리케이션 실행 시 엔티티 기준으로 테이블 구조가 자동 반영됩니다.
요약
Clamp는 Spring Boot와 PostgreSQL을 사용한 기본 CRUD API 서버입니다.
사용자 정보를 관리하는 /users API와 서버 상태를 확인하는 /health API를 제공하며, Spring Data JPA를 통해 데이터베이스와 연동됩니다.
