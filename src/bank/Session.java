package bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Session represents an valid customer logged in the bank database
 * @author Eduardo Augusto da Silva Leite
 */
public class Session {
    
    protected Bank bank;
    protected Customer customer;

    public Session(Bank bank, Customer customer) {
        this.bank = bank;
        this.customer = customer;
    }

    public List<Account> getAccounts(){

        List<Account> accounts = new ArrayList<Account>();

        bank.getAccounts().forEach( (account) -> {
            if(account.getOwnerId() == customer.getId()){
                accounts.add(account);
            }
        });

        return accounts;

    }

    public Customer getCustomer(){
        return this.customer;
    }

    public Bank getBank(){
        return this.bank;
    }
    
}
