package spring.edu.mongstudent;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
    public List<Student> findAll();
}
