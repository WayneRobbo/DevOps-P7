package revolut;

/* Wayne Robinson
   L00162024
   26/05/2021
 */

import java.util.Currency;
import java.util.HashMap;

public class Person {

    private String name;
    //Accounts currency & balance
    // EUR 30
    // USD 50
    // STG 30
    private HashMap<String, Account> userAccounts = new HashMap<String, Account>();

    public Person(String name){
        this.name = name;
        //Create a default euro account and add it the our userAccounts container
        Currency accCurrency = Currency.getInstance("EUR");
        Account euroAccount = new Account(accCurrency, 0);
        userAccounts.put("EUR", euroAccount);
    }

    public void setAccountBalance(double startingBalance) {
        userAccounts.get("EUR").setBalance(startingBalance);
    }

    public double getAccountBalance(String eur) {
        return userAccounts.get("EUR").getBalance();
    }

    public Account getAccount(String account) {

        return userAccounts.get(account);
    }

    public void topUpAccount(double topUpAmount, int cvvPin, DebitCard card, PaymentService verifyPin){
        if(verifyPin.pinApproval(card,cvvPin)){
            double tempBalance = userAccounts.get("EUR").getBalance() + topUpAmount;
            this.setAccountBalance(tempBalance);
            System.out.println("Successful cvvPin verification, top up balance successful");

        }else{
            System.out.println("Invalid cvvPin for debit card, attempted top up failed");
        }
    }

    public void sendMoney(double sendAmount, Person name, PaymentService verifyPin){
        //Get senders balance
        double balance = userAccounts.get("EUR").getBalance();

        //Verify if amount being sent is available in the balance
        if(balance >= sendAmount){

            //Removes funds from sender account
            userAccounts.get("EUR").removeFunds(sendAmount);

            //Get receivers balance
            double newBalance = userAccounts.get("EUR").getBalance();

            //Add funds to revivers account
            name.userAccounts.get("EUR").addFunds(sendAmount);

            System.out.println("Senders balance after sending funds:" + newBalance);
        }else{
            System.out.println("Insufficient Funds in senders account");
        }
    }
}
