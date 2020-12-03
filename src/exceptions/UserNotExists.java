package exceptions;
//Throws this exception when a user that is trying to be accessed does not exist
public class UserNotExists extends Throwable {

    private static final String DEFAULT_MSG = "The user does not exist.";

    public UserNotExists( )
    {
        super(DEFAULT_MSG);
    }

    public UserNotExists( String message )
    {
        super(message);
    }
}
