## 라우팅
url 요청을 처리하는 방식
<br>

### 서버-사이드 라우팅(Server-Side Routing)
<img src="https://github.com/user-attachments/assets/6fb19a7a-fc91-4509-9e3b-e13dd3eb1a56" alt="image" width="500"><br>
- 페이지 이동 시마다 요청을 보내고 서버가 HTML 반환
- 처음 로딩이 빠르지만 페이지 전환 시 전체 페이지 새로 렌더링
- SEO(검색 엔진 최적화)에 유리
<br>

### 클라이언트-사이드 라우팅(Client-Side Routing)
<img src="https://github.com/user-attachments/assets/4ba264ba-b2a0-427d-a618-7889de6d0a58" alt="image" width="500"><br>
- 처음 접속할 때 필요한 모든 리소스를 서버에서 받음
- 이후 자바스크립트가 요청을 해결하고 필요한 데이터만 서버에 요청
- 페이지 전환이 빠름
- 검색 엔진이 자바스크립트를 해석하지 못하면 SEO에 불리
<br>

## 웹 스토리지
- 사용자의 브라우저에 데이터를 key-value형태로 저장
- 쿠키와 비슷하지만 서버로 자동 전송 X

### 세션 스토리지
- 브라우저를 닫으면 데이터 삭제

### 로컬 스토리지
- 브라우저를 닫아도 데이터 유지
- 브라우저를 재시작해도 로그인 상태 유지 가능
- 도메인마다 따로 저장돼서 같은 도메인에서만 접근 가능
<br><br><br>

## 프로젝트
### AppRouter.js
- `<BrowserRouter>` : 브라우저가 관리하는 히스토리를 사용해 브라우저와 리액트 사이의 URL을 동기화
- `<Routes>` : `<Route>`를 관리하고 탐색하는 컴포넌트
- `<Route>` : 실제 경로를 지정해주기 위한 컴포넌트