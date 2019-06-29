package example.domain;

public class Message {
    private String text;

    private Message(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Message newMessage(final String text) {
        return new Message(text);
    }
}
