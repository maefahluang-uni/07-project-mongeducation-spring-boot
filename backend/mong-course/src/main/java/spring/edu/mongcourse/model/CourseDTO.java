package spring.edu.mongcourse.model;

public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int credit;
    private String categoryID;
    private String teacherID;

    public CourseDTO() {
    }

    public CourseDTO(Long id, String name, String description, double price, int credit, String categoryID,
            String teacherID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.credit = credit;
        this.categoryID = categoryID;
        this.teacherID = teacherID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

}
