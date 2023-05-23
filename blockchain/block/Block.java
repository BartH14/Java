package blockchain.block;

public interface Block {
    int getId();
    long getTimestamp();
    String getPreviousBlockHash();
    String getCurrentBlockHash();
    long getMilliseconds();
    void setMilliseconds(long milliseconds);
    int getMagicNumber();
    void setMinerNumber(long number);
    long getMinerNumber();

}