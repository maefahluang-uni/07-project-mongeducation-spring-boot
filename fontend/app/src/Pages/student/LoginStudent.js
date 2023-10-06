import React, { useState, useContext } from "react";
import PopupLogin from "../../Components/PopupLogin";
import { userContext } from "../../App";
import axios from "axios";
import { Link } from "react-router-dom";

function LoginStudent() {
  // set state
  const [userName, setUserName] = useState("");
  const [passWord, setPassWord] = useState("");
  const { dataContent, setDataContent } = useContext(userContext);

  // set event
  const handleSubmit = (e) => {
    e.preventDefault();
    const data = {
      username: userName,
      password: passWord,
    };

    // กำหนด URL ที่จะทำการ POST ข้อมูล
    const url = "http://localhost:8000/students/login";

    // กำหนด Headers
    const headers = {
      "Content-Type": "application/json",
    };

    // กำหนดตัวเลือกให้ Axios
    const axiosOptions = {
      method: "post",
      url: url,
      headers: headers,
      data: data,
    };

    // ใช้ Axios สำหรับการ POST ข้อมูล
    axios(axiosOptions)
      .then((response) => {
        // Check if dataContent is not null and valid before updating it
        if (dataContent && typeof dataContent === "object") {
          // Update dataContent
          const updatedDataContent = {
            id:response.data[0].id,
            firstName: response.data[0].firstName,
            lastName: response.data[0].lastName,
            status:"Student"
          };
          setDataContent(updatedDataContent);

          // Update localStorage
          localStorage.setItem(
            "dataContent",
            JSON.stringify(updatedDataContent)
          );
          console.log(updatedDataContent);
        }

      })
      .catch((error) => {
        console.error("เข้าสู่ระบบไม่สำเร็จ", error); // แสดงข้อผิดพลาด (ถ้ามี)
      });
  };

  // return function
  return (
    <div className="Card">
      <form onSubmit={handleSubmit}>
        <label htmlFor="username">Username:</label>
        <input
          type="text"
          id="userName"
          name="userName"
          value={userName}
          onChange={(e) => setUserName(e.target.value)}
        ></input>
        <label htmlFor="password">Password:</label>
        <input
          type="text"
          id="passWord"
          name="passWord"
          value={passWord}
          onChange={(e) => setPassWord(e.target.value)}
        ></input>
        <button type="submit">Login</button>
      </form>
      <Link to={"/RegisterStudent"}>Register</Link>
    </div>
  );
}

export default LoginStudent;
