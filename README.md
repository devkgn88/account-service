# 🔐 account-service
사용자 인증 및 권한 관리를 담당하는 마이크로서비스

## 🧩 개요
account-service는 사용자 인증, 권한 부여, JWT 토큰 발급 및 갱신을 담당하는 마이크로서비스입니다. 
Spring Security와 JWT를 활용하여 안전한 인증 메커니즘을 구현하였습니다.

## 🛠 기술 스택
1. Spring Boot: 애플리케이션 프레임워크
2. Spring Security: 인증 및 권한 부여
3. JWT: 토큰 기반 인증
4. Gradle: 빌드 도구
5. Spring Data(JPA) : 회원 정보 조회

## 🚀 주요 기능
![Image](https://github.com/user-attachments/assets/38b24951-ddfc-4366-9545-5c22b6afc328)
* 사용자 등록 및 로그인
* JWT 액세스 토큰 및 리프레시 토큰 발급
* 토큰 유효성 검사 및 갱신
* 역할 기반 접근 제어

## 📦 실행 방법
```
git clone https://github.com/devkgn88/account-service.git
cd account-service
./gradlew bootRun
```
## 📧 문의
이메일: devkgn88@gmail.com
