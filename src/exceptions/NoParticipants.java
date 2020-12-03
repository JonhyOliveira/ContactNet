package exceptions;
//Throws this exception when there are no participants on a group
public class NoParticipants extends Throwable {

    private static final String DEFAULT_MSG = "The group has no participants.";

    public NoParticipants( )
    {
        super(DEFAULT_MSG);
    }

    public NoParticipants( String message )
    {
        super(message);
    }
}
