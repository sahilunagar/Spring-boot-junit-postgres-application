package com.example.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.loan.service.LoanService;

@SpringBootTest
class LoanApplicationTests {

	@Autowired
	LoanService loanService;

	@Test
	void createLoanWithGreaterPaymentDateTest() {
		String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date paymentDate = new Date();
		Date dueDate = new Date();
		try {
			paymentDate = dateFormat.parse("2023-07-27");
			dueDate = dateFormat.parse("2023-06-27");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Loan loan = new Loan();
		loan.setCustomerId("C1_test");
		loan.setLenderId("L1_test");
		loan.setAmount(10000.0);
		loan.setRemainingAmount(10000.0);
		loan.setInterestPerDay(1.0);
		loan.setPenaltyPerDay(1.0);
		loan.setPaymentDate(paymentDate);
		loan.setDueDate(dueDate);

		ResponseEntity<String> loanAdded = loanService.addLoan(loan);
		assertEquals(loanAdded.getStatusCode(), HttpStatus.BAD_REQUEST, "Loan creation should fail with Bad Request error");
	}

	@Test
	void integrationTest() {
		String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date paymentDate = new Date();
		Date dueDate = new Date();
		try {
			paymentDate = dateFormat.parse("2023-07-27");
			dueDate = dateFormat.parse("2023-10-27");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//deleteAllLoansTest
		ResponseEntity<String> loansDeleted = loanService.deleteAllLoans();
		assertEquals(loansDeleted.getStatusCode(), HttpStatus.OK, "All Loans should be deleted");


		//addLoanTest
		Loan loan1 = new Loan();
		loan1.setCustomerId("C1_int_test");
		loan1.setLenderId("L1_int_test");
		loan1.setAmount(10000.0);
		loan1.setRemainingAmount(10000.0);
		loan1.setInterestPerDay(1.0);
		loan1.setPenaltyPerDay(1.0);
		loan1.setPaymentDate(paymentDate);
		loan1.setDueDate(dueDate);

		ResponseEntity<String> loanAdded1 = loanService.addLoan(loan1);
		assertEquals(loanAdded1.getStatusCode(), HttpStatus.OK, "Loan should be created successfully");

		Loan loan2 = new Loan();
		loan2.setCustomerId("C1_int_test");
		loan2.setLenderId("L2_int_test");
		loan2.setAmount(10000.0);
		loan2.setRemainingAmount(10000.0);
		loan2.setInterestPerDay(1.0);
		loan2.setPenaltyPerDay(1.0);
		loan2.setPaymentDate(paymentDate);
		loan2.setDueDate(dueDate);

		ResponseEntity<String> loanAdded2 = loanService.addLoan(loan2);
		assertEquals(loanAdded2.getStatusCode(), HttpStatus.OK, "Loan should be created successfully");

		Loan loan3 = new Loan();
		loan3.setCustomerId("C2_int_test");
		loan3.setLenderId("L2_int_test");
		loan3.setAmount(20000.0);
		loan3.setRemainingAmount(20000.0);
		loan3.setInterestPerDay(1.0);
		loan3.setPenaltyPerDay(1.0);
		loan3.setPaymentDate(paymentDate);
		loan3.setDueDate(dueDate);

		ResponseEntity<String> loanAdded3 = loanService.addLoan(loan3);
		assertEquals(loanAdded3.getStatusCode(), HttpStatus.OK, "Loan should be created successfully");

		//getAllLoansTest
		List<Loan> allLoans = loanService.getAllLoans();
		assertEquals(3, allLoans.size(), "Total loans created should be 3");

		//getRemainingAmountByCustomerIdTest
		String remainingAmountByCustomerId = loanService.getRemainingAmountByCustomerId("C1_int_test");
		assertEquals("20000.0", remainingAmountByCustomerId, "Aggregated Remaining Amount bu Customer ID should match");

		//getRemainingAmountByLenderIdTest
		String remainingAmountByLenderId = loanService.getRemainingAmountByLenderId("L2_int_test");
		assertEquals("30000.0", remainingAmountByLenderId, "Aggregated Remaining Amount by Lender ID should match");
	}
}
