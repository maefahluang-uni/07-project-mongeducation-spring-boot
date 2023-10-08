import React, { useState, useContext } from "react";
import { userContext } from "../../App";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import "./index.css";

function LoginStudent() {
  const [activeTab, setActiveTab] = useState("login");
  const [loginData, setLoginData] = useState({
    username: "",
    password: "",
  });
  const [errorText, setErrorText] = useState("");
  const [registerData, setRegisterData] = useState({
    firstName: "",
    lastName: "",
    creditUsage: "",
    username: "",
    password: "",
  });
  const [errorTextReg, setErrorTextReg] = useState("");
  const { dataContent, setDataContent } = useContext(userContext);
  const navigate = useNavigate();

  const handleLoginSubmit = (e) => {
    e.preventDefault();
    const data = {
      username: loginData.username,
      password: loginData.password,
    };

    const url = "http://localhost:8000/students/login";

    const headers = {
      "Content-Type": "application/json",
    };

    const axiosOptions = {
      method: "post",
      url: url,
      headers: headers,
      data: data,
    };

    axios(axiosOptions)
      .then((response) => {
        if (dataContent && typeof dataContent === "object") {
          const updatedDataContent = {
            id: response.data[0].id,
            firstName: response.data[0].firstName,
            lastName: response.data[0].lastName,
            status: "Student",
          };
          setDataContent(updatedDataContent);

          localStorage.setItem(
            "dataContent",
            JSON.stringify(updatedDataContent)
          );
        }
        navigate("/Home");
      })
      .catch((error) => {
        setErrorText("Login failed. Please check your credentials.");
        console.error("Login failed", error);
      });
  };

  const handleRegisterSubmit = (e) => {
    e.preventDefault();
    const data = {
      firstName: registerData.firstName,
      lastName: registerData.lastName,
      creditUsage: registerData.creditUsage,
      username: registerData.username,
      password: registerData.password,
    };

    const url = "http://localhost:8000/students/";

    const headers = {
      "Content-Type": "application/json",
    };

    const axiosOptions = {
      method: "post",
      url: url,
      headers: headers,
      data: data,
    };

    axios(axiosOptions)
      .then((response) => {
        axios
          .get("http://localhost:8000/students/")
          .then((response) => {
            const last_index = response.data.length - 1;
            const data = response.data[last_index];

            if (dataContent && typeof dataContent === "object") {
              const updatedDataContent = {
                id: data.id,
                firstName: data.firstName,
                lastName: data.lastName,
                status: "Student",
              };
              setDataContent(updatedDataContent);

              localStorage.setItem(
                "dataContent",
                JSON.stringify(updatedDataContent)
              );
            }
            navigate("/Home");
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      })
      .catch((error) => {
        setErrorTextReg("Registration failed. Please try again.");
        console.error("Registration failed", error);
      });
  };

  return (
    <>
      <ul className="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
        <li className="nav-item" role="presentation">
          <button
            className={`btn btn-outline-primary w-100 ${activeTab === 'login' ? 'active' : ''}`}
            onClick={() => setActiveTab("login")}
            aria-controls="pills-login"
            aria-selected={activeTab === 'login'}
          >
            Login
          </button>
        </li>
        <li className="nav-item" role="presentation">
          <button
            className={`btn btn-outline-primary w-100 ${activeTab === 'register' ? 'active' : ''}`}
            onClick={() => setActiveTab("register")}
            aria-controls="pills-register"
            aria-selected={activeTab === 'register'}
          >
            Register
          </button>
        </li>
      </ul>

      <div className="position-absolute top-50 start-50 translate-middle w-50">
        <div>
          {activeTab === 'login' ? (
            <div className="tab-content">
              <div className="tab-pane fade show active" id="pills-login" role="tabpanel">
                <form onSubmit={handleLoginSubmit}>
                  <div className="mb-3">
                    <label htmlFor="loginUsername" className="form-label">Username:</label>
                    <input
                      type="text"
                      id="loginUsername"
                      name="loginUsername"
                      value={loginData.username}
                      onChange={(e) => setLoginData({ ...loginData, username: e.target.value })}
                      className="form-control"
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="loginPassword" className="form-label">Password:</label>
                    <input
                      type="password"
                      id="loginPassword"
                      name="loginPassword"
                      value={loginData.password}
                      onChange={(e) => setLoginData({ ...loginData, password: e.target.value })}
                      className="form-control"
                    />
                  </div>
                  <div className="form-outline mb-4 text-center text-danger">
                    <label>{errorText}</label>
                  </div>
                  <button type="submit" className="btn btn-primary btn-block mb-4">Sign in</button>
                </form>
              </div>
            </div>
          ) : (
            <div className="tab-content">
              <div className="tab-pane fade show active" id="pills-login" role="tabpanel">
                <form onSubmit={handleRegisterSubmit}>
                  <div className="mb-3">
                    <label htmlFor="registerFirstName" className="form-label">First Name:</label>
                    <input
                      type="text"
                      id="registerFirstName"
                      name="registerFirstName"
                      value={registerData.firstName}
                      onChange={(e) => setRegisterData({ ...registerData, firstName: e.target.value })}
                      className="form-control"
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="registerLastName" className="form-label">Last Name:</label>
                    <input
                      type="text"
                      id="registerLastName"
                      name="registerLastName"
                      value={registerData.lastName}
                      onChange={(e) => setRegisterData({ ...registerData, lastName: e.target.value })}
                      className="form-control"
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="creditUsage" className="form-label">Credit Usage:</label>
                    <input
                      type="text"
                      id="creditUsage"
                      name="creditUsage"
                      value={registerData.creditUsage}
                      onChange={(e) => setRegisterData({ ...registerData, creditUsage: e.target.value })}
                      className="form-control"
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="registerUsername" className="form-label">Username:</label>
                    <input
                      type="text"
                      id="registerUsername"
                      name="registerUsername"
                      value={registerData.username}
                      onChange={(e) => setRegisterData({ ...registerData, username: e.target.value })}
                      className="form-control"
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="registerPassword" className="form-label">Password:</label>
                    <input
                      type="password"
                      id="registerPassword"
                      name="registerPassword"
                      value={registerData.password}
                      onChange={(e) => setRegisterData({ ...registerData, password: e.target.value })}
                      className="form-control"
                    />
                  </div>
                  <div className="form-outline mb-4 text-center text-danger">
                    <label>{errorTextReg}</label>
                  </div>
                  <button type="submit" className="btn btn-primary btn-block mb-3 mt-3">Sign up</button>
                </form>
              </div>
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default LoginStudent;
