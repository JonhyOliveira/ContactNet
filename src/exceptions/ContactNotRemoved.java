package exceptions;
//Throws this exception when a certain user1 is trying to remove a contacts but the user he is trying to remove is himself
public class ContactNotRemoved extends Throwable {

    private static final String DEFAULT_MSG = "The contact could not be removed.";

    public ContactNotRemoved( )
    {
        super(DEFAULT_MSG);
    }

    public ContactNotRemoved( String message )
    {
        super(message);
    }
}
