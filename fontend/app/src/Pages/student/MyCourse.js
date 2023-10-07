import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link,useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import "./index.css";


function MyCourses() {
  const [courses, setCourses] = useState([]);
  const dataUser = JSON.parse(localStorage.getItem("dataContent"));
  const navigate = useNavigate();
  //Check if statust is not Student
  if(dataUser.status != "Student" || dataUser.status == null){
      navigate("/");
  }

  useEffect(() => {
    axios
      .get("http://localhost:8070/reports")
      .then((response) => {
        // ตรวจสอบข้อมูลที่ส่งมาจาก API
        console.log(response.data);

        const filteredReports = response.data.filter(
          (report) => report.studentId == dataUser.id
        );
        const courseIds = filteredReports.map((report) => report.courseId);

        // สร้างอาร์เรย์ courses ที่ว่างเปล่า
        const allCourses = [];

        // สำหรับแต่ละ courseIds ดึงข้อมูลคอร์สและเพิ่มลงในอาร์เรย์
        courseIds.forEach((courseId) => {
          axios
            .get(`http://localhost:8020/courses/${courseId}`)
            .then((response) => {
              // ตรวจสอบข้อมูลที่ส่งมาจาก API
              console.log(response.data);

              allCourses.push(response.data);

              // เมื่อสรุปครบทุกคอร์สใน courseIds
              if (allCourses.length === courseIds.length) {
                setCourses(allCourses); // กำหนดคอร์สทั้งหมดใน state
              }
            })
            .catch((error) => {
              console.error("เกิดข้อผิดพลาดในการดึงข้อมูลคอร์ส", error);
            });
        });
      })
      .catch((error) => {
        console.error("เกิดข้อผิดพลาดในการดึงข้อมูลรายงาน", error);
      });
  }, []);

  return (
    <div>
      <div style={{ margin: "5px"}}><Link to={"/"}>ล็อคอิน</Link>&gt;<Link to={"/Home"}>หน้าหลัก</Link>&gt;<Link to={"/MyCourse"}>คอร์สของฉัน</Link></div>
      <h2 className="mb-4">คอร์สของฉัน</h2>
      <ul className="list-group">
        {courses.map((course) => (
          <Link to={`/Lesson/${course.id}`} className="list-group-item" key={course.id}>
            <div>
              <h4>ชื่อคอร์ส: {course.name}</h4>
              <p>รายละเอียด: {course.description}</p>
              <p>เครดิต: {course.credit}</p>
            </div>
          </Link>
        ))}
      </ul>
    </div>
  );
}

export default MyCourses;
