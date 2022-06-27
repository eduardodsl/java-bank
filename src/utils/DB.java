package utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bank.*;
import exceptions.*;

/**
 * DB contains helper functions aimed to query and manage the "bank database".
 * @author Eduardo Augsuto da Silva Leite
 */
public class DB {
    
    /**
     * Generates an bank to be seeded with customers
     * @param name name of the bank
     * @param customers
     * @return
     */
    public static Bank makeBank(String name, Customer[] customers){
        Bank bank = new Bank(name);
        bank.addCostumers(customers);
        return bank;
    }

    /**
     * Customer seed
     * @return Customer[]
     */
    public static Customer[] makeCustomers(){

        Customer[] customers = {
            new Customer("David", "Xanatos", "david", "123"),
            new Customer("Goliath", "the Gargoyle", "goliath", "789"),
            new Customer("Elisa", "Maza", "elisa", "456"),
        };

        return customers;

    }

    /**
     * Verifies if the user is a valid customer of the bank
     * @param bank the bank database
     * @param credentials login and password, separated by a space
     * @return
     * @throws WrongLineFormatException
     * @throws CustomerException
     */
    public static Customer authCustomer(Bank bank, String credentials) throws WrongLineFormatException, CustomerException {
        String[] loginPass = Format.splitLine(credentials);
        Customer customer =  bank.authCustomer(loginPass[0], loginPass[1]);
        return customer;
    }

    /**
     * Creates an customer session to the bank
     * @param bank the bank database
     * @param customer the customer from the bank
     * @return Session
     */
    public static Session startSession(Bank bank, Customer customer){
        
        List<Account> accounts = new ArrayList<Account>();

        bank.getAccounts().forEach( (account) -> {
            if(account.getOwnerId() == customer.getId()){
                accounts.add(account);
            }
        });

        Session session = new Session(bank, customer);

        return session;

    }

    /**
     * Removes funds from the given account
     * @param bank the bank database
     * @param from the account to remove the funds
     * @param value the value in cents
     * @return
     * @throws NotEnoughBalanceException
     */
    public static Transaction makeWithdrawal(Bank bank, Account from, int value) throws NotEnoughBalanceException{
        Transaction transaction = new Withdrawal(from, value);
        from.remBalance(value);
        makeTransaction(bank, transaction);
        return transaction;
    }

    /**
     * Adds funds to the given account
     * @param bank the bank database
     * @param to to the 
     * @param value the money ammount in cents
     * @return Transaction
     */
    public static Transaction makeDeposit(Bank bank, Account from, int value){
        Transaction transaction = new Deposit(from, value);
        from.addBalance(value);
        makeTransaction(bank, transaction);
        return transaction;
    }

    /**
     * Transfers data between two valid accounts
     * @param bank the bank database
     * @param from the account that is making the transference
     * @param to the account receiving the transference
     * @param value the money ammount in cents
     * @return Transaction
     * @throws NotEnoughBalanceException
     */
    public static Transaction makeTransfer(Bank bank, Account from, Account to, int value) throws NotEnoughBalanceException {
        Transaction transaction = new Transfer(from, to, value);
        from.remBalance(value);
        to.addBalance(value);
        makeTransaction(bank, transaction);
        return transaction;
    }

    /**
     * Adds a new transaction to the Bank
     * @param bank
     * @param transaction
     */
    public static void makeTransaction(Bank bank, Transaction transaction){
        bank.addTransaction(transaction);
    }
    
    /**
     * Find a single account by its registered ID
     * @param bank
     * @param id
     * @return Account
     * @throws DataNotFoundException
     */
    public static Account fetchAccountById(Bank bank, int id) throws DataNotFoundException {

        Iterator<Account> it = bank.getAccounts().iterator();
        while(it.hasNext()){
            Account next = it.next();
            if(next.getId() == id){
                return next;
            }
        }
        throw new DataNotFoundException("Account not found");

    }

    /**
     * Fetches all transactions from the bank done by a given account.
     * @param bank
     * @param account
     * @return List<Transaction>
     * @throws DataNotFoundException
     */
    public static List<Transaction> fetchTransactions(Bank bank, Account account) throws DataNotFoundException {
        
        List<Transaction> transactions = new ArrayList<>();

        Iterator<Transaction> it = bank.getTransactions().iterator();
        while(it.hasNext()){
            Transaction next = it.next();
            
            // gets deposits, withdrawals and transfers from this account
            if(next.getFrom().getId() == account.getId()){
                transactions.add(next);
            }

            // get transfers from other accounts to this account
            if(next instanceof Transfer){
                Transfer t = (Transfer)next;
                if(t.getTo().getId() == account.getId()){
                    transactions.add(next);
                }
            }

        }

        if(transactions.size() < 1){
            throw new DataNotFoundException("No transaction for account ["+account.getId()+"] found!");
        }

        return transactions;

    }

}
