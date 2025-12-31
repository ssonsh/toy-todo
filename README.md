# 토이프로젝트 - Todo Application

## 프로젝트 개요
이 토이프로젝트는 간단한 Todo Application을 구현하는 것을 목표로 합니다.
사용자는 할 일 목록을 추가, 수정, 삭제할 수 있으며, 완료된 할 일을 표시할 수 있습니다.

## 프로젝트 목표
- Spring, Kotlin 을 이용한 백엔드 개발 경험 쌓기

## 기술 스택
- **Language**: Kotlin
- **Framework**: Spring Boot 3.4.1
- **Database**: MySQL 8.4
- **Build Tool**: Gradle (Kotlin DSL)
- **JVM**: Java 17

## 프로젝트 구조
이 프로젝트는 **레이어드 아키텍처(Layered Architecture)**를 기반으로 한 멀티모듈 구조로 설계되었습니다.

```
toy-todo/
├── app-api/          # 프레젠테이션 레이어 (Controller)
├── application/      # 애플리케이션 서비스 레이어
├── domain/          # 도메인 레이어 + 인프라스트럭처 레이어
├── common-lib/      # 공통 라이브러리
├── support/         # 모니터링, 로깅 등 지원 기능
└── docker/          # Docker 설정 파일
```

### 모듈별 역할

#### 🌐 app-api (프레젠테이션 레이어)
- **역할**: REST API 엔드포인트 제공
- **주요 구성요소**:
  - `MemberController`: 회원 관련 API
  - `GlobalExceptionHandler`: 전역 예외 처리
  - HTTP 테스트 파일들

#### 🔧 application (애플리케이션 서비스 레이어)
- **역할**: 비즈니스 유스케이스 구현 및 트랜잭션 관리
- **주요 구성요소**:
  - `MemberCommandService`: 회원 생성/수정/삭제
  - `MemberQueryService`: 회원 조회

#### 🏛️ domain (도메인 + 인프라스트럭처 레이어)
- **역할**: 도메인 모델과 데이터 접근 로직
- **주요 구성요소**:
  - `Member`: 회원 도메인 엔티티
  - `MemberRepository`: 회원 저장소 인터페이스
  - `MemberJpaEntity`: JPA 엔티티
  - `MemberRepositoryAdapter`: 저장소 구현체

#### 📚 common-lib
- **역할**: 프로젝트 전반에서 사용되는 공통 기능

#### 🔍 support
- **역할**: 모니터링, 로깅 등 운영 지원 기능

## 실행 방법

### 1. 데이터베이스 실행
```bash
cd docker
docker-compose up -d
```

### 2. 애플리케이션 실행
```bash
./gradlew :app-api:bootRun
```

### 3. API 테스트
- HTTP 파일 위치: `app-api/http/members/member.http`
- 서버 주소: http://localhost:8080

## API 엔드포인트

### 회원 관리
- `POST /api/v1/members` - 회원 생성
- `GET /api/v1/members/{id}` - 회원 조회 






