import './App.css';
import React, { useState } from "react";
import { List, Paper, Container } from "@mui/material";
import Todo from "./Todo/Todo";
import AddTodo from "./Todo/AddTodo"


function App() {
  const [items, setItems] = useState([
    {
      id: "0",
      title: "Hello World",
      done: true
    },
    {
      id: "1",
      title: "Hello World 2",
      done: false
    }
  ]);

  const addItem = (item) => {
    item.id = "ID-" + items.length;
    item.done = false;

    // setItems로 업데이트하고 새 배열 만들기
    setItems([...items, item]);
    
    console.log("items : ", items);
  };

  let todoItems = items.length > 0 && (
    <Paper style={{ margin: 16 }}>
      <List>
        {items.map((item) => (<Todo item={item} key={item.id} />))}
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