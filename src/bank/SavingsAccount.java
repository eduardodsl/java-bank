package bank;

public class SavingsAccount extends Account{

    public SavingsAccount(Customer customer){
        super(0, customer);
        this.accTypeName = "Savings Account";
    }

}
