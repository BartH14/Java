package blockchain.block;

import blockchain.Blockchain;
import blockchain.message.EmbeddedMessage;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

public class ProofOfWorkBlock implements Block {
    static final Random random = new Random();
    final int id;
    private final long timestamp;
    final String previousBlockHash;
    String currentBlockHash;
    private long milliSeconds;
    private int magicNumber;
    private long minerNumber;
    private String message;

    public ProofOfWorkBlock(int id, long timestamp, String previousBlockHash, String currentBlockHash, long milliSeconds,
                            int magicNumber, long minerNumber, String message) {
        this.id = id;
        this.timestamp = timestamp;
        this.previousBlockHash = previousBlockHash;
        this.currentBlockHash = currentBlockHash;
        this.milliSeconds = milliSeconds;
        this.magicNumber = magicNumber;
        this.minerNumber = minerNumber;
        this.message = message;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public long getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String getPreviousBlockHash() {
        return this.previousBlockHash;
    }
    @Override
    public String getCurrentBlockHash() {
        return this.currentBlockHash;
    }

    @Override
    public long getMilliseconds() {
        return this.milliSeconds;
    }

    @Override
    public void setMilliseconds(long milliSeconds) {
        this.milliSeconds = milliSeconds;
    }

    @Override
    public int getMagicNumber() {
        return this.magicNumber;
    }

    @Override
    public void setMinerNumber(long number) {
        this.minerNumber = number;
    }

    @Override
    public long getMinerNumber() {
        return minerNumber;
    }

    public static String getCurrentBlockHashPrefix(int zeroes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zeroes; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    public static String encryptWithSha256(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(string.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException((e));
        }
    }

    @Override
    public String toString() {
        String prefixChange;
        Blockchain Blockchain = blockchain.Blockchain.getInstance();
        if (milliSeconds > Blockchain.maxTime * 1000L) {
            prefixChange = "N was decreased by 1";
        } else if (milliSeconds < Blockchain.minTime * 1000L) {
            prefixChange = String.format("N was increased to %d", Blockchain.getCurrentBlockPrefix().length());
        } else {
            prefixChange = "N stays the same";
        }

        return String.format("Block:%n" +
                        "Created by miner%d%n" +
                        "miner%d gets 100 VC%n" +
                        "Id: %d%n" +
                        "Timestamp: %d%n" +
                        "Magic number: %d%n" +
                        "Hash of the previous block:%n%s%n" +
                        "Hash of the block:%n%s%n" +
                        "Block data: %s%n" +
                        "Block was generating for %d seconds%n" +
                        "%s%n%n",
                minerNumber,minerNumber, id, timestamp, magicNumber, previousBlockHash, currentBlockHash, message, milliSeconds / 1000, prefixChange);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}