package com.easypay.offer;

public enum LoanOfferType {
  REFINANCE("Refinance"),
  SHORT_TERM_CONSOLIDATION("Short Term Consolidation");

  public final String label;

  private LoanOfferType(String label) {
    this.label = label;
  }
}
