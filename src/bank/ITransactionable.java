package bank;

import exceptions.NotEnoughBalanceException;

/**
 * ITransactionable defines classes that can operate in transactions
 * @author Eduardo Augusto da Silva Leite
 */
public interface ITransactionable {
    
    /**
     * Adds money to its balance
     * @param value the money ammount in cents
     * @return int
     */
    int addBalance(int value);

    /**
     * Removes money from its balance, it will throw an Exception if i is lower than 0
     * @param i to money ammount in cents
     * @return int
     * @throws NotEnoughBalanceException
     */
    int remBalance(int value) throws NotEnoughBalanceException;

}
