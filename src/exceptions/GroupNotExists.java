package exceptions;
//Throws this exception when a certain group that is trying to be used for something does not exist 
public class GroupNotExists extends Throwable {

    private static final String DEFAULT_MSG = "The group does not exist.";

    public GroupNotExists( )
    {
        super(DEFAULT_MSG);
    }

    public GroupNotExists( String message )
    {
        super(message);
    }
}
