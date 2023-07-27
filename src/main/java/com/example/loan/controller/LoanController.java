package com.example.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loan.Loan;
import com.example.loan.service.LoanService;

@RestController
@RequestMapping("loan")
public class LoanController {

    @Autowired
    LoanService loanService;
    
    @GetMapping("allLoans")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PostMapping("add")
    public ResponseEntity<String> addLoan(@RequestBody Loan loan) {
        return loanService.addLoan(loan);
    }

    @GetMapping("remainingAmountByCustomerId/{custId}")
    public String getRemainingAmountByCustomerId(@PathVariable String custId) {
        return loanService.getRemainingAmountByCustomerId(custId);
    }

    @GetMapping("remainingAmountByLenderId/{lenderId}")
    public String getRemainingAmountByLenderId(@PathVariable String lenderId) {
        return loanService.getRemainingAmountByLenderId(lenderId);
    }

    @DeleteMapping("delete/all")
    public ResponseEntity<String> deleteAllLoans() {
        return loanService.deleteAllLoans();
    }

    @DeleteMapping("delete/{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable Integer loanId) {
        return loanService.deleteLoan(loanId);
    }
}
