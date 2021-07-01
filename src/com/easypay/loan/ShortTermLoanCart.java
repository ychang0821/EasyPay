package com.easypay.loan;

import com.apps.util.Prompter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShortTermLoanCart<T extends ShortTermLoan>{
  // storage for the short term loans
  private List<T> shortTermloans = new ArrayList<>();

  public double shortTermTotalAmount() {
    double result = 0;
    for (T shortTermLoan : shortTermloans) {
      result += shortTermLoan.getAmount();
    }
    return result;
  }

  public void addShortTermLoan(ShortTermLoanType loanType) {
    Prompter prompter = new Prompter(new Scanner(System.in));
    String loanAmount = prompter.prompt("Please enter the loan amount you have left: ", "^[+]?([.]\\d+|\\d+[.]?\\d*)$", "That's not a valid loan amount");
    Double amount = Double.valueOf(loanAmount);

    String loanTerm = prompter.prompt("Please enter how many months you have left on the loan: ", "^[+]?([.]\\d+|\\d+[.]?\\d*)$", "That's not a valid loan term");
    int loanTermPeriod = Integer.parseInt(loanTerm);

    String loanRate = prompter.prompt("Please enter the rate you have on the loan: ", "^[+]?([.]\\d+|\\d+[.]?\\d*)$", "That's not a valid loan rate");
    Double currentRate = Double.valueOf(loanRate);

    int id = shortTermloans.size() + 1;

    shortTermloans.add((T) ShortTermLoanFactory.createShortTermLoan(loanType, amount, loanTermPeriod, currentRate, id));
  }

  public void displayShortTermLoans() {
    for (T shortTermLoan : shortTermloans) {
      ShortTermLoanType loanType = shortTermLoan.getLoanType();
      double amount = shortTermLoan.getAmount();
      double rate = shortTermLoan.getRate() * 100;
      int loanTerm = shortTermLoan.getLoanTerm();
      int id = shortTermLoan.getId();
      double monthlyPayment = shortTermLoan.calculate();
      System.out.println("Loan ID: " + id + ", Loan Type: " + loanType + ", Amount: $" + String.format("%.2f", amount) + ", Rate: " + String.format("%.3f", rate) + "%"
                        + ", Loan Term: " + loanTerm + " month" + ", Monthly Payment: $" + String.format("%.2f", monthlyPayment));
    }
  }

  public double shortTermMonthlyTotal() {
    double result = 0;
    for (T shortTermLoan : shortTermloans) {
      result += shortTermLoan.calculate();
    }
    return result;
  }
}
