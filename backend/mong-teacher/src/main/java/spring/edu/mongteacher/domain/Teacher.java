package spring.edu.mongteacher.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bankID",referencedColumnName = "id")
    private Bank bankID;
    

    private String firstName;
    private String lastName;
    private String idCard;
    private String userName;
    private String passWord;

    public Teacher(){}

    public Teacher(Long id, Bank bankID, String firstName, String lastName, String idCard, String userName,
            String passWord) {
        this.id = id;
        this.bankID = bankID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idCard = idCard;
        this.userName = userName;
        this.passWord = passWord;
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

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
