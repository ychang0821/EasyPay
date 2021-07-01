package com.easypay.loan;

public class Mortgage {
  // Fields
  private double amount;
  private double rate;
  private int loanTerm;
  private int id;
  private double propertyTax;
  private double houseInsurance;

  // constructor
  Mortgage(double amount, int loanTerm,  double rate, int id, double propTaxes, double houseInsurance) {
    setAmount(amount);
    setLoanTerm(loanTerm);
    setRate(rate);
    setId(id);
    setPropertyTax(propTaxes);
    setHouseInsurance(houseInsurance);
  }

  // Business methods
  public double calculate() {
    double result = 0;
    double monthlyRate = getRate() / 12;
    int numberOfPayments = getLoanTerm(); // ask client to input number of month
    // Equation for mortgage payments
    result = getAmount() * ((monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) / (Math.pow((1 + monthlyRate), numberOfPayments) - 1));
    result += getPropertyTax();
    result += getHouseInsurance();
    return result;
  }

  // Getters and Setters
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
    this.rate = rate;
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

  public void setPropertyTax(double propertyTax) {
    this.propertyTax = propertyTax;
  }

  public void setHouseInsurance(double houseInsurance) {
    this.houseInsurance = houseInsurance;
  }

  public double getPropertyTax() {
    return propertyTax;
  }

  public double getHouseInsurance() {
    return houseInsurance;
  }

  @Override
  public String toString() {
    return "Loan ID: " + getId() + ", Loan Type: Mortgage" + ", Amount: $" + String.format("%.2f", getAmount()) + ", Rate: " + String.format("%.3f", getRate() * 100) + "%" + ", Loan Term: " + getLoanTerm()
        + " month, Property Tax: $" + String.format("%.2f", getPropertyTax()) + ", House Insurance: $" + String.format("%.2f", getHouseInsurance()) + ", Monthly Payment: $" + String.format("%.2f", this.calculate());
  }
}
