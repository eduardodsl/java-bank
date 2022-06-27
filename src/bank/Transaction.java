package bank;

import utils.Calc;

/**
 * It represents a transaction on the bank database
 * @author Eduardo Augusto da Silva Leite
 */
public class Transaction implements IIdentifiable, IPrintable {
    
    protected int id;
    protected int value;
    protected int transactionType;
    protected Account from;

    /**
     * @param from the account that triggered the transaction
     * @param value the value to be transfered
     */
    public Transaction(Account from, int value){
        this.transactionType = -1;
        this.from = from;
        this.value = value;
    }

    public Account getFrom(){
        return this.from;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String print(){
        String ret = "";
        ret += "Transaction ["+this.id+"] R$"+String.format("%.2f", Calc.convertToReal(this.value));
        return ret;
    }

}
