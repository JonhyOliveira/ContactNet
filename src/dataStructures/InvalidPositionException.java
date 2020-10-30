package dataStructures;
// When it tries to access a position of a data structure that does not exist
public class InvalidPositionException extends RuntimeException {

    static final long serialVersionUID = 0L;
    
    private static final String DEFAULT_MSG = "Invalid position.";

    public InvalidPositionException( )
    {
        super(DEFAULT_MSG);
    }

    public InvalidPositionException( String message )
    {
        super(message);
    }

}

