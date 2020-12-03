package exceptions;
//Throws this exception when a group that supposedly did not exist, exists
public class GroupExists extends Throwable {

    private static final String DEFAULT_MSG = "The group already exists.";

    public GroupExists( )
    {
        super(DEFAULT_MSG);
    }

    public GroupExists( String message )
    {
        super(message);
    }
}
