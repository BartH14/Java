package blockchain.miner;

import blockchain.Blockchain;
import blockchain.block.ProofOfWorkBlock;
import blockchain.factory.ProofOfWorkBlockFactory;
import blockchain.message.EmbeddedMessage;
import blockchain.message.MessageReader;
import blockchain.message.RandomMessageGenerator;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProofOfWorkMiner implements Miner{
    private final Blockchain blockchain;
    private MessageReader reader;
    private final Random random = new Random();
    private final Lock lock = new ReentrantLock();
    private final long threadNumber;

    public ProofOfWorkMiner(long threadNumber) {
        this.threadNumber = threadNumber;
        blockchain = getBlockchain();
        reader = MessageReader.getInstance();
    }

    @Override
    public Blockchain getBlockchain() {
        return Blockchain.getInstance();
    }

    @Override
    public ProofOfWorkBlock calculateBlock() {
        int id = blockchain.getBlockId();
        String previousBlockHash = blockchain.getPreviousBlockHash();
        ProofOfWorkBlockFactory factory = new ProofOfWorkBlockFactory();
        String message = reader.getMessage();
        return factory.createBlock(id, previousBlockHash, threadNumber, message);
    }

    public Boolean validateBlock(ProofOfWorkBlock block) {
        return blockchain.validateBlock(block);
    }

    public void mine() {
        ProofOfWorkBlock block;
        while (blockchain.chain.size() != 15) {
            do {
                block = calculateBlock();
            } while (!validateBlock(block));

            lock.lock();
            try {
                blockchain.addBlock(block);
            } finally {
                lock.unlock();
            }
        }
    }
}
