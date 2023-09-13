package spring.edu.mongbank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String bankID;

    public Bank(){
        
    }
    public Bank(Long id, String name, String bankID) {
        this.id = id;
        this.name = name;
        this.bankID = bankID;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getBankID() {
        return bankID;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    
}
