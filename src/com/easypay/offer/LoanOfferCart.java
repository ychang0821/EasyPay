package com.easypay.offer;

import com.easypay.loan.Mortgage;
import com.easypay.loan.MortgageCart;
import com.easypay.loan.ShortTermLoanCart;

import java.util.ArrayList;
import java.util.List;

public class LoanOfferCart {
  // storage for the Offers
  public List<LoanOffer> offers = new ArrayList<>();

  public void addLoanOffer(ShortTermLoanCart shortTermLoanCart, MortgageCart mortgageCart, int creditScore) {
    addConsolidatedShortTermOffers(shortTermLoanCart, creditScore);
    addMortgageOffers(mortgageCart, creditScore);
  }

  public void addConsolidatedShortTermOffers(ShortTermLoanCart shortTermLoanCart, int creditScore) {
    double shortTermTotalAmount = shortTermLoanCart.shortTermTotalAmount();
    if (shortTermTotalAmount != 0) {
      int[] loanTermArray = {12, 24, 36, 48, 60, 72};
      for (int loanTerm : loanTermArray) {
        this.offers.add(LoanOfferFactory.createShortTermLoanOffer(LoanOfferType.SHORT_TERM_CONSOLIDATION, loanTerm, shortTermTotalAmount, creditScore));
      }
    }
  }

  public void addMortgageOffers(MortgageCart mortgageCart, int creditScore) {
    List<Mortgage> mortgages = mortgageCart.Mortgages;
    for (Mortgage mortgage : mortgages) {
      double amount = mortgage.getAmount();
      double propertyTax = mortgage.getPropertyTax();
      double houseInsurance = mortgage.getHouseInsurance();
      int id = mortgage.getId();
      this.offers.add(LoanOfferFactory.createRefinance(LoanOfferType.REFINANCE, 180, amount, creditScore, propertyTax, houseInsurance, id));
      this.offers.add(LoanOfferFactory.createRefinance(LoanOfferType.REFINANCE, 360, amount, creditScore, propertyTax, houseInsurance, id));
    }
  }

  public void display() {
    System.out.println("Using EasyPay we can offer these rates! ");
    for (LoanOffer offer : offers) {
      System.out.println(offer);
    }
  }
}
