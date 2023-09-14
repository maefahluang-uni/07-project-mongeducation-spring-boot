package spring.edu.mongbank;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<Bank,Long>{
    public List<Bank> findAll();
}
