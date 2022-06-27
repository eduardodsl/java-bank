import java.util.Scanner;

import actions.*;
import bank.Bank;
import bank.Customer;
import bank.Session;
import exceptions.CustomerException;
import exceptions.WrongLineFormatException;
import templates.Index;
import utils.DB;
import utils.Format;

/**
 * <h1>Java Bank</h1>
 * <p>
 * This app was originally developed to for the DIO/TQI bootcamp challenge and oficially my first Java application, it creates a single bank instance
 * that represents a database containing customers, accounts and transactions
 * </p>
 * <h2>Current Features</h2>
 * <ul>
 * <li>Customers have two hardcoded bank accounts (checking and savings)</li>
 * <li>Customers can deposit and withdraw money to/from their own account</li>
 * <li>Customers can transfer money to their own accounts and to other customers' accounts</li>
 * <li>Customers can print reports of all transacions in one of their bank accounts</li>
 * </ul>
 * @author Eduardo Augusto da Silva Leite<eduardodsl@gmail.com>
 */
public class App {
    
    public static void main(String[] args) throws Exception {

        Bank bank = DB.makeBank("Java Bank", DB.makeCustomers());
        Format.println(Index.mainHeader("Welcome to "+ bank.name));
        Format.println(Index.loginMessage());
        Scanner scan = new Scanner(System.in);
        Format.print(Index.cursor());
        String credentials = scan.nextLine();
        
        try {

            Customer customer = DB.authCustomer(bank, credentials);
            Session session = DB.startSession(bank, customer);
            boolean exit = false;            

            Format.cls();

            while(!exit){

                Format.println(Index.mainHeader("Welcome to "+ bank.name));
                Format.println(Index.displayAccounts(session.getAccounts()));
                Format.println(Index.mainOptions(customer.fullName()) +" \n");
                Format.print(Index.cursor());

                String action = scan.nextLine();

                if(action.equals("1")) MainActions.selfTransferAction(session, scan);
                if(action.equals("2")) MainActions.transferAction(session, scan);
                if(action.equals("3")) MainActions.withdrawalAction(session, scan);
                if(action.equals("4")) MainActions.depositAction(session, scan);
                if(action.equals("5")) ReportActions.reportAction(session, scan);
                if(action.equals("x")) exit = true;

            }

        } catch (WrongLineFormatException e){
            Format.println("Please, check again if you provided both login and password separated by one space", "yellow");
        } catch (CustomerException e){
            Format.println("Login or password incorrect, please try again", "red");
        }

        scan.close();

    }

}
