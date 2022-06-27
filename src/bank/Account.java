package bank;

import exceptions.NotEnoughBalanceException;
import utils.Calc;

/**
 * Represents an bank account, a single customer can have multiple accounts that holds its own funds ammount
 * @author Eduardo Augusto da Silva Leite
 */
public class Account implements ITransactionable, IIdentifiable, IPrintable {

    private int id;
    private Customer owner;
    private int balance;
    private int accType;
    
    protected String accTypeName;
    
    public Account(int accType, Customer customer){
        this.owner = customer;
        this.balance = 0;
        this.id = 0;
        this.accType = accType;
        this.accTypeName = "";
    }

    public int addBalance(int i){
        this.balance += i;
        return this.balance;
    }

    public int remBalance(int i) throws NotEnoughBalanceException {
        
        int value = this.balance - i;

        if(value >= 0){
            this.balance -= i;
            return this.balance;
        }

        throw new NotEnoughBalanceException(String.format("The current balance of \"%d\" is not enough", this.balance));

    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    /**
     * Gets the string format of the account type
     * @return
     */
    public String getAccTypeName(){
        return this.accTypeName;
    }

    /**
     * Gets an integer representing the account type
     * @return
     */
    public int getAccType(){
        return this.accType;
    }

    /**
     * Gets the id of the owner of this account
     * @return
     */
    public int getOwnerId(){
        return this.owner.id;
    }

    /**
     * Gets the owner of this account
     * @return
     */
    public Customer getOwner(){
        return this.owner;
    }

    /**
     * Gets the current balance of this account
     * @return
     */
    public int getBalance(){
        return this.balance;
    }

    public String print(){
        
        String ret = "";
        ret += "Account ["+this.id+"] "+ this.accTypeName + "\n";
        ret += "\t-> Balance: "+String.format("R$%.2f", Calc.convertToReal(this.balance));
        return ret;

    }

}
