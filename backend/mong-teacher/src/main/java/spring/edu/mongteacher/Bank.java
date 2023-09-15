package spring.edu.mongteacher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Bank {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String bankNum;
    

    public Bank(){
        
    }
    public Bank(String name, String bankNum) {
        this.name = name;
        this.bankNum =bankNum;
    }

    public String getName() {
        return name;
    }
    public String getBankNum() {
        return bankNum;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    
}
