import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams,useNavigate } from "react-router-dom";

function Lesson() {
  const [lessons, setLessons] = useState([]); // เก็บข้อมูลบทเรียนที่ได้จาก API
  const { id } = useParams();
  const navigate = useNavigate();
  

  const dataUser = JSON.parse(localStorage.getItem("dataContent"));

  //Check if statust is not Student
  if (dataUser.status == null) {
    navigate("/LoginStudent");
  }

  useEffect(() => {
    // ส่งคำขอไปยัง API เพื่อดึงข้อมูลบทเรียน
    axios
      .get(`http://localhost:8030/lessons/course/${id}`) // เปลี่ยน URL เป็น URL ของ API ของคุณ
      .then((response) => {
        setLessons(response.data); // อัปเดต state ด้วยข้อมูลบทเรียนที่ได้จาก API
      })
      .catch((error) => {
        console.error("เกิดข้อผิดพลาดในการดึงข้อมูลบทเรียน", error);
      });
  }, []); // ค่าว่างใน dependencies หมายถึงให้เรียก API เมื่อคอมโพเนนต์ถูกโหลดครั้งแรกเท่านั้น

  return (
    <div>
      <h2>บทเรียน</h2>
      {lessons.map((lesson) => (
        <div key={lesson.id}>
          <p>{lesson.name}</p>
        </div>
      ))}
    </div>
  );
}

export default Lesson;
