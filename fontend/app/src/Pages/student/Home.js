import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {Link,useNavigate} from "react-router-dom";
import "../../Css/home.css"

function Home() {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('');
  const [content, setContent] = useState([]);
  const [nameCategory, setNameCategory] = useState('');
  const navigate = useNavigate();

  const dataUser = JSON.parse(localStorage.getItem("dataContent"));
  if(dataUser.id == null){
    navigate("/LoginTeacher")
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
    <div>
      <header>
        {/* Dropdown ด้านบน */}
        <select onChange={handleCategoryChange}>
          <option value="null">กรุณาเลือกหมวดหมู่</option>
          {categories.map(category => (
            <option key={category.id} value={category.id}>{category.name}</option>
          ))}
        </select>
      </header>
      <main>
        {/* รายการหมวดหมู่ด้านล่าง */}
        <div>
          <h2>หมวดหมู่ที่เลือก: {nameCategory || 'กรุณาเลือกหมวดหมู่'}</h2>
          <ul className='main'>
            {content.map(item => (
              <li key={item.id} className='card'>
                <Link to={`/BuyCourse/${item.id}`}>
                <img src='https://findstack.com/wp-content/uploads/2021/03/The-Ultimate-List-of-E-Learning-Statistics-1.png'></img>
                <div className="Products__name">{item.name}</div>
                <div className="Products__price">{item.price}</div>
                </Link>
              </li>
            ))}
          </ul>
        </div>
      </main>
    </div>
  );
}

export default Home;
