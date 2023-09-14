package spring.edu.mongcourse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import spring.edu.mongcourse.model.Category;
import spring.edu.mongcourse.model.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
    public List<Course> findAll();

    public Optional<Course> findById(Long id);

    public boolean existsById(Long id);

    public List<Course> findByCategory(Category category);
}
