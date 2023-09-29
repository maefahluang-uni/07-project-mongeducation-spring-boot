package spring.edu.mongteacher.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.edu.mongteacher.domain.Bank;
import spring.edu.mongteacher.dto.BankDTO;
import spring.edu.mongteacher.mapper.ServerMapper;
import spring.edu.mongteacher.repository.BankRepository;

@RestController
public class BankController {
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private ServerMapper serverMapper;

    //get all
    @GetMapping("/banks")
    public ResponseEntity<List<Bank>> getAllBank(){
        return ResponseEntity.ok().body(bankRepository.findAll());
    }
    //get by id
    @GetMapping("/banks/{id}")
    public ResponseEntity<Optional<Bank>> getBankById(@PathVariable Long id){
        Optional<Bank> bank = bankRepository.findById(id);
        if(bank.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id is not found");
        }
        return ResponseEntity.ok(bank);
    }
    //post
    @PostMapping("/banks")
    public ResponseEntity<String> crateBank(@RequestBody Bank bank){
        bankRepository.save(bank);
        return ResponseEntity.ok().body("created");
    }
    //delete by id
    @DeleteMapping("/banks/{id}")
    public ResponseEntity<String> deleteBankById(@PathVariable Long id){
         Optional<Bank> bank = bankRepository.findById(id);
        if(!bank.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id is not found");
        }
        bankRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("deleted");
    }
    //update by patch
    @PatchMapping("/banks/{id}")
    public ResponseEntity<String> UpdateBank(@PathVariable Long id,@RequestBody BankDTO bankDTO){
        Optional<Bank> optBank = bankRepository.findById(id);
        if(!optBank.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank not found");
        }
        Bank bank = optBank.get();
        serverMapper.updateBankFromDto(bankDTO, bank);
        bankRepository.save(bank);
        return ResponseEntity.ok("updated");
    }
}
