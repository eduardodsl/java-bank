package actions;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import bank.Account;
import bank.Session;
import bank.Transaction;
import exceptions.DataNotFoundException;
import templates.Index;
import utils.DB;
import utils.Format;

public class ReportActions {
    
    public static void reportAction(Session session, Scanner scan){
        Format.cls();

        Format.println(Index.mainHeader("ACCOUNT REPORT"));

        Format.println(Index.displayAccounts(session.getAccounts()));
        Format.println("Input your account ID to see its transfers report:");
        Format.println("EXAMPLE: 10\n");
        Format.print(Index.cursor());
        
        try {
            int accountID = scan.nextInt();
            Account acc = DB.fetchAccountById(session.getBank(), accountID);
            if(acc.getOwnerId() == session.getCustomer().getId()){
                
                String report = "";

                List<Transaction> transactions = DB.fetchTransactions(session.getBank(), acc);
                report += Index.mainHeader("ACCOUNT REPORT");
                report += acc.print()+"\n";
                Iterator<Transaction> it = transactions.iterator();
                while(it.hasNext()){
                    Transaction next = it.next();
                    report += next.print()+"\n";
                    report += Index.separator()+"\n";
                }
                
                Format.print(report);
                Format.print("Press ENTER to exit report\n");
                
                scan.nextLine();
                scan.nextLine();

            }else{
                Format.println("This account doesn't belong to you", "yellow");
            }
        } catch (NumberFormatException e){
            Format.println("The account ID is not a valid number", "red");
        } catch (DataNotFoundException e) {
            Format.println("Account ID not found!", "red");
        }
    }

}
