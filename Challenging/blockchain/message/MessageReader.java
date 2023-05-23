package blockchain.message;

import blockchain.Blockchain;

import java.util.Random;

public class MessageReader {
    Random random = new Random();
    static MessageReader reader;
    String message;

    public static synchronized MessageReader getInstance() {
        if (reader == null) {
            synchronized (MessageReader.class) {
                if (reader == null) {
                    reader = new MessageReader();
                }
            }
        }
        return reader;
    }

    void setMessage() {
        this.message = RandomMessageGenerator.generateRandomMessage();
    }

    public String getMessage() {
        setMessage();
        return message;
    }
}
