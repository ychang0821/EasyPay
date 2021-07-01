package com.easypay.loan;

public abstract class ShortTermLoan {

  private double amount;
  private double rate;
  private int loanTerm;
  private int id;
  private ShortTermLoanType loanType;

  // constructor
  ShortTermLoan(ShortTermLoanType type, double amount, int loanTerm, double rate, int id){
    setAmount(amount);
    setLoanTerm(loanTerm);
    setRate(rate);
    setId(id);
    setLoanType(type);
  };

  // Business methods
  public abstract double calculate();

  // Getter and Setter
  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate / 100;
  }

  public int getLoanTerm() {
    return loanTerm;
  }

  public void setLoanTerm(int loanTerm) {
    this.loanTerm = loanTerm;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ShortTermLoanType getLoanType() {
    return loanType;
  }

  public void setLoanType(ShortTermLoanType loanType) {
    this.loanType = loanType;
  }
}

