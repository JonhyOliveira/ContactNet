package dataStructures;
// Thwros this exception when the user tries to access a data structure that has size equals to zero
public class NoElementException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MSG = "Empty data structure.";

	public NoElementException( )
	{
		super(DEFAULT_MSG);
	}
	
	public NoElementException( String msg )
	{
		super(msg);
	}
}
