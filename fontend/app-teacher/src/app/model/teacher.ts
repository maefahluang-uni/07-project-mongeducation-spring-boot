// src/app/models/teacher.model.ts

export class Teacher {
  id: number;
  bankID: number; // Assuming bankID is of type number in your entity
  firstName: string;
  lastName: string;
  idCard: string;
  userName: string;
  passWord: string;

  constructor(
    id: number,
    bankID: number,
    firstName: string,
    lastName: string,
    idCard: string,
    userName: string,
    passWord: string
  ) {
    this.id = id;
    this.bankID = bankID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.idCard = idCard;
    this.userName = userName;
    this.passWord = passWord;
  }
}
