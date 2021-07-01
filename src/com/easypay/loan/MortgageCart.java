package com.easypay.loan;

import com.apps.util.Prompter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MortgageCart {
  // storage for the long term loans (Mortgage)
  public List<Mortgage> Mortgages = new ArrayList<>();

  public void addMortgage() {
    Prompter prompter = new Prompter(new Scanner(System.in));
    String remainingPrincipal = prompter.prompt("Please enter how much you have left on your mortgage: ", "^[+]?([.]\\d+|\\d+[.]?\\d*)$", "That's not a valid amount");
    Double amount = Double.valueOf(remainingPrincipal);

    String rate = prompter.prompt("Please enter your mortgage rate: ", "^[+]?([.]\\d+|\\d+[.]?\\d*)$", "This's not a valid mortgage rate"); //TODO FIX it
    Double currentRate = Double.valueOf(rate) / 100;

    String loanTerm = prompter.prompt("Please enter your mortgage term in years: ", "^[+]?([.]\\d+|\\d+[.]?\\d*)$", "That's not a valid mortgage term (in years)");
    int loanTermPeriod = (int)(Double.parseDouble(loanTerm) * 12);


    String propTaxes = prompter.prompt("Please enter how much you pay for property taxes per month: ", "^[+]?([.]\\d+|\\d+[.]?\\d*)$", "That's not a valid amount");
    Double propertyTax = Double.valueOf(propTaxes);

    String houseInsurance = prompter.prompt("Please enter how much you pay for house insurance per month: ", "^[+]?([.]\\d+|\\d+[.]?\\d*)$", "That's not a valid amount");
    Double houseInsuranceNumber = Double.valueOf(houseInsurance);

    int id = Mortgages.size() + 1;

    Mortgages.add(MortgageFactory.createMortgage(amount, loanTermPeriod, currentRate, id, propertyTax, houseInsuranceNumber));
  }

  public void displayMortgages() {
    for (Mortgage mortgage : Mortgages) {
      System.out.println(mortgage);
    }
  }

  public double mortgageMonthlyTotal() {
    double result = 0;
    for (Mortgage mortgage : Mortgages) {
      result += mortgage.calculate();
    }
    return result;
  }
}
