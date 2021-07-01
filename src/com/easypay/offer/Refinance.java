package com.easypay.offer;

public class Refinance extends LoanOffer {
  private final LoanOfferType loanOfferType = LoanOfferType.REFINANCE;
  private int loanTerm;
  private double propertyTax;
  private double houseInsurance;
  private double rate;
  private double amount;
  private int creditScore;
  private int id;


  // constructors
  Refinance(LoanOfferType loanOfferType, int loanTerm, double amount, int creditScore, double propertyTax, double houseInsurance, int id) {
    super(loanOfferType, loanTerm, amount, creditScore);
    setPropertyTax(propertyTax);
    setHouseInsurance(houseInsurance);
    setId(id);
  }
  // Business methods
  @Override
  public double calculate() {
    double result = 0;
    double monthlyRate = getRate() / 12;
    int numberOfPayments = getLoanTerm();
    // Equation for mortgage payments
    result += getAmount() * ((monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) / (Math.pow((1 + monthlyRate), numberOfPayments) - 1));
    result += getPropertyTax();
    result += getHouseInsurance();
    return result;
  }

  public double getRate() {
    return rate;
  }

  @Override
  public void setRate() {
    if (getCreditScore() >= 800 && getCreditScore() <= 850) {
      this.rate = 0.025;
    } else if (getCreditScore() >= 750 && getCreditScore() <= 799) {
      this.rate = 0.02875;
    } else if (getCreditScore() >= 700 && getCreditScore() <= 749) {
      this.rate = 0.03;
    } else if (getCreditScore() >= 650 && getCreditScore() <= 699) {
      this.rate = 0.035;
    } else if (getCreditScore() >= 600 && getCreditScore() <= 649) {
      this.rate = 0.04;
    } else {
      this.rate = 0.05;
    }
  }

  public double getPropertyTax() {
    return propertyTax;
  }

  public void setPropertyTax(double propertyTax) {
    this.propertyTax = propertyTax;
  }

  public double getHouseInsurance() {
    return houseInsurance;
  }

  public void setHouseInsurance(double houseInsurance) {
    this.houseInsurance = houseInsurance;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public LoanOfferType getLoanOfferType() {
    return loanOfferType;
  }

  @Override
  public int getLoanTerm() {
    return loanTerm;
  }

  @Override
  public void setLoanTerm(int loanTerm) {
    this.loanTerm = loanTerm;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  @Override
  public double getAmount() {
    return amount;
  }

  @Override
  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public int getCreditScore() {
    return creditScore;
  }

  @Override
  public void setCreditScore(int creditScore) {
    this.creditScore = creditScore;
  }

  @Override
  public String toString() {
    return "Loan Offer ID: " + getId() + ", Loan Type: " + loanOfferType + ", New Monthly Payment $" + String.format("%.2f", this.calculate()) + ", Rate: " + String.format("%.2f", getRate() * 100)
        + "% , Loan Term: " + getLoanTerm();
  }
}
