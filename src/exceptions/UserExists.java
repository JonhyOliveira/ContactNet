package exceptions;
//Throws this exception when a user exists but the action that was called needs the user to not be created yet
public class UserExists extends Throwable {

    private static final String DEFAULT_MSG = "The user already exists.";

    public UserExists( )
    {
        super(DEFAULT_MSG);
    }

    public UserExists( String message )
    {
        super(message);
    }
}
