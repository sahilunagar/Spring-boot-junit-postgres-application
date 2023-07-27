package com.example.loan;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;
    private String customerId;
    private String lenderId;
    private Double amount;
    private Double remainingAmount;
    private Date paymentDate;
    private Double interestPerDay;
    private Date dueDate;
    private Double penaltyPerDay;
    private Boolean cancel;
}
