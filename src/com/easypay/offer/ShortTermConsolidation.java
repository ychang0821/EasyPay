package com.easypay.offer;

public class ShortTermConsolidation extends LoanOffer {
    private final LoanOfferType loanOfferType = LoanOfferType.SHORT_TERM_CONSOLIDATION;
    private int loanTerm;
    private double amount;
    private int creditScore;
    private double rate;

    // constructor
    ShortTermConsolidation(LoanOfferType loanOfferType, int loanTerm, double amount, int creditScore) {
        super(loanOfferType, loanTerm, amount, creditScore);
        setRate();
    }

    @Override
    public double calculate() {
        double result = 0;
        double monthlyRate = getRate() / 12;
        result = getAmount() / ((1 - (1/Math.pow(1 + monthlyRate, getLoanTerm()))) / (monthlyRate));
        return result;
    }

    @Override
    public void setRate() {
        if (getCreditScore() >= 800 && getCreditScore() <= 850) {
            this.rate = 0.03;
        } else if (getCreditScore() >= 750 && getCreditScore() <= 799) {
            this.rate = 0.04;
        } else if (getCreditScore() >= 700 && getCreditScore() <= 749) {
            this.rate = 0.05;
        } else if (getCreditScore() >= 650 && getCreditScore() <= 699) {
            this.rate = 0.06;
        } else if (getCreditScore() >= 600 && getCreditScore() <= 649) {
            this.rate = 0.08;
        } else {
            this.rate = 0.15;
        }
    }

    @Override
    public String toString() {
        return "Loan Type: " + getLoanOfferType() + ", New Monthly Payment: $" + String.format("%.2f", this.calculate()) + ", Rate: " + String.format("%.2f", getRate() * 100)
            + ", Loan Term: " + getLoanTerm();
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
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
