package actions;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import bank.Account;
import bank.Session;
import bank.Transaction;
import exceptions.*;
import templates.Index;
import utils.Calc;
import utils.DB;
import utils.Format;

public class MainActions {

    public static void selfTransferAction(Session session, Scanner scan){
        
        Format.cls();

        Format.println(Index.mainHeader("TRANSFER BETWEEN YOUR ACCOUNTS"));

        Format.println(Index.displayAccounts(session.getAccounts()));
        Format.println("Write the account ID, deposit account ID and the ammount to trasfer separated by spaces:");
        Format.println("EXAMPLE: 9 10 900.4\n");
        Format.print(Index.cursor());
        
        try {

            String[] line = Format.splitLine(scan.nextLine(), 3);
            int fromAccountID = Integer.parseInt(line[0]);
            int toAccountID = Integer.parseInt(line[1]);
            int money = Calc.convertToCents(line[2]);
            Account fromAcc = DB.fetchAccountById(session.getBank(), fromAccountID);
            Account toAcc = DB.fetchAccountById(session.getBank(), toAccountID);

            boolean isSameOwner = (fromAcc.getOwnerId() == session.getCustomer().getId()) && (toAcc.getOwnerId() == session.getCustomer().getId());
            boolean isSameAccount = (toAcc.getId() == fromAcc.getId());

            if(!isSameOwner){
                Format.println("Both accounts must belong to you");
            }else if (isSameAccount){
                Format.println("You must send to a different account");
            }else{
                Transaction transaction = DB.makeTransfer(session.getBank(), fromAcc, toAcc, money);
                Format.println(Index.transactionDone(transaction));
            }
            
        } catch (WrongLineFormatException e){
            Format.println("Please, check again if you provided the account ID, deposit account ID and the ammount to trasfer separated by spaces", "red");
        } catch (NumberFormatException e){
            Format.println("The account number or money value are not valid numbers", "red");
        } catch (DataNotFoundException e) {
            Format.println("Account ID not found!", "red");
        } catch (NotEnoughBalanceException e) {
            Format.println("You don't have enough funds for this action!", "red");
        }
        
    }

    public static void transferAction(Session session, Scanner scan){
        
        Format.cls();

        Format.println(Index.mainHeader("TRANSFER TO ANOTHER CUSTOMER ACCOUNT"));

        Format.println(Index.displayAccounts(session.getAccounts()));
        Format.println("Write your account ID, receiving account ID and the ammount to transfer separated by spaces:");
        Format.println("EXAMPLE: 11 20 182.5\n");

        List<Account> accounts = session.getBank().getAccounts();
        int CustomerId = session.getCustomer().getId();

        Iterator<Account> it = accounts.iterator();
        String availableAccounts = "";
        while(it.hasNext()){
            Account next = it.next();
            if(next.getOwner().getId() != CustomerId){
                availableAccounts += String.format(
                    "[%d] %s - %s\n",
                    next.getId(),
                    next.getAccTypeName(),
                    next.getOwner().fullName()
                );
            }
        }
        
        if(availableAccounts != ""){
            Format.println("Available accounts: ");
            Format.println(availableAccounts);
        }else{
            Format.println("No accounts found!", "red");
        }

        Format.print(Index.cursor());
        
        try {
            String[] line = Format.splitLine(scan.nextLine(), 3);
            int fromAccountID = Integer.parseInt(line[0]);
            int toAccountID = Integer.parseInt(line[1]);
            int money = Calc.convertToCents(line[2]);
            Account fromAcc = DB.fetchAccountById(session.getBank(), fromAccountID);
            Account toAcc = DB.fetchAccountById(session.getBank(), toAccountID);
            if((fromAcc.getOwnerId() == session.getCustomer().getId()) && (toAcc.getOwnerId() != session.getCustomer().getId())){
                Format.println("You are about to transfer R$"+line[2]+" to "+toAcc.getOwner().fullName()+". Input y and ENTER to confirm\n");
                String option = scan.nextLine();
                if(option.equals("y")){
                    Transaction transaction = DB.makeTransfer(session.getBank(), fromAcc, toAcc, money);
                    Format.println(Index.transactionDone(transaction));
                }else{
                    Format.println("Operation aborted");
                }
            }else{
                Format.println("The sending account must be yours, and the receiving account from another customer", "red");
            }
        } catch (WrongLineFormatException e){
            Format.println("Please, check again if you provided the account ID, deposit account ID and the ammount to trasfer separated by spaces", "red");
        } catch (NumberFormatException e){
            Format.println("The account number or money value are not valid numbers", "red");
        } catch (DataNotFoundException e) {
            Format.println("Account ID not found!", "red");
        } catch (NotEnoughBalanceException e) {
            Format.println("You don't have enough funds for this action!", "red");
        }
        
    }

    public static void withdrawalAction(Session session, Scanner scan){
        
        Format.cls();

        Format.println(Index.mainHeader("FUNDS WITHDRAWAL"));

        Format.println(Index.displayAccounts(session.getAccounts()));
        Format.println("Write the account ID followed by the ammount of money to withdral:");
        Format.println("EXAMPLE: 10 900.4\n");
        Format.print(Index.cursor());
        
        try {
            String[] line = Format.splitLine(scan.nextLine());
            int accountID = Integer.parseInt(line[0]);
            int money = Calc.convertToCents(line[1]);
            Account acc = DB.fetchAccountById(session.getBank(), accountID);
            if(acc.getOwnerId() == session.getCustomer().getId()){
                Transaction transaction = DB.makeWithdrawal(session.getBank(), acc, money);
                Format.println(Index.transactionDone(transaction));
            }else{
                Format.println("This account doesn't belong to you");
            }
        } catch (WrongLineFormatException e){
            Format.println("Please, check again if you provided both account ID and ammount separated by one space", "red");
        } catch (NumberFormatException e){
            Format.println("The account number or money value are not valid numbers", "red");
        } catch (DataNotFoundException e) {
            Format.println("Account ID not found!", "red");
        } catch (NotEnoughBalanceException e) {
            Format.println("You don't have enough funds for this action!", "red");
        }
        
    }

    public static void depositAction(Session session, Scanner scan){
        
        Format.cls();

        Format.println(Index.mainHeader("FUNDS DEPOSIT"));

        Format.println(Index.displayAccounts(session.getAccounts()));
        Format.println("Input your account ID followed by the ammount of money to deposit:");
        Format.println("EXAMPLE: 10 900.4\n");
        Format.print(Index.cursor());
        
        try {
            String[] line = Format.splitLine(scan.nextLine());
            int accountID = Integer.parseInt(line[0]);
            int money = Calc.convertToCents(line[1]);
            Account acc = DB.fetchAccountById(session.getBank(), accountID);
            if(acc.getOwnerId() == session.getCustomer().getId()){
                Transaction transaction = DB.makeDeposit(session.getBank(), acc, money);
                Format.println(Index.transactionDone(transaction));
            }else{
                Format.println("This account doesn't belong to you", "yellow");
            }
        } catch (WrongLineFormatException e){
            Format.println("Please, check again if you provided both account ID and ammount separated by one space", "red");
        } catch (NumberFormatException e){
            Format.println("The account number or money value are not valid numbers", "red");
        } catch (DataNotFoundException e) {
            Format.println("Account ID not found!", "red");
        }
        
    }
}
