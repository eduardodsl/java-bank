package bank;

import utils.Calc;

/**
 * It represents money deposit in only one target account
 */
public class Deposit extends Transaction{
    public Deposit(Account from, int value){
        super(from, value);
        this.transactionType = 1;
    }

    @Override
    public String print(){
        String ret = "";
        ret += "Transaction ["+this.id+"] R$"+Calc.convertToReal(this.value)+"\n";
        ret += "Deposit on ATM";
        return ret;
    }

}