package bank;

/**
 * Implements a way objects must be identified on the bank database
 * @author Eduardo Augusto da Silva Leite
 */
public interface IIdentifiable {
    
    /**
     * Defines an ID to this object
     * @param value
     */
    void setId(int value);
    /**
     * Returns this object's current ID
     * @return
     */
    int getId();

}
