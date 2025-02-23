import React, { useState } from "react";
import { Button, Grid, TextField } from "@mui/material";

const AddTodo = (props) => {
  // 사용자 입력 저장 임시 오브젝트
  const [item, setItem] = useState({title: ""});

  // 입력 이벤트
  const onInputChange = (e) => {
    setItem({title: e.target.value});

    console.log(item);
  };

  // + 클릭 이벤트
  const addItem = props.addItem;
  const onButtonClick = () => {
    addItem(item);
    setItem({title: ""});
  }

  // enter 키 이벤트
  const enterKeyEventHandler = (e) => {
    if (e.key === 'Enter') {
      onButtonClick();
    }
  };

  return (
    <Grid container style={{ marginTop: 20 }}>
      <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
        <TextField placeholder="Add Todo here" fullWidth
          onChange={onInputChange} onKeyDown={enterKeyEventHandler}
          value={item.title} />
      </Grid>

      <Grid xs={1} md={1} item >
        <Button fullWidth style={{ height: '100%' }} color="secondary" variant="outlined"
          onClick={onButtonClick}>
          +
        </Button>
      </Grid>
    </Grid>
  );
}

export default AddTodo;