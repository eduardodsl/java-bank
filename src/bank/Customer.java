package bank;

/**
 * Customer represents an bank customer
 */
public class Customer implements IIdentifiable, IPrintable {

    protected int id;
    protected String name;
    protected String surname;
    protected String login;
    protected String password;
    
    public Customer(String name, String surname, String login, String password){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    /**
     * Fetches customer's full name
     * @return
     */
    public String fullName(){
        return this.name + " " + this.surname;
    }

    public void setId(int value){
        this.id = value;
    }

    public int getId(){
        return this.id;
    }

    public String print(){
        String ret = "";
        ret += "Customer ["+this.login+"] "+ this.fullName();
        return ret;
    }

}
