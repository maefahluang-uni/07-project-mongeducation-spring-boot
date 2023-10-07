export class Course {
  id: number;
  name: string;
  description: string;
  price: number;
  credit: number;
  categoryID: string;
  teacherID: string;

  constructor(
    id: number,
    name: string,
    description: string,
    price: number,
    credit: number,
    categoryID: string,
    teacherID: string
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.credit = credit;
    this.categoryID = categoryID;
    this.teacherID = teacherID;
  }
}
