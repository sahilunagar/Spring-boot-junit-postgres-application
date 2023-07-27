package com.example.loan.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loan.Loan;

@Repository
public interface LoanDao extends JpaRepository<Loan, Integer> {

    List<Loan> findByCustomerId(String custId);

    List<Loan> findBylenderId(String lenderId);

}
