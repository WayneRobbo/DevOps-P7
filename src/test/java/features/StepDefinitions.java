package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.Account;
import revolut.DebitCard;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;
    DebitCard debitCard;
    PaymentService cvvPin;


    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        debitCard = new DebitCard(111);

    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance);
        //double newAccountBalance = danny.getAccountBalance("EUR");
        //System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.topUpAmount = topUpAmount;
        //throw new io.cucumber.java.PendingException();
    }

    //@Given("Danny selects his {word} as his topUp method")
    @Given("Danny selects his {paymentService} as his topUp method")
    //public void danny_selects_his_debit_card_as_his_top_up_method(String topUpSource) {
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(topUpAmount);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    // Additions to CA Data table
    @Given("Danny has a starting balance of {int}")
    public void danny_has_a_starting_balance_of(Integer initialBalance) {
        System.out.println("Give the Danny has starting balance of: " + initialBalance);
        //Act
        danny.setAccountBalance(initialBalance);
    }

    @When("Danny now tops up by {int}")
    public void danny_now_tops_up_by(Integer topUpAmount) {
        System.out.println("Tops up by " +  topUpAmount);
        //Act
        danny.getAccount("EUR").addFunds(topUpAmount);
    }

    @Then("^The balance in his euro account should be (.*)$")
    public void the_balance_in_his_euro_account_should_be(Integer newBalance) {
        //Arrange
        double expectedResult = newBalance;

        //Act
        double actualResult = (danny.getAccount("EUR").getBalance());
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    // CA Part 2 - implement the additional scenarios, failed topUp request.
    @Given("Danny DebitCard has a CVV pin of {int}")
    public void danny_debit_card_has_a_cvv_pin_of(Integer cvvPin) {
        //Act
        debitCard.setCvvPin(cvvPin);
        System.out.println("Danny debit card cvv pin is: " + debitCard.getCvvPin());
    }

    @Given("Danny has a revolut balance of {int}")
    public void danny_has_a_revolut_balance_of(Integer initialBalance) {
        //Act
        danny.setAccountBalance(initialBalance);
        System.out.println("Revolut balance: " + danny.getAccountBalance("EUR"));
    }

    @When("Danny enters top amount of {int} using a CVV of {int}")
    public void danny_enters_top_amount_of_using_a_cvv_of(Integer topUpAmount, Integer cvvPin){
        //Act
        danny.topUpAccount(topUpAmount,cvvPin, debitCard, topUpMethod);
    }

    @Then("The revolut account balance remains {int}")
    public void the_revolut_account_balance_remains(Integer newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccountBalance("EUR");
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("Danny's balance is: " + actualResult);
    }

    // CA Part 2 - implement the additional scenarios, successful topUp request.
    @Then("The revolut account balance increase to {int}")
    public void the_revolut_account_balance_increase_to(Integer newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccountBalance("EUR");
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("Danny's balance is: " + actualResult);
    }
}