package com.easypay.client;

import com.apps.util.Prompter;
import com.easypay.loan.*;
import com.easypay.offer.LoanOffer;
import com.easypay.offer.LoanOfferCart;
import com.easypay.offer.LoanOfferType;
import com.easypay.offer.Refinance;


import java.util.*;

public class LoanClient {
    private String name;
    private int creditScore;
    private String email;
    private ShortTermLoanCart shortTermLoanCart = new ShortTermLoanCart();
    private MortgageCart mortgageCart = new MortgageCart();
    private LoanOfferCart offerCart = new LoanOfferCart();
    private List<LoanOffer> selectedOffers = new ArrayList<>();

    // constructor
    public LoanClient(String name, int creditScore, String email) {
        setCreditScore(creditScore);
        setName(name);
        setEmail(email);
    }

    // Business Methods
    public void addShortTermLoan(ShortTermLoanType loanType) {
        shortTermLoanCart.addShortTermLoan(loanType);
    }

    public void addMortgage() {
        mortgageCart.addMortgage();
    }

    public double displayDebt() {
        System.out.println(this.getName() +
            "\n\nYour monthly debt summary is:" +
            "\n================================================================================================================================================");
        shortTermLoanCart.displayShortTermLoans();
        mortgageCart.displayMortgages();
        double monthTotal = shortTermLoanCart.shortTermMonthlyTotal() + mortgageCart.mortgageMonthlyTotal();
        System.out.println("The total monthly payment of your current debt: $" + String.format("%.2f", monthTotal) +
            "\n================================================================================================================================================");
        return monthTotal;
    }

    public void generateOffers() {
        offerCart.addLoanOffer(shortTermLoanCart, mortgageCart, creditScore);
    }

    public void displayOffer() {
        offerCart.display();
    }

    public double chooseOffer(Prompter prompter) {
        System.out.println("\n\nHi, " + getName() +
            "\n\nIt's time to choose your offers!!" +
            "\n================================================================================================================================================\n");
        chooseRefinance(prompter);
        chooseConsolidated(prompter);
        double newMonthlyTotal = displaySelected();
        return newMonthlyTotal;
    }

    private void chooseRefinance(Prompter prompter) {
        List<LoanOffer> mortgages = new ArrayList<>();
        for (LoanOffer offer : offerCart.offers) {
            if (offer != null && offer.getLoanOfferType().equals(LoanOfferType.REFINANCE)) {
                mortgages.add(offer);
            }
        }
        for (int i = 0; i < mortgages.size(); i += 2) {
            LoanOffer offer15 = mortgages.get(i);
            LoanOffer offer30 = mortgages.get(i + 1);
            int id = ((Refinance) offer15).getId();
            System.out.println("================================================================================================================================================");
            System.out.println("Refinance Mortgage #" + id +
                "\n[0]None" +
                "\n[1]15 years " +
                "\n[2]30 years ");
            String chosenOffer = prompter.prompt("Response for refinance offer #" + id + ": ",
                "[0-2]", "That is not a valid response for the offer");
            switch (chosenOffer) {
                case "0":
                    String sendOffer = prompter.prompt("Do you want me to send an email for further consideration?[Y/N]:", "[Yy]\\w+|[Yy]|[Nn]\\w+|[Nn]", "That is not a valid response");
                    if (sendOffer.equals("Y") || sendOffer.equals("y")) {
                        System.out.println("Detected email on file... Email Sent!\n");
                    }
                    break;
                case "1":
                        selectedOffers.add(offer15);
                    break;
                case "2":
                        selectedOffers.add(offer30);
                    break;
            }
        }
    }

    private void chooseConsolidated(Prompter prompter) {
        List<LoanOffer> consolidated = new ArrayList<>();
        for (LoanOffer offer : offerCart.offers) {
            if (offer != null && offer.getLoanOfferType().equals(LoanOfferType.SHORT_TERM_CONSOLIDATION)) {
                consolidated.add(offer);
            }
        }
        if (!consolidated.isEmpty()) {
            System.out.println("Short term loan consolidation" +
                "\n[0]None" +
                "\n[1]12 months " +
                "\n[2]24 months " +
                "\n[3]36 months " +
                "\n[4]48 months " +
                "\n[5]60 months " +
                "\n[6]72 months ");
            String chosenOffer = prompter.prompt("Response for short term consolidation offer ", "[0-6]", "That is not a valid response");
            switch (chosenOffer) {
                case "0":
                    String sendOffer = prompter.prompt("Do you want me to send an email for further consideration?[Y/N]:", "[Yy]\\w+|[Yy]|[Nn]\\w+|[Nn]", "That is not a valid response");
                    if (sendOffer.equals("Y") || sendOffer.equals("y")) {
                        System.out.println("Detected email on file... Email Sent!\n");
                    }
                    break;
                case "1":
                    selectedOffers.add(consolidated.get(0));
                    break;
                case "2":
                    selectedOffers.add(consolidated.get(1));
                    break;
                case "3":
                    selectedOffers.add(consolidated.get(2));
                    break;
                case "4":
                    selectedOffers.add(consolidated.get(3));
                    break;
                case "5":
                    selectedOffers.add(consolidated.get(4));
                    break;
                case "6":
                    selectedOffers.add(consolidated.get(5));
                    break;
            }
        }
    }

    private double displaySelected() {
        double sumTotal = 0;
        for (LoanOffer offer : selectedOffers) {
            System.out.println(offer);
            sumTotal += offer.calculate();
        }
        System.out.println("================================================================================================================================================\n\n");
        System.out.println("Congratulations your total consolidated monthly payment is now: $" + String.format("%.2f", sumTotal));
        return sumTotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
