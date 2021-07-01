package com.easypay.offer;

public abstract class LoanOffer {
    private double amount;
    private int creditScore;
    private double rate;
    private int loanTerm;
    private LoanOfferType loanOfferType;

    LoanOffer(LoanOfferType loanOfferType, int loanTerm, double amount, int creditScore) {
        setLoanOfferType(loanOfferType);
        setLoanTerm(loanTerm);
        setAmount(amount);
        setCreditScore(creditScore);
        setRate();
    }

    //Business Method:
    public abstract double calculate();

    // getter and setter
    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public double getRate() {
        return rate;
    }

    public abstract void setRate();

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LoanOfferType getLoanOfferType() {
        return loanOfferType;
    }

    public void setLoanOfferType(LoanOfferType loanOfferType) {
        this.loanOfferType = loanOfferType;
    }
}
