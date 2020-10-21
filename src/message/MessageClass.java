package message;

public class MessageClass implements Message {

    private String title, text, URL;

    public MessageClass(String title, String text, String URL) {
        this.title = title;
        this.text = text;
        this.URL = URL;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return text;
    }

    @Override
    public String getURL() {
        return URL;
    }
}
