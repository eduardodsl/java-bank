package bank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exceptions.CustomerException;

/**
 * Bank holds together most of the application's data, like customers, accounts and transactions
 * @author Eduardo Augusto da Silva Leite
 */
public class Bank {
    
    private List<Customer> customerList;
    private List<Account> accountList;
    private List<Transaction> transactionList;
    public String name;

    public Bank(String name){
        this.name = name;
        this.customerList = new ArrayList<Customer>();
        this.accountList = new ArrayList<Account>();
        this.transactionList = new ArrayList<Transaction>();
    }

    /**
     * Checks if is a valid customer of this bank
     * @param login
     * @param password
     * @return Customer
     * @throws CustomerException
     */
    public Customer authCustomer(String login, String password) throws CustomerException {

        Iterator<Customer> it = this.customerList.iterator();

        while(it.hasNext()){
            Customer next = it.next();
            if((next.login.equals(login)) && (next.password.equals(password))){
                return next;
            }
        }

        throw new CustomerException("User \""+login+"\" not found!");

    }

    /**
     * Adds new customers to the bank
     * @param customers
     */
    public void addCostumers(Customer[] customers){
        for(int i = 0; i < customers.length; i++){
            
            customers[i].setId(this.accountList.size()+1);
            customerList.add(customers[i]);
            
            SavingsAccount savings = new SavingsAccount(customers[i]);
            CheckingAccount checking = new CheckingAccount(customers[i]);
            
            Account[] accounts = {
                savings,
                checking
            };

            this.addAccount(accounts);

        }
    }

    /**
     * Adds new accounts to the bank
     * @param accounts
     */
    public void addAccount(Account[] accounts){
        for(int i = 0; i < accounts.length; i++){
            accounts[i].setId(this.accountList.size()+1);
            accountList.add(accounts[i]);
        }
    }

    /**
     * Adds a single to the bank
     * @param transaction
     */
    public void addTransaction(Transaction transaction){
        transaction.setId(this.transactionList.size()+1);
        this.transactionList.add(transaction);
    }

    /**
     * Adds multiple transactions to the bank
     * @param transactions
     */
    public void addTransactions(Transaction[] transactions){
        for(int i = 0; i < transactions.length; i++){
            transactions[i].setId(this.transactionList.size()+1);
            transactionList.add(transactions[i]);
        }
    }

    /**
     * Fectches all accounts from the bank
     * @return
     */
    public List<Account> getAccounts(){
        return this.accountList;
    }

    /**
     * Fetches all transactions from the bank
     * @return
     */
    public List<Transaction> getTransactions(){
        return this.transactionList;
    }

}
