package spring.edu.monglesson.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import spring.edu.monglesson.model.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    public List<Lesson> findAll();

    public Optional<Lesson> findById(Long id);

    public boolean existsById(Long id);

    public List<Lesson> findByCourseID(String courseID);

}
