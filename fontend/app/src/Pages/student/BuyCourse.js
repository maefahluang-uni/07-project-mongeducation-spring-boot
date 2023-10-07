import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate,Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import "./index.css";

function BuyCourse() {
  const [course, setCourse] = useState({});
  const { id } = useParams();
  const [teacherName, setTeacherName] = useState([]);
  const navigate = useNavigate();

  const dataUser = JSON.parse(localStorage.getItem("dataContent"));
  //Check if statust is not Student
  //Check if statust is not Student
  if (dataUser.status == null) {
    navigate("/");
  }

  useEffect(() => {
    // เรียกใช้ API เพื่อดึงข้อมูลคอร์สตาม ID ที่รับมาจาก URL
    axios
      .get(`http://localhost:8020/courses/${id}`)
      .then((response) => {
        setCourse(response.data);

        // เรียกใช้ API เพื่อดึงข้อมูลคอร์สตาม ID ที่รับมาจาก URL
        axios
          .get(`http://localhost:8010/teachers/${response.data.teacherID}`)
          .then((response) => {
            setTeacherName(response.data);
          })
          .catch((error) => {
            console.error("เกิดข้อผิดพลาดในการดึงข้อมูลคอร์ส", error);
          });
      })
      .catch((error) => {
        console.error("เกิดข้อผิดพลาดในการดึงข้อมูลคอร์ส", error);
      });
  }, [id]);

  // set event
  const handleBuyCourse = () => {
    // ทำ POST request ไปยัง URL ของ API
    axios
      .post(`http://localhost:8000/students/${dataUser.id}/enroll/${id}`)
      .then((response) => {
        console.log("สร้างโพสต์สำเร็จ", response.data);
        // ทำอย่างอื่น ๆ หลังจากที่สร้างโพสต์เรียบร้อยแล้ว
        alert(`คุณได้ทำการซื้อคอร์ส ${course.name}`);
        navigate("/MyCourse");
      })
      .catch((error) => {
        console.error("เกิดข้อผิดพลาดในการสร้างโพสต์", error);
      });
  };

  return (
    <div className="container mt-5">
      <div style={{ margin: "5px"}}><Link to={"/"}>ล็อคอิน</Link>&gt;<Link to={"/Home"}>หน้าหลัก</Link>&gt;<Link to={"/MyCourse"}>คอร์สของฉัน</Link></div>
    <div className="row">
      <div className="col-md-6 mx-auto text-center">
        <div className="card">
          <img
            src="https://findstack.com/wp-content/uploads/2021/03/The-Ultimate-List-of-E-Learning-Statistics-1.png"
            alt={course.name}
            className="card-img-top"
          />
          <div className="card-body">
            <h2 className="card-title">รายละเอียดคอร์ส: {course.name}</h2>
            <p>ID: {course.id}</p>
            <p>รายละเอียด: {course.description}</p>
            <p>ราคา: {course.price}</p>
            <p>เครดิต: {course.credit}</p>
            <p>หมวดหมู่: {course.categoryID}</p>
            <p>อาจารย์ผู้สอน: {teacherName.firstName} {teacherName.lastName}</p>
            <button className="btn btn-primary" onClick={handleBuyCourse}>ซื้อ</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  );
}

export default BuyCourse;
