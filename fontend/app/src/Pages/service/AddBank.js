import React, { useContext, useState } from "react";
import axios from "axios";
import { userContext } from "../../App";

function AddBank() {
  const [bankName, setBankName] = useState("");
  const [bankNum, setBankNum] = useState("");
  const { datacontent, setDataContent } = useContext(userContext);

  //set event
  const handleSubmit = (e) => {
    e.preventDefault();
    const data = {
      name: bankName,
      bankNum: bankNum,
    };

    // กำหนด URL ที่จะทำการ POST ข้อมูล
    const url = "http://localhost:8010/banks";

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
        console.log(response.data);

        // get last id
        axios
          .get("http://localhost:8010/banks/")
          .then((response) => {
            // Handle the successful response here
            const last_index = response.data.length - 1;
            const data = response.data[last_index];
            const apiUrl = `http://localhost:8010/teachers/${dataUser.id}/banks/${data.id}`;
            console.log(data.id + "\n" + last_index);
            axios
              .put(apiUrl)
              .then((response) => {
                console.log("PUT request เสร็จสมบูรณ์", response.data);
              })
              .catch((error) => {
                console.error("เกิดข้อผิดพลาดในการทำ PUT request", error);
              });
          })
          .catch((error) => {});
      })
      .catch((error) => {
        console.error("เข้าสู่ระบบไม่สำเร็จ", error); // แสดงข้อผิดพลาด (ถ้ามี)
      });

    //เชื่อม teacher กับ bank
    const dataUser = JSON.parse(localStorage.getItem("dataContent"));
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <label htmlFor="bankName">Bank Name:</label>
        <input
          type="text"
          id="bankName"
          name="bankName"
          value={bankName}
          onChange={(e) => setBankName(e.target.value)}
        ></input>
        <label htmlFor="BankNum">Bank number:</label>
        <input
          type="text"
          id="bankNum"
          name="bankNum"
          value={bankNum}
          onChange={(e) => setBankNum(e.target.value)}
        ></input>
        <button>submit</button>
      </form>
    </>
  );
}

export default AddBank;
