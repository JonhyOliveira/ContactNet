package exceptions;
//Throws this exception when a certain user is not a subscriber of a certain group
public class SubscriptionNotExists extends Throwable {

    private static final String DEFAULT_MSG = "The group subscription does not exist.";

    public SubscriptionNotExists( )
    {
        super(DEFAULT_MSG);
    }

    public SubscriptionNotExists( String message )
    {
        super(message);
    }
}
