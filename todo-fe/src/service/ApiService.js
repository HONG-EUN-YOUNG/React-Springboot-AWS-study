import { API_BASE_URL } from "../api-config";

export function call(api, method, request) {
  let headers = new Headers({
    "Content-Type": "application/json",
  });

  // 헤더에 액세스 토큰 추가
  const accessToken = localStorage.getItem("ACCESS_TOKEN");

  if (accessToken && accessToken !== null) {
    headers.append("Authorization", "Bearer " + accessToken);
  }

  let options = {
    headers: headers,
    url: API_BASE_URL + api,
    method: method,
  };

  if (request) {
    // GET method
    options.body = JSON.stringify(request);
  }

  return fetch(options.url, options)
    .then((response) => {
      if (response.status === 200) {
        return response.json();
      } else if (response.status === 403) {
        window.location.href = "/login"; // 로그인 페이지로 redirect
      }else {
        new Error(response);
      }
    })
    .catch((error) => {
      console.log("http error");
      console.log(error);
    });
}

// 로그인
export function signin(userDTO) {
  return call("/auth/signin", "POST", userDTO)
  .then((response) => {
    if (response.token) {
      // 로컬 스토리지에 토큰 저장
      localStorage.setItem("ACCESS_TOKEN", response.token);
      // token 있으면 todo화면으로 리디렉트
      window.location.href = "/";
    }
  });
}

// 로그아웃
export function signout() {
  localStorage.setItem("ACCESS_TOKEN", null);
  window.location.href = "/login";
}