package com.easypay.loan;

public enum ShortTermLoanType {
  STUDENT_LOAN("Student"),
  CREDIT_CARD_LOAN("Credit Card"),
  AUTO_LOAN("Auto"),
  PERSONAL_LOAN("Personal");

  public final String label;

  private ShortTermLoanType(String label) {
    this.label = label;
  }
}
