package com.easypay.client;

import com.apps.util.Prompter;
import com.easypay.loan.ShortTermLoanType;

import java.util.Scanner;

class Controller {

  public void initialize() {
    Prompter prompter = new Prompter(new Scanner(System.in));

    String name = prompter.prompt("Please enter your name: ");                      //make the regex for these
    String creditScore = prompter.prompt("Please enter your credit score: ", "[0-9][0-9][0-9]", "That is not a valid credit score");
    String email = prompter.prompt("Please enter your email: ", "^(.+)@(.+)$", "That is not a valid email");

    int score = Integer.parseInt(creditScore);
    LoanClient client = new LoanClient(name, score, email);

    // collect information from user
    input(prompter, client);
    // Display the debt of user based on the input information
    double currentMonthlyTotal = client.displayDebt();
    // Generate short term consolidated offer and refinance offer
    client.generateOffers();
    // Display the short term consolidated offer and refinance offer
    client.displayOffer();
    //let the user choose the choices for short term and refinance
    double newMonthlyTotal = client.chooseOffer(prompter);
    System.out.println("\nYour total saving is: $" + String.format("%.2f", (currentMonthlyTotal - newMonthlyTotal)) + " per month");
  }

  // collect info from user
  private void input(Prompter prompter, LoanClient client) {
    String name = client.getName();
    boolean again = true;
    while (again) {
      String loanResponse = prompter.prompt("\n" + name + " do you have any debt?\n" +

          "\n[0]I have no additional debt" +                                          //if they press no it should exit the program entirely
          "\n[1]Personal " +
          "\n[2]Auto " +
          "\n[3]Credit Card " +
          "\n[4]Student " +
          "\n[5]Mortgage" +
          "\n\nResponse: ", "[0-5]", "That is not a valid response to the question");

      switch (loanResponse) {
        case "0":
          again = false;
          break;
        case "1":
          client.addShortTermLoan(ShortTermLoanType.PERSONAL_LOAN);
          break;
        case "2":
          client.addShortTermLoan(ShortTermLoanType.AUTO_LOAN);
          break;
        case "3":
          client.addShortTermLoan(ShortTermLoanType.CREDIT_CARD_LOAN);
          break;
        case "4":
          client.addShortTermLoan(ShortTermLoanType.STUDENT_LOAN);
          break;
        case "5":
          client.addMortgage();
          break;
      }
    }
  }
}
