package exceptions;
//Throws this exception when there are no messages registered on a certain group
public class NoGroupMessages extends Throwable {

    private static final String DEFAULT_MSG = "The group has no messages";

    public NoGroupMessages( )
    {
        super(DEFAULT_MSG);
    }

    public NoGroupMessages( String message )
    {
        super(message);
    }
}
