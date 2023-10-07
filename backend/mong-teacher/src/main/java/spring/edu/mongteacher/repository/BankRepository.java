package spring.edu.mongteacher.repository;


import java.util.*;

import org.springframework.data.repository.CrudRepository;

import spring.edu.mongteacher.domain.Bank;

public interface BankRepository extends CrudRepository<Bank,Long>{
    public List<Bank> findAll();
    public Optional<Bank> findById(Long id);
}
