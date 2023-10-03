import React, { useState, useContext } from "react";
import PopupLogin from "../Components/PopupLogin";
import { userContext } from "../App";

function LoginTeacher() {
  // set state
  const [userName, setUserName] = useState("");
  const [passWord, setPassWord] = useState("");
  const { dataContent, setDataContent } = useContext(userContext);

  // set event
  const handleSubmit = (e) => {
    e.preventDefault();

    // Check if dataContent is not null and valid before updating it
    if (dataContent && typeof dataContent === "object") {
      // Update dataContent
      const updatedDataContent = {
        ...dataContent,
        firstName: userName,
        lastName: passWord,
      };
      setDataContent(updatedDataContent);

      // Update localStorage
      localStorage.setItem("dataContent", JSON.stringify(updatedDataContent));
    }
    console.log(dataContent)
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
    </div>
  );
}

export default LoginTeacher;
