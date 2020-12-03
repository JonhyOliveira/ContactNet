package exceptions;
//Throws this exception when a certain user that supposedly exists, does not exist
public class ContactNotExists extends Throwable {

    private static final String DEFAULT_MSG = "The contact does not exist.";

    public ContactNotExists( )
    {
        super(DEFAULT_MSG);
    }

    public ContactNotExists( String message )
    {
        super(message);
    }
}
