import "./App.css";
import React, { useState, useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import LoginTeacher from "./Pages/student/LoginTeacher";
import RegisterTeacher from "./Pages/teacher/RegisterTeacher";
import AddBank from "./Pages/service/AddBank";
import RegisterStudent from "./Pages/teacher/RegisterStudent";
import LoginStudent from "./Pages/student/LoginStudent";

const userContext = React.createContext();

function App() {
  //set data
  const [dataContent, setDataContent] = useState({
    id: null,
    userName: "",
    passWord: "",
    status: "",
  });

  const dataUser = JSON.parse(localStorage.getItem("dataContent"));

  //return function

  return (
    <>
      <userContext.Provider value={{ dataContent, setDataContent }}>
        <>
          <Routes>
            <Route path="/Bank" element={<AddBank></AddBank>} />
            <Route
              path="/LoginTeacher"
              element={<LoginTeacher></LoginTeacher>}
            />
            <Route
              path="/LoginStudent"
              element={<LoginStudent></LoginStudent>}
            />
            <Route
              path="/RegisterStudent"
              element={<RegisterStudent></RegisterStudent>}
            />
            <Route
              path="/RegisterTeacher"
              element={<RegisterTeacher></RegisterTeacher>}
            />
            <Route path="/*" element={<div>Page not found</div>} />
          </Routes>
        </>
      </userContext.Provider>
    </>
  );
}
export { userContext };
export default App;
