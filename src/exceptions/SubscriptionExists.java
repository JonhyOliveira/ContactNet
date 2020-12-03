package exceptions;
// throws this exception when a certain user already subscribes a certain 
//group and tries to make an action that is supposed of him not to be a subscriber
public class SubscriptionExists extends Throwable {

    private static final String DEFAULT_MSG = "The group subscription already exists.";

    public SubscriptionExists( )
    {
        super(DEFAULT_MSG);
    }

    public SubscriptionExists( String message )
    {
        super(message);
    }
}
