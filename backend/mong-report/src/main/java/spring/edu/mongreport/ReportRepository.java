package spring.edu.mongreport;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Long> {
    public List<Report> findAll();
}
