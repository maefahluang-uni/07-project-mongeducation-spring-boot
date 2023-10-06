package spring.edu.mongstudent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private int creditUsage;

    private String username;
    private String password;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, int creditUsage, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditUsage = creditUsage;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCreditUsage() {
        return creditUsage;
    }

    public void setCreditUsage(int creditUsage) {
        this.creditUsage = creditUsage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
