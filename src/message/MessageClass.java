package message;

public class MessageClass implements Message {

    private String title, description, URL;

    public MessageClass(String title, String description, String URL) {
        this.title = title;
        this.description = description;
        this.URL = URL;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getURL() {
        return URL;
    }
}
