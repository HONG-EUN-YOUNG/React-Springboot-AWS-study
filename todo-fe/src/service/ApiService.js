import { API_BASE_URL } from "../api-config";

export function call(api, method, request) {
  let options = {
    headers: new Headers({
      "Content-Type": "application/json",
    }),
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
        Promise.reject(response);
        throw Error(response);
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
    // token 있으면 todo화면으로 리디렉트
    if (response.token) {
      window.location.href = "/";
    }
  });
}