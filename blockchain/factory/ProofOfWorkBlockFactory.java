package blockchain.factory;

import blockchain.block.ProofOfWorkBlock;
import blockchain.message.EmbeddedMessage;

import java.util.Random;

public class ProofOfWorkBlockFactory implements BlockFactory {
    private static final Random random = new Random();
    @Override
    public ProofOfWorkBlock createBlock(int id, String previousBlockHash, long threadNumber, String message) {
        long timestamp = System.currentTimeMillis();
        String currentBlockHash;
        long milliSeconds;
        int magicNumber;

        magicNumber = random.nextInt(100000);
        currentBlockHash = ProofOfWorkBlock.encryptWithSha256(String.valueOf(id + timestamp + previousBlockHash + magicNumber));
        milliSeconds = System.currentTimeMillis() - timestamp;

        return new ProofOfWorkBlock(id, timestamp, previousBlockHash, currentBlockHash, milliSeconds, magicNumber, threadNumber, message);
    }
}