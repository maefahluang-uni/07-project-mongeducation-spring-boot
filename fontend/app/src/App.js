import "./App.css";
import React, { useState, useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import AddBank from "./Pages/service/AddBank";
import RegisterStudent from "./Pages/teacher/RegisterStudent";
import LoginStudent from "./Pages/student/LoginStudent";
import Home from "./Pages/student/Home";
import BuyCourse from "./Pages/student/BuyCourse";
import MyCourses from "./Pages/student/MyCourse";
import Lesson from "./Pages/student/Lesson";

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
            <Route path="/Home" element={<Home></Home>} />
            <Route path="/Bank" element={<AddBank></AddBank>} />
            <Route path="/Lesson/:id" element={<Lesson></Lesson>} />
            <Route path="/MyCourse" element={<MyCourses></MyCourses>} />
            <Route path="/BuyCourse/:id" element={<BuyCourse></BuyCourse>} />
            <Route path="/" element={<LoginStudent></LoginStudent>}/>
            <Route path="/RegisterStudent" element={<RegisterStudent></RegisterStudent>}/>
            <Route path="/*" element={<div>Page not found</div>} />
          </Routes>
        </>
      </userContext.Provider>
    </>
  );
}
export { userContext };
export default App;
