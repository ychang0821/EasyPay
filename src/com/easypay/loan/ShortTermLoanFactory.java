package com.easypay.loan;

public class ShortTermLoanFactory {
  private ShortTermLoanFactory() {};

  public static ShortTermLoan createShortTermLoan(ShortTermLoanType loanType, double amount, int loanTerm, double rate, int id) {
    ShortTermLoan loan = null;
    switch(loanType) {
      case STUDENT_LOAN:
        loan = new StudentLoan(loanType, amount, loanTerm, rate, id);
        break;
      case CREDIT_CARD_LOAN:
        loan = new CreditCardLoan(loanType, amount, loanTerm, rate, id);
        break;
      case AUTO_LOAN:
        loan = new AutoLoan(loanType, amount, loanTerm, rate, id);
        break;
      case PERSONAL_LOAN:
        loan = new PersonalLoan(loanType, amount, loanTerm, rate, id);
        break;
    }
    return loan;
  }
}
