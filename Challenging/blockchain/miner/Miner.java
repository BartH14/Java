package blockchain.miner;

import blockchain.Blockchain;
import blockchain.block.ProofOfWorkBlock;

public interface Miner {
    Blockchain getBlockchain();
    ProofOfWorkBlock calculateBlock();
}
