## Basic 인증
- 모든 HTTP 요청에 아이디와 비밀번호 함께 전송
- 최초 로그인 후 HTTP 요청 헤더 Authorization에 아이디, 비밀번호를 Base64로 인코딩한 문자열 보냄
- 아이디, 비밀번호를 노출해서 HTTP와 사용하기에 취약 -> HTTPS와 사용
- 모든 요청이 일종의 로그인 요청이라 로그아웃 기능 X
- 계정 정보가 있는 저장 장소에 과부하 발생 가능
![alt text](20250228_175846.png)
<br>

## 토큰 기반 인증
- 최초 로그인 시 서버가 만드는 사용자 구별용 토큰 발급
- 헤더에 `Authorization: Bearer <TOKEN>` 형태로 토큰 전송
- 사용자의 인가 정보나 유효기간을 정해 관리 가능
- 디바이스마다 다른 토큰을 생성하고 유효기간을 다르게 정하거나 임의로 로그아웃 가능
![alt text](20250228_183136.png)
<br>

## JSON Web Token(JWT)
- 전자 서명된 토큰을 이용해 인증
- {header}.{payload}.{signature}로 구성
  - Header: 해싱 알고리즘 정보
  - Payload: 사용자 정보, 만료 시간 등
  - Signature: {header}.{payload}와 시크릿 키를 사용해 생성된 해시값
- 서버가 헤더와 페이로드를 생성한 후 전자 서명
![alt text](20250301_002732.png)

## 서블릿 필터
- 서블릿 실행 전 특정 작업을 수행하는 클래스
- 디스패쳐 서블릿이 실행되기 전에 항상 실행
- 구현된 로직에 따라 원하지 않는 HTTP 요청을 걸러냄
- 여러 필터가 FilterChain에 등록되어 순서대로 실행
- 주로 인증, 보안 검사, 로깅 등에 사용

- - -
## 프로젝트
### UserEntity.java
- password에 null 허용 -> SSO로 로그인하기 때문
- UserController에서 password 존재 확인

### TokenProvider.java
- 유저 정보를 받아 JWT 생성