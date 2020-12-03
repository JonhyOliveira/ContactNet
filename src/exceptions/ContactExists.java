package exceptions;
//Throws this exception when a certain user that supposedly does not exist, already exists
public class ContactExists extends Throwable {

    private static final String DEFAULT_MSG = "The contact already exists";

    public ContactExists( )
    {
        super(DEFAULT_MSG);
    }

    public ContactExists( String message )
    {
        super(message);
    }
}
