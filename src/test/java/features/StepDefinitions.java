package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.DebitCard;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    PaymentService topUpMethod;
    Person danny, joe;
    DebitCard debitCard;


    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        joe = new Person("Joe");
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

    // CA Part 3 - implement the additional scenarios, b.Sending money to a friend using Revolut.
    @Given("Danny selects his Joe to send money to")
    public void danny_selects_his_joe_to_send_money_to() {
        System.out.println("Remove money for Danny's account");
    }
    @Given("Danny has a revolut balance of {int} euros")
    public void danny_has_a_revolut_balance_of_euros(Integer initialBalance) {
        danny.setAccountBalance(initialBalance);
        System.out.println("Starting balance: " + initialBalance);
    }
    @When("Danny sends {int} using the correct cvvPin {int} to joe")
    public void danny_sends_using_the_correct_cvv_pin_to_joe(Integer sendAmount, Integer cvvPin) {
        //Act
        danny.sendMoney(sendAmount,cvvPin,joe, topUpMethod);
    }

    @Then("Danny has a new revolut balance of {int}")
    public void danny_has_a_new_revolut_balance_of(Integer accountBalance) {
        //Arrange
        double expectedResult = accountBalance;

        //Act
        double actualResult = danny.getAccountBalance("EUR");

        //Assert
        Assert.assertEquals(expectedResult,actualResult,0);
        System.out.println("Dannys account balance after sending money is: " + actualResult);
    }

    //Verify money has been added to receivers account
    @Given("Joe has received money from Danny.")
    public void joe_has_received_money_from_danny() {
        System.out.println("Joe successfully receives money from Danny");
    }

    @Given("Joe already has an initial balance of {int}.")
    public void joe_already_has_an_initial_balance_of(Integer initialBalance) {
        //Arrange
        double expectedResults = initialBalance;

        //Act
        joe.setAccountBalance(initialBalance);
        double actualResult = joe.getAccountBalance("EUR");

        //Assert
        Assert.assertEquals(expectedResults, actualResult, 0);
        System.out.println("Joe's account balance:" + actualResult);
    }
    @When("Danny sends {int} euro using the correct cvvPin {int} to joe")
    public void danny_sends_euro_using_the_correct_cvv_pin_to_joe(Integer sendAmount, Integer cvvPin){
        danny.sendMoney(sendAmount,cvvPin,joe, topUpMethod);

    }
    @Then("Joe's account increases to {int}.")
    public void joe_s_account_increases_to(Integer balance) {
        //Arrange
        double expectedResult = balance;

        //Act
        double actualResult = joe.getAccountBalance("EUR");

        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        System.out.println("Joe's account balance:" + actualResult);
    }

    //Test insufficient amount in senders account. Check senders amount after action.
    @Given("Danny selects {int} euro to send to a friend.")
    public void danny_selects_euro_to_send_to_a_friend(Integer amount) {
        System.out.println("Danny attempts to send" + amount);
    }

    @Then("Danny still has a account balance of {int}.")
    public void danny_still_has_a_account_balance_of(Integer accountBalance) {
        //Arrange
        double expectedResult = accountBalance;

        //Act
        double actualResult = danny.getAccountBalance("EUR");

        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        System.out.println("Danny's account balance:" + actualResult);
    }

    @When("Danny sends {int} using the correct cvvPin {int} to joes account.")
    public void danny_sends_using_the_correct_cvv_pin_to_joes_account(Integer sendAmount, Integer cvvPin) {
        danny.sendMoney(sendAmount,cvvPin,joe, topUpMethod);
    }

    @Then("Joe's account should remain the same with {int}.")
    public void joe_s_account_should_remain_the_same_with(Integer accountBalance) {
        //Arrange
        double expectedResult = accountBalance;

        //Act
        double actualResult = joe.getAccountBalance("EUR");

        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        System.out.println("Joe's account balance:" + actualResult);
    }

    // Data Table
    @Given("Danny send money to joe.")
    public void danny_send_money_to_joe() {
        System.out.println("Danny sending money");
    }
}