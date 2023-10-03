import "./App.css";
import React, { useState, useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import LoginTeacher from "./Pages/LoginTeacher";

const userContext = React.createContext();

function App() {
  //set data
  const [dataContent, setDataContent] = useState({
    id: null,
    firstName: "",
    lastName: "",
  });

  const dataUser = JSON.parse(localStorage.getItem("dataContent"))
  //return function
  return (
    <>
      <userContext.Provider value={{ dataContent, setDataContent }}>
        <>
        <Routes>
          <Route path="/LoginTeacher" element={<LoginTeacher></LoginTeacher>} />
          <Route path="/*" element={<div>Page not found {console.log(dataUser.firstName+dataUser.lastName)}</div>} />
        </Routes>
        </>
      </userContext.Provider>
    </>
  );
}
export {userContext};
export default App;
