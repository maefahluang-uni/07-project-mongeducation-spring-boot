package spring.edu.mongteacher;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/banks")
    public ResponseEntity<List<Bank>> getAllBank(){
        return ResponseEntity.ok().body(bankRepository.findAll());
    }
    @PostMapping("/banks")
    public ResponseEntity<String> crateBank(@RequestBody Bank bank){
        bankRepository.save(bank);
        return ResponseEntity.ok().body("created");
    }
}
