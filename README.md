# MiniBoard

> 게시글 작성, 조회, 수정, 삭제를 지원하는 Spring Boot 기반 미니 게시판 프로젝트입니다.

## 1. 프로젝트 소개

MiniBoard는 게시판 CRUD의 기본 흐름을 Spring Boot와 JPA로 구현한 개인 프로젝트입니다. 게시글 엔티티, 요청/응답 DTO, 입력값 검증, 공통 예외 처리, 페이지네이션 목록 조회, 소프트 삭제를 직접 구현했습니다.

정적 프론트엔드 화면도 함께 제공해 브라우저에서 게시글 작성부터 수정, 삭제까지 전체 흐름을 확인할 수 있습니다.

## 2. 주요 기능

- 게시글 작성
- 게시글 단건 조회
- 게시글 목록 조회
- 제목 기준 검색
- Pageable 기반 페이지네이션
- 게시글 수정
- 게시글 소프트 삭제
- Jakarta Validation 기반 입력값 검증
- `NotFoundException`, validation error 공통 응답 처리

## 3. 기술 스택

| 분류 | 기술 |
| --- | --- |
| Language | Java 17 |
| Backend | Spring Boot 4, Spring Web, Spring Data JPA, Validation |
| Database | MySQL |
| Frontend | HTML, JavaScript |
| Build | Gradle |
| Test | JUnit 5, Spring Boot Test |

## 4. 프로젝트 구조

```text
.
├── src/main/java/com/example/miniboard
│   ├── global/error        # 공통 예외 처리
│   ├── post/api            # PostController, PostService
│   ├── post/domain         # Post entity, PostRepository
│   └── post/dto            # 요청/응답 DTO
├── src/main/resources
│   ├── application.yaml
│   └── static/index.html   # 테스트용 단일 페이지 화면
└── build.gradle
```

## 5. 실행 방법

### 사전 준비

- Java 17
- MySQL

### DB 설정

`src/main/resources/application.yaml`은 `application-secret.yml`을 import합니다. 로컬 실행 전 아래 파일을 생성합니다.

```yaml
# src/main/resources/application-secret.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/miniboard?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: root
    password: your-password
    driver-class-name: com.mysql.cj.jdbc.Driver
```

### 애플리케이션 실행

```bash
./gradlew bootRun
```

브라우저에서 다음 주소로 접속합니다.

```text
http://localhost:8080
```

## 6. API 명세

| Method | URI | 설명 |
| --- | --- | --- |
| POST | `/posts` | 게시글 작성 |
| GET | `/posts` | 게시글 목록 조회, 검색, 페이지네이션 |
| GET | `/posts/{id}` | 게시글 단건 조회 |
| PUT | `/posts/{id}` | 게시글 수정 |
| DELETE | `/posts/{id}` | 게시글 소프트 삭제 |

목록 조회는 `q`, `page`, `size`, `sort` 파라미터를 사용할 수 있습니다.

## 7. 테스트 및 배포

### 테스트

```bash
./gradlew test
```

현재 별도 배포 설정은 없으며, 로컬 실행과 API 흐름 학습에 초점을 둔 프로젝트입니다.

## 8. 학습 포인트

- 엔티티와 요청/응답 DTO를 분리해 API 응답 구조를 명확히 했습니다.
- `@PrePersist`, `@PreUpdate`를 사용해 생성/수정 시간을 엔티티 생명주기 안에서 관리했습니다.
- 실제 삭제 대신 `deleted` 플래그를 사용하는 소프트 삭제를 구현했습니다.
- 공통 예외 처리로 404, validation error, 서버 에러 응답을 분리했습니다.

## 9. 개선할 점

- 게시글 작성자 인증/인가 추가
- 테스트 코드 보강
- Swagger/OpenAPI 문서 추가
- DB 실행 환경을 Docker Compose로 제공
