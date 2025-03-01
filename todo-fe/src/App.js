import './App.css';
import React, { useState, useEffect } from "react";
import { List, Paper, Container } from "@mui/material";
import Todo from "./Todo/Todo";
import AddTodo from "./Todo/AddTodo"
import { call } from "./service/ApiService";


function App() {
  const [items, setItems] = useState([]);

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
      .then((response) => setItems(response.data));
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

  return (
    <div className="App">
      <Container maxWidth="md">
        <AddTodo addItem={addItem} />
        <div className="TodoList">{todoItems}</div>
      </Container>
    </div>
  );
}

export default App;