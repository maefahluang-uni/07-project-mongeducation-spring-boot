import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {Link,useNavigate} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import "./index.css";

function Home() {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('');
  const [content, setContent] = useState([]);
  const [nameCategory, setNameCategory] = useState('');
  const navigate = useNavigate();
  

  const dataUser = JSON.parse(localStorage.getItem("dataContent"));
    //Check if statust is not Student
    if(dataUser.status == null){
        navigate("/");
    }

  useEffect(() => {
    // เรียกใช้ API เพื่อดึงข้อมูลหมวดหมู่
    axios.get('http://localhost:8020/categories/')
      .then(response => {
        setCategories(response.data);
      })
      .catch(error => {
        console.error('เกิดข้อผิดพลาดในการดึงข้อมูลหมวดหมู่', error);
      });
  }, []);

  useEffect(() => {
    // เรียกใช้ API เพื่อดึงข้อมูลเนื้อหาสำหรับหมวดหมู่ที่ถูกเลือก
    if (selectedCategory) {
      axios.get(`http://localhost:8020/courses/category/${selectedCategory}`)
        .then(response => {
          setContent(response.data);
        })
        .catch(error => {
          console.error('เกิดข้อผิดพลาดในการดึงข้อมูลเนื้อหา', error);
        });
    }
  }, [selectedCategory]);

  const handleCategoryChange = (event) => {
    setSelectedCategory(event.target.value);
    setNameCategory(event.target.options[event.target.selectedIndex].text); // อัพเดต nameCategory ด้วยค่า category.name
  };

  return (
    <div className="container">
      <div style={{ margin: "5px"}}><Link to={"/"}>ล็อคอิน</Link>&gt;<Link to={"/Home"}>หน้าหลัก</Link>&gt;<Link to={"/MyCourse"}>คอร์สของฉัน</Link></div>
      <header>
        <div className="row">
          <div className="col">
            {/* Dropdown ด้านบน */}
            <select className="form-select" onChange={handleCategoryChange}>
              <option value="null">กรุณาเลือกหมวดหมู่</option>
              {categories.map(category => (
                <option key={category.id} value={category.id}>{category.name}</option>
              ))}
            </select>
          </div>
        </div>
      </header>
      <main>
        {/* รายการหมวดหมู่ด้านล่าง */}
        <div>
          <h2>หมวดหมู่ที่เลือก: {nameCategory || 'กรุณาเลือกหมวดหมู่'}</h2>
          <div className="row">
            {content.map(item => (
              <div key={item.id} className="col-md-4 mb-4">
                <div className="card">
                  <Link to={`/BuyCourse/${item.id}`}>
                    <img src="https://findstack.com/wp-content/uploads/2021/03/The-Ultimate-List-of-E-Learning-Statistics-1.png" className="card-img-top" alt="Course" />
                    <div className="card-body">
                      <h5 className="card-title">{item.name}</h5>
                      <p className="card-text">Price: {item.price}</p>
                    </div>
                  </Link>
                </div>
              </div>
            ))}
          </div>
        </div>
      </main>
    </div>
  );
}

export default Home;
