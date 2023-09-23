package spring.edu.mongteacher.repository;

import org.springframework.data.repository.CrudRepository;

import spring.edu.mongteacher.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository <Teacher,Long>{
    public List<Teacher> findAll();
    public Optional<Teacher> findById(Long id);
}
