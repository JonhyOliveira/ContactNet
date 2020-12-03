package exceptions;
//Throws this exception when a certain user has no contacts
public class NoContacts extends Throwable {

    private static final String DEFAULT_MSG = "The user has no contacts.";

    public NoContacts( )
    {
        super(DEFAULT_MSG);
    }

    public NoContacts( String message )
    {
        super(message);
    }
}
