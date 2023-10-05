package spring.edu.mongteacher.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BankDTO {
    private Long id;
    private String name;
    private String bankNum;
    

    public BankDTO(){
        
    }


    public BankDTO(Long id, String name, String bankNum) {
        this.id = id;
        this.name = name;
        this.bankNum = bankNum;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getBankNum() {
        return bankNum;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }
   

    
}
