package bank;

/**
 * An checking account based on the Account class
 */
public class CheckingAccount extends Account {

    CheckingAccount(Customer customer){
        super(1, customer);
        this.accTypeName = "Checking Account";
    }
}
