package com.easypay.loan;

public class MortgageFactory {
  private MortgageFactory() {};

  public static Mortgage createMortgage(double amount, int loanTerm,  double rate, int id, double propTaxes, double houseInsurance) {
    Mortgage mortgage = new Mortgage(amount, loanTerm,  rate, id, propTaxes, houseInsurance);
    return mortgage;
  }
}
