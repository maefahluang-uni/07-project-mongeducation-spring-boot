import React, { useState, useContext } from "react";
import { userContext } from "../../App";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function RegisterTeacher() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [userName, setUserName] = useState("");
  const [passWord, setPassWord] = useState("");
  const [idCard, setIdCard] = useState("");
  const { dataContent, setDataContent } = useContext(userContext);
  const navigate = useNavigate();

  //set even
  const handleSubmit = (e) => {
    e.preventDefault();
    const data = {
      firstName: firstName,
      lastName: lastName,
      idCard: idCard,
      userName: userName,
      passWord: passWord,
    };

    // กำหนด URL ที่จะทำการ POST ข้อมูล
    const url = "http://localhost:8010/teachers/";

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
        // get last id
        axios
          .get("http://localhost:8010/teachers/")
          .then((response) => {
            // Handle the successful response here
            const last_index = response.data.length - 1;
            const data = response.data[last_index];

            // Check if dataContent is not null and valid before updating it
            if (dataContent && typeof dataContent === "object") {
              // Update dataContent
              const updatedDataContent = {
                id: data.id,
                firstName: data.firstName,
                lastName: data.lastName,
                status: "Teacher",
              };
              setDataContent(updatedDataContent);

              // Update localStorage
              localStorage.setItem(
                "dataContent",
                JSON.stringify(updatedDataContent)
              );
              console.log(response);
              console.log(`\n` + response);
            }

            console.log("Response data:", response.data[last_index]);
            navigate("/");
          })
          .catch((error) => {
            // Handle any errors that occurred during the request
            console.error("Error:", error);
          });
        console.log(response);
      })
      .catch((error) => {
        console.error("เข้าสู่ระบบไม่สำเร็จ", error); // แสดงข้อผิดพลาด (ถ้ามี)
      });
  };

  return (
    <>
      <h1>Register</h1>
      <form onSubmit={handleSubmit}>
        <label>First Name:</label>
        <input
          type="text"
          id="firstName"
          name="firstName"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        ></input>

        <label>Last Name:</label>
        <input
          type="text"
          id="lastName"
          name="lastName"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        ></input>

        <label>ID Card:</label>
        <input
          type="text"
          id="idCard"
          name="idCard"
          value={idCard}
          onChange={(e) => setIdCard(e.target.value)}
        ></input>

        <label>Username:</label>
        <input
          type="text"
          id="userName"
          name="userName"
          value={userName}
          onChange={(e) => setUserName(e.target.value)}
        ></input>

        <label>Password:</label>
        <input
          type="text"
          id="passWord"
          name="passWord"
          value={passWord}
          onChange={(e) => setPassWord(e.target.value)}
        ></input>

        <button>submit</button>
      </form>
    </>
  );
}

export default RegisterTeacher;
