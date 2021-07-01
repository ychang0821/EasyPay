package com.easypay.offer;

public class LoanOfferFactory {
  LoanOfferFactory () {}

  public static LoanOffer createShortTermLoanOffer(LoanOfferType loanOfferType, int loanTerm, double amount, int creditScore) {
    LoanOffer offer = null;
    offer = new ShortTermConsolidation(loanOfferType, loanTerm, amount, creditScore);
    return offer;
  }

  public static LoanOffer createRefinance(LoanOfferType loanOfferType, int loanTerm, double amount, int creditScore, double propertyTax, double houseInsurance, int id) {
    LoanOffer offer = null;
    offer = new Refinance(loanOfferType, loanTerm, amount, creditScore, propertyTax, houseInsurance, id);
    return offer;
  }
}
