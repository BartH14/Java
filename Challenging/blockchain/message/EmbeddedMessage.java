package blockchain.message;

public class EmbeddedMessage implements Message {
    String message;
    long timestamp;

    public EmbeddedMessage(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
}
