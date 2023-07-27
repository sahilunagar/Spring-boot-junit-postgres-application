# Spring boot-junit-postgres-application
 Simple Java application in Spring Boot to create/delete/query loans along with Unit tests

1. Versions used:
    Java: 1.8
   
    Spring-boot: 3.1.2
   
    Postgresql: 4

3. Create schema in PGadmin 4 with the name "loandb".
4. Update the \src\main\resources\application.properties file with the appropriate username and password of your Postgres account.
5. Rest API endpoints of the application:
   
    `loan/add`: To create a new loan (the JSON object is accepted in the HTTP POST request body.
   
    `loan/delete/{loanId}`: To delete a particular loan with the id `loanId` from a database.
   
    `loan/delete/all`: To delete all the loans from a database.
   
    `loan/allLoans`: To list all the loans in JSON format.
   
    `loan/remainingAmountByCustomerId/{custId}`: To get the Aggregated remaining amount for a particular Customer Id `custId`.
   
    `loan/remainingAmountByLenderId/{lenderId}` To get the Aggregated remaining amount for a particular Lender Id `lenderId`.
   
7. Loan creation will fail with `BAD_REQUEST` error if we try to create a loan with Payment data greater than the Due date.
8. Unit test and Integration test to test all the functionalities of the application are added in the `test.java.com.example.loan.LoanApplicationTest.java` class.
