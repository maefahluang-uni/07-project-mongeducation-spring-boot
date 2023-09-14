package spring.edu.mongbank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import spring.edu.mongteacher.TeacherDomain;

@Entity
public class Bank {
    
    @OneToOne
    @JoinColumn(name = "userID")
    private TeacherDomain teacherDomain;

    private String name;
    private String bankID;

    public Bank(){
        
    }
    public Bank(String name, String bankID) {
        this.name = name;
        this.bankID = bankID;
    }

    public String getName() {
        return name;
    }
    public String getBankID() {
        return bankID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    
}
