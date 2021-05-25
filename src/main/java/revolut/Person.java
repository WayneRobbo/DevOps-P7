package revolut;

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

    public void setAccountBalance(double startingBlanace) {
        userAccounts.get("EUR").setBalance(startingBlanace);
    }

    public double getAccountBalance(String eur) {
        return userAccounts.get("EUR").getBalance();
    }

    public Account getAccount(String account) {

        return userAccounts.get(account);
    }

    public void topUpAccount(double topUpAmount, int cvvPin, DebitCard card, PaymentService topUpRequest){

        if(topUpRequest.pinApproval(card,cvvPin)){
            double tempBalance = userAccounts.get("EUR").getBalance() + topUpAmount;
            this.setAccountBalance(tempBalance);
            System.out.println("Successful cvvPin verification, top up balance successful");

        }else{
            System.out.println("Invalid cvvPin for debit card, attempted top up failed");
        }
    }
}
