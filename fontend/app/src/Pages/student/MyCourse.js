import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link,useNavigate } from "react-router-dom";

function MyCourses() {
  const [courses, setCourses] = useState([]);
  const dataUser = JSON.parse(localStorage.getItem("dataContent"));
  const navigate = useNavigate();
  //Check if statust is not Student
  if(dataUser.status != "Student" || dataUser.status == null){
      navigate("/LoginStudent");
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
      <h2>คอร์สของฉัน</h2>
      <ul>
        {courses.map((course) => (
          <Link to={`/Lesson/${course.id}`}>
            <li key={course.id}>
              <div>
                ชื่อคอร์ส: {course.name} รายละเอียด: {course.description}{" "}
                เครดิต: {course.credit}
              </div>
            </li>
          </Link>
        ))}
      </ul>
    </div>
  );
}

export default MyCourses;
