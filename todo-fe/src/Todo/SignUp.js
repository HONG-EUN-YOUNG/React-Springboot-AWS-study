import React from "react";
import { Container, Grid, Typography, TextField, Button } from "@mui/material";
import { signup } from "../service/ApiService";
import { Link } from "react-router-dom";

function SignUp() {
  const handleSubmit = (event) => {
    event.preventDefault();

    // 오브젝트에서 form에 저장된 데이터를 맵의 형태로 변경
    const data = new FormData(event.target);
    const username = data.get("username");
    const password = data.get("password");

    signup({ username: username, password: password })
      .then((response) => {
        // 회원가입 성공하면 login 페이지로 리디렉트
        window.location.href = "/login";
      }
    );
  };

  return (
    <Container component="main" maxWidth="xs" style={{ marginTop: "8%" }}>
      <form noValidate onSubmit={handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <Typography component="h1" variant="h5">
              회원가입
            </Typography>
          </Grid>

          <Grid item xs={12}>
            <TextField
              autoComplete="fname"
              name="username"
              variant="outlined"
              required
              fullWidth
              id="username"
              label="아이디"
              autoFocus
            />
          </Grid>
          
          <Grid item xs={12}>
            <TextField
              variant="outlined"
              required
              fullWidth
              name="password"
              label="비밀번호"
              type="password"
              id="password"
              autoComplete="current-password"
            />
          </Grid>

          <Grid item xs={12}>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
            >
              회원가입
            </Button>
          </Grid>
        </Grid>

        <Grid container justify="flex-end">
          <Grid item>
            <Link to="/login" variant="body2">
              이미 계정이 있습니까? 로그인하세요.
            </Link>
          </Grid>
        </Grid>
      </form>
    </Container>
  );
};

export default SignUp;