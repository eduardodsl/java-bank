package bank;

import utils.Calc;

/**
 * It represents money withdrawal in only one account
 */
public class Withdrawal extends Transaction {
    
    public Withdrawal(Account from, int value){
        super(from, value);
        this.transactionType = 2;
    }

    public String print(){
        String ret = "";
        ret += "Transaction ["+this.id+"] R$"+String.format("%.2f", Calc.convertToReal(this.value))+"\n";
        ret += "Withdrawal from ATM";
        return ret;
    }

}