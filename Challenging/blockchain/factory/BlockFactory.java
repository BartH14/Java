package blockchain.factory;

import blockchain.block.Block;
import blockchain.message.EmbeddedMessage;

public interface BlockFactory {
    Block createBlock(int id, String previousBlockHash, long threadNumber, String message);
}