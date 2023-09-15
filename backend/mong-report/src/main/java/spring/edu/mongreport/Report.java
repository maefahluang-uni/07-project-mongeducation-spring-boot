package spring.edu.mongreport;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Report {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String studentId;
    private String description;
    
    public Report() {
    }

    public Report(Long id, String description, String studentId) {
        this.id = id;
        this.description = description;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
}
