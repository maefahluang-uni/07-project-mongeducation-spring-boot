
# Mong Education
@Springboot-project-group

This is a group project to learning about how to develop the backend using a springboot framework. we've learn various topic like entity relational, loadbalancer, kafka and microservices. Now we use these knowledege and integrate them in our project of e-education system.

here is a video for project review https://youtu.be/Rngf3Gt5WsQ



## Acknowledgements

#### :: Student Microservice 

This microservice provide the student entity who is a person who want to enroll or learning some new knowledges.

#### :: Teacher Microservice

This microservice provice the teacher entity who is a person who want to teach a topic of thier particular track.

#### :: Course Microservice 

This microservice provide the course entity which collect a data of lesson and topic and the category entity which it seperate course into any group.

#### :: Lesson Microservice

This microservice provide lesson entity which contain a knowledges and details of learning topic.

#### :: Report Microservice

This microservice provide report entity which map the student who enroll any course from buying transaction.

#### :: Naming Eureka Microservice

This service provide the naming server and act like a hub to connect all of microservice in our system.

#### :: Loadbalancer

This service will provide a loadbalancing function to seperate a user request to make a better flow traffic.
## Installation

before start we want to make sure you have a react and node installation in your computer first.

```bash
  npm install
  npm install react-router-dom --save
```

After you configurated anyting complete you can run the server of frontend to try the journal of our backend use by this command.

```bash
  node start
```
## API Reference
the content below will show you the whole api of our services. you can follow the instructions to know how does it use.

#### Get all items.

```http
  GET /teachers
  GET /students
  GET /banks
  GET /courses
  GET /categories
  GET /lessons
  GET /reports
```
#### Get item by id.

```http
  GET /teachers/{id}
  GET /students/{id}
  GET /banks/{id}
  GET /courses/{id}
  GET /categories/{id}
  GET /lessons/{id}
  GET /reports/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. specific the id of their own entity.|

#### Create new item.

```http
  POST /teachers
  POST /students
  POST /banks
  POST /courses
  POST /categories
  POST /lessons
```

#### Put own item.

```http
  PUT /students/{id}
  PUT /courses/{id}
  PUT /categories/{id}
  PUT /lessons/{id}
  PUT /reports/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. specific the id of their own entity.|

#### Patch or update own item.

```http
  PATCH /students/{id}
  PATCH /courses/{id}
  PATCH /categories/{id}
  PATCH /lessons/{id}
  PATCH /reports/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. specific the id of their own entity.|

#### Delete item by id.

```http
  DEL /teachers/{id}
  DEL /students/{id}
  DEL /banks/{id}
  DEL /courses/{id}
  DEL /categories/{id}
  DEL /lessons/{id}
  DEL /reports/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. specific the id of their own entity.|

#### Delete all items.
```http
  DEL /students
  DEL /courses
  DEL /categories
  DEL /lessons
  DEL /reports
```

#### Special function.
 
```http
  PUT /teachers/{teacher_id}/banks/{bank_id}
  POST /students/{student_id}/enroll/{course_id}
  POST /courses/create/lesson/{course_id}
  GET /lessons/course/{course_id}
  GET /notes/lesson/{lesson_id}
  GET /videoes/lesson/{lesson_id}
  GET /courses/category/{category_id}
  GET /courses/teacher/{teacher_id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `teacher_id` | `Long` | **Required**. specific the id of teacher entity.|
| `bank_id` | `Long` | **Required**. specific the id of bank entity.|
| `student_id` | `Long` | **Required**. specific the id of student entity.|
| `course_id` | `Long` | **Required**. specific the id of course entity.|
| `lesson_id` | `Long` | **Required**. specific the id of lesson entity.|
| `category_id` | `Long` | **Required**. specific the id of category entity.|
