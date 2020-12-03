package exceptions;

public class EmptyDictionaryException extends Throwable{

    private static final String DEFAULT_MSG = "The dictionary is empty.";

    public EmptyDictionaryException( )
    {
        super(DEFAULT_MSG);
    }

    public EmptyDictionaryException( String message )
    {
        super(message);
    }
}
