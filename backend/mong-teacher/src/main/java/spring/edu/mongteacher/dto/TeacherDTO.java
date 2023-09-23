package spring.edu.mongteacher.dto;

import spring.edu.mongteacher.domain.Bank;
import spring.edu.mongteacher.domain.Teacher;

public class TeacherDTO {

    private Long id;
    private Bank bankID;
    private String firstName;
    private String lastName;
    private String idCard;

    public TeacherDTO() {}

    public TeacherDTO(Long id, Bank bankID, String firstName, String lastName, String idCard) {
        this.id = id;
        this.bankID = bankID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idCard = idCard;
    }

    public Long getId() {
        return id;
    }

    public Bank getBankID() {
        return bankID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBankID(Bank bankID) {
        this.bankID = bankID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
