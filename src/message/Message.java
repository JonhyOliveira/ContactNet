package message;

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
