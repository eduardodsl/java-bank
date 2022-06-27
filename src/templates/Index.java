package templates;

import java.util.Iterator;
import java.util.List;

import bank.Account;
import bank.Transaction;
import utils.Format;

/**
 * Contains a some functions that helps with templating on the terminal
 * @author Eduardo Augusto da Silva Leite
 */
public class Index {
    
    public static String separator(){
        return "+--------------------------------------------------------------------------------------------------+";
    }

    public static String doubleSeparator(){
        return "====================================================================================================";
    }

    public static String mainHeader(String message){

        String ret = "";

        ret = doubleSeparator()+"\n";
        ret += message+"\n";
        ret += doubleSeparator()+"\n";

        return ret;

    }

    public static String loginMessage(){

        String ret = "";

        ret = "Please, provide your login and password separated by a space";

        return ret;

    }

    public static String displayAccounts(List<Account> accounts){
        
        String ret = "";

        ret += separator()+"\n";
        Iterator<Account> it = accounts.iterator();
        while(it.hasNext()){
            Account next = it.next();
            ret += next.print()+"\n";
            ret += separator()+"\n";
        }
        return ret;

    }

    public static String mainOptions(String name){

        String ret = "";

        ret += "Dear "+Format.color(name, "green")+", please enter the following option number to manage your account\n";
        ret += "1) transfer between your accounts;\n2) transfer to another customer account;\n";
        ret += "3) withdraw money;\n4) deposit money;\n";
        ret += "5) print transfers report;\nx) exit;";

        return ret;

    }

    public static String cursor(){

        return Format.color(">> ", "yellow");

    }

    public static String transactionDone(Transaction transaction){
        return Format.color("Transaction ["+transaction.getId()+"] done!", "green");
    }

}
