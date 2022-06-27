package bank;

import utils.Calc;

/**
 * It represents an transaction between two accounts
 * @author Eduardo Augusto da Silva Leite
 */
public class Transfer extends Transaction {
    
    protected Account from;
    protected Account to;
    
    public Transfer(Account from, Account to, int value){
        super(from, value);
        this.from = from;
        this.to = to;
        this.transactionType = 0;
    }

    public Account getTo(){
        return this.to;
    }

    @Override
    public String print(){
        String ret = "";
        ret += "Transaction ["+this.id+"] R$"+String.format("%.2f", Calc.convertToReal(this.value))+"\n";
        ret += "Transfer from...: "+this.from.getAccTypeName()+" ["+this.from.getId()+"]\n";
        ret += "Owned by........: "+this.from.getOwner().fullName()+"\n";
        ret += "to..............: "+this.to.getAccTypeName()+" ["+this.to.getId()+"]\n";
        ret += "Owned by........: "+this.to.getOwner().fullName();
        return ret;
    }

}
