package message;

/**
 * Message Abstract Data Type
 * Includes description of general methods to be implemented by a Message.
 * @author Joao Oliveira 58001 & Rafel Borralho 58349
 * @version final
 */
public interface Message {

    /**
     * @return the message title
     */
    String getTitle();

    /**
     * @return the message description
     */
    String getDescription();

    /**
     * @return the message URL
     */
    String getURL();
}
