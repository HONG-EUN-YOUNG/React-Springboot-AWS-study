import './App.css';
import React, { useState } from "react";
import { List, Paper, Container } from "@mui/material";
import Todo from "./Todo/Todo";
import AddTodo from "./Todo/AddTodo"


function App() {
  const [items, setItems] = useState([]);

  // Todo 추가
  const addItem = (item) => {
    item.id = "ID-" + items.length;
    item.done = false;

    // setItems로 업데이트하고 새 배열 만들기
    setItems([...items, item]);
    
    console.log("items : ", items);
  };

  // Todo 삭제
  const deleteItem = (item) => {
    // 삭제할 아이템 제외하고 새 배열에 저장
    const newItems = items.filter(e => e.id !== item.id);
    setItems([...newItems]);
  }

  // Todo 수정
  const editItem = () => {
    setItems([...items]);
  }

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