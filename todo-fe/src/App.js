import './App.css';
import React, { useState, useEffect } from "react";
import { List, Paper, Container, Grid,
  Button, AppBar, Toolbar, Typography, } from "@mui/material";
import Todo from "./Todo/Todo";
import AddTodo from "./Todo/AddTodo"
import { call, signout } from "./service/ApiService";


function App() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);

  // Todo 추가
  const addItem = (item) => {
    call("/todo", "POST", item)
      .then((response) => setItems(response.data));
  };

  // Todo 삭제
  const deleteItem = (item) => {
    call("/todo", "DELETE", item)
      .then((response) => setItems(response.data));
  }

  // Todo 수정
  const editItem = (item) => {
    call("/todo", "PUT", item)
      .then((response) => setItems(response.data));
  }

  // Todo API 호출
  useEffect(() => {
    call("/todo", "GET", null)
      .then((response) => {
        setItems(response.data);
        setLoading(false);
    });
  }, []);

  let todoItems = items.length > 0 && (
    <Paper style={{ margin: 16 }}>
      <List>
        {items.map((item) => (
          <Todo item={item} key={item.id}
            deleteItem={deleteItem}
            editItem={editItem} />
        ))}
      </List>
    </Paper>
  );

  // 네비게이션 바
  let navigationBar = (
    <AppBar position="static">
      <Toolbar>
        <Grid justifyContent="space-between" container>
          <Grid item>
            <Typography variant="h6">오늘의 할일</Typography>
          </Grid>

          <Grid item>
            <Button color="inherit" raised onClick={signout}>
              로그아웃
            </Button>
          </Grid>
        </Grid>
      </Toolbar>
    </AppBar>
  );

  // 로딩중 아닐 때 렌더링 할 부분
  let todoListPage = (
    <div>
      {navigationBar}

      <Container maxWidth="md">
        <AddTodo addItem={addItem} />
        <div className="TodoList">{todoItems}</div>
      </Container>
    </div>
  );

  // 로딩중일 때 렌더링 할 부분
  let loadingPage = <h1> 로딩중... </h1>;
  let content = loadingPage;

  if (!loading) {
    content = todoListPage;
  }

  return (
    <div className="App">
      {content}
    </div>
  );
}

export default App;