import React, { useState } from "react";
import {
  ListItem, ListItemText, InputBase, Checkbox,
  ListItemSecondaryAction, IconButton
} from "@mui/material";
import DeleteOutlined from "@mui/icons-material/DeleteOutlined";

const Todo = (props) => {
  const [item, setItem] = useState(props.item);

  // Todo 삭제
  const deleteItem = props.deleteItem;
  const deleteEventHandler = () => {
    deleteItem(item);
  }

  //// Todo 수정
  // readOnly 설정
  const [readOnly, setReadOnly] = useState(true);
  const turnOffReadOnly = () => {
    setReadOnly(false);
  }
  const turnOnReadOnly = (e) => {
    if (e.key === "Enter" && readOnly === false) {
      setReadOnly(true);
      editItem(item);
    }
  }

  // title 수정
  const editItem = props.editItem;
  const editEventHandler = (e) => {
    setItem({...item, title: e.target.value});
  };

  // 체크박스 수정
  const checkboxEventHandler = (e) => {
    item.done = e.target.checked;
    editItem(item);
  }

  return (
    <ListItem>
      <Checkbox checked={item.done} onChange={checkboxEventHandler}/>

      <ListItemText>
        <InputBase
          inputProps={{ "aria-label": "naked", readOnly: readOnly }}
          onClick={turnOffReadOnly}
          onKeyDown={turnOnReadOnly}
          onChange={editEventHandler}
          type="text"
          id={item.id}
          name={item.id}
          value={item.title}
          multiline={true}
          fullWidth={true}
        />
      </ListItemText>

      <ListItemSecondaryAction>
        <IconButton aria-label="Delete Todo"
          onClick={deleteEventHandler}>
          <DeleteOutlined />
        </IconButton>
      </ListItemSecondaryAction>
    </ListItem>
  );
};

export default Todo;