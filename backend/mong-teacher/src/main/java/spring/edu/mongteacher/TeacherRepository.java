package spring.edu.mongteacher;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository <Teacher,Long>{
    public List<Teacher> findAll();
    public Optional<Teacher> findById(Long id);
}
