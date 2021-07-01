package com.easypay.loan;

class PersonalLoan extends ShortTermLoan {
    private double amount;
    private double rate;
    private int loanTerm;
    private int id;
    private final ShortTermLoanType type = ShortTermLoanType.PERSONAL_LOAN;
    PersonalLoan(ShortTermLoanType type, double amount, int loanTerm, double rate, int id) {
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
