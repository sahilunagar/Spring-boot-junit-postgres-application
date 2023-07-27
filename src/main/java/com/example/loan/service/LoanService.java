package com.example.loan.service;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.loan.Loan;
import com.example.loan.dao.LoanDao;

@Service
public class LoanService {

    @Autowired
    LoanDao loanDao;

    public List<Loan> getAllLoans() {
        List<Loan> loans = loanDao.findAll();
        Date today = new Date();
        for(Loan loan : loans) {
            if(today.after(loan.getDueDate())) {
                System.out.println("WARNING!! Payment is past due for the loan " + loan.getLoanId());
            }
        }
        return loans;
    }

    public ResponseEntity<String> addLoan(Loan loan) {
        if(loan.getDueDate().before(loan.getPaymentDate())) {
            return new ResponseEntity<>("Failed to save! Payment Date can't be after the due date.", HttpStatus.BAD_REQUEST);
        }
        loanDao.save(loan);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public String getRemainingAmountByCustomerId(String custId) {
        List<Loan> loansByCustomerId = loanDao.findByCustomerId(custId);
        Double totalRemainingAmount = 0.0;
        for(Loan loan : loansByCustomerId) {
            totalRemainingAmount += loan.getRemainingAmount();
        }
        return String.valueOf(totalRemainingAmount);
    }

    public String getRemainingAmountByLenderId(String lenderId) {
        List<Loan> loansByLenderId = loanDao.findBylenderId(lenderId);
        Double totalRemainingAmount = 0.0;
        for(Loan loan : loansByLenderId) {
            totalRemainingAmount += loan.getRemainingAmount();
        }
        return String.valueOf(totalRemainingAmount);
    }

    public ResponseEntity<String> deleteLoan(Integer loanId) {
        loanDao.deleteById(loanId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAllLoans() {
        loanDao.deleteAll();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
}
