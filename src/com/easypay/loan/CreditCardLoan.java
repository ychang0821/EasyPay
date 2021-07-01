package com.easypay.loan;

class CreditCardLoan extends ShortTermLoan {
  private double amount;
  private double rate;
  private int loanTerm;
  private int id;
  private ShortTermLoanType type = ShortTermLoanType.CREDIT_CARD_LOAN;

  CreditCardLoan(ShortTermLoanType type, double amount, int loanTerm, double rate, int id) {
    super(type, amount, loanTerm, rate, id);
  }

  @Override
  public double calculate() {
    double result = 0;
    double monthlyRate = getRate() / 12;
    result = getAmount() / ((1 - (1/Math.pow(1 + monthlyRate, getLoanTerm()))) / (monthlyRate));
    return result;
  }
}
