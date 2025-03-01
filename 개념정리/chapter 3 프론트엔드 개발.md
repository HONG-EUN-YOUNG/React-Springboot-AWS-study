**Node.js : 22.13.1 LTS**

### Node.js
- 자바스크립트를 브라우저 밖에서 실행할 수 있게 하는 자바스크립트 런타임 환경
- 구글 크롬의 V8엔진을 사용해 자바스크립트 실행
- 클라이언트를 벗어나 서버 개발에 활용 가능
<br>

### NPM(Node Package Manager)
- node.js 패키지 관리 시스템
<br>

### 프론트엔드 애플리케이션 생성
1. 메인 폴더에서 바로 `npx create-react-app todo-fe` (대문자 사용불가)
2. React 버전 충동 발생
3. `cd todo-fe`
4. `npm install react@18 react-dom@18`
5. `npm i web-vitals`
6. `npm start` 정상적으로 실행되는지 확인
<br>

### 브라우저 작동 원리
1. 브라우저 -> 서버 요청
2. 서버 -> 브라우저 html 반환
3. 파싱 : 렌더링을 하기 위한 전처리 단계
   1. HTML을 트리 자료구조 형태인 DOM 트리로 변환
   2. image, css, script 등 리소스 다운로드 (css는 CSSOMCSS 트리로 변환)
   3. 다운로드한 자바스크립트를 인터프리트, 컴파일, 파싱, 실행
4. 렌더링
   1. DOM 트리(내용) + CSSOM 트리(디자인) = 렌더 트리
   2. 트리의 노드가 브라우저 어디에 배치될지 레이아웃 정하기
   3. 브라우저 스크린에 렌더트리 노드 그리기
<br>

### SPA(Single Page Application)
- 최초 웹 페이지 로딩 후, 새로고침 없이 동적으로 화면을 갱신하는 애플리케이션
- 서버에 새 HTML을 요청하지 않고 자바스크립트가 동적으로 HTML을 재구성
<br>

### CORS(Cross-Origin Resource Sharing)
- 브라우저에서 출처(origin)가 다른 서버의 리소스를 요청할 때 허용 여부 결정하는 보안 정책
- 프로토콜 + 도메인 + 포트번호가 동일하면 같은 출처로 간주
- 해결방법(백엔드)
  - `@CrossOrigin` : 특정 컨트롤러에서만 허용
  - `WebMvcConfigurer` : 전체 API에 대해 허용

**CORS 에러 메시지**
```
Access to fetch at 'http://localhost:8080/todo' from origin 'http://localhost:3000' has been blocked by CORS policy: Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource. If an opaque response serves your needs, set the request's mode to 'no-cors' to fetch the resource with CORS disabled.
```
<br>

- - - - -

## Hook
- 리액트가 제공하는 내장 함수로 함수형 컴포넌트에서 상태와 기능을 사용할 수 있도록 하는 함수

### useState(초기값)
- 함수형 컴포넌트에서 상태를 가질 수 있게 하는 Hook
- 현재 상태 값과 상태를 변경하는 함수 반환
```react
const [count, setCount] = useState(0);  // 초기값
setCount(count + 1); // 상태 변경
```

### useEffect(콜백함수, [])
- 컴포넌트가 렌더링될 때 특정 코드를 실행하는 Hook
- API 요청, DOM 조작, 타이머 설정 같은 비동기 작업에 사용
- 빈 배열이면 처음에만 실행되고 배열 값이 변경될 때마다 콜백함수 실행 
```react
useEffect(() => {
  console.log("컴포넌트 등장"); 

  return () => {
    console.log("컴포넌트 사라짐"); 
  };
}, []);
```
<br>

## fetch
- API 서버와 http 요청/응답을 주고받는 **비동기** 메서드
- Promise 객체 반환
```js
fetch(url, options)
  .then(response => {
    // 성공 시 실행
  })
  .catch(e => {
      // 네트워크 오류 및 예외처리
  })
```

## Promise
- 비동기 작업 결과 상태와 값을 처리하는 객체
- 상태 : Pending(대기), Fulfilled(성공), Rejected(실패)

- - - - -

## 프로젝트 관련
### App.js 클래스 버전
```javascript
import React from 'react';
import logo from './logo.svg';
import './App.css';

class App extends React.Component {
    render() {
        return (
        <div className="App">
            ...
        </div>
        );
    }
}

export default App;
```

### AddItem 핸들러
- onInputChange : 키보드를 누를 때마다 실행
- onButtonClick : + 버튼 클릭
- enterKeyEventHandler : enter 키

### App.js > addItem에서 setItems로 새 배열 만드는 이유
> 리액트는 레퍼런스를 기준으로 재렌더링하는데 배열 레퍼런스는 값을 추가해도 변화 X  
> 배열에 변화가 없다 생각해서 렌더링 X  
> -> 배열에 추가할 때마다 새 배열을 만들어서 문제 해결
<br>