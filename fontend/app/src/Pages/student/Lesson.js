import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams,useNavigate,Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import "./index.css";

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
    <div className="container">
      <div style={{ margin: "5px"}}><Link to={"/"}>ล็อคอิน</Link>&gt;<Link to={"/Home"}>หน้าหลัก</Link>&gt;<Link to={"/MyCourse"}>คอร์สของฉัน</Link></div>
      <h2 className="my-4">บทเรียน</h2>
      <div className="list-group">
        {lessons.map((lesson) => (
          <a
            href="#" // หรือใช้ Link ของ react-router-dom ตามที่คุณต้องการ
            className="list-group-item list-group-item-action"
            key={lesson.id}
          >
            {lesson.name}
          </a>
        ))}
      </div>
    </div>
  );
}

export default Lesson;
