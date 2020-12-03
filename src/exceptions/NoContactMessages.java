package exceptions;
//Throws this exception when a certain user has no messages shared with another user
public class NoContactMessages extends Throwable {

    private static final String DEFAULT_MSG = "The user has no messages.";

    public NoContactMessages( )
    {
        super(DEFAULT_MSG);
    }

    public NoContactMessages( String message )
    {
        super(message);
    }
}
