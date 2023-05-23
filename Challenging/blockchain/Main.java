package blockchain;

import blockchain.block.ProofOfWorkBlock;
import blockchain.miner.ProofOfWorkMiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Blockchain blockchain = Blockchain.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (long i = 0; i < 3; i++) {
            ProofOfWorkMiner miner = new ProofOfWorkMiner(i);
            executorService.execute(miner::mine);
        }
        Thread.sleep(1500);
        if (blockchain.chain.size() >= 15) {
            executorService.shutdown();
            for (int i = 0; i < 15; i++) {
                System.out.print(blockchain.chain.get(i).toString());
            }
        }
    }
}