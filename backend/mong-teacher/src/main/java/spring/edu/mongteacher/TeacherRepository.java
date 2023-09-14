package spring.edu.mongteacher;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TeacherRepository extends CrudRepository <Teacher,Long>{
    public List<Teacher> findAll();
}
