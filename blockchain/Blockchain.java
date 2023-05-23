package blockchain;

import blockchain.block.ProofOfWorkBlock;

import java.util.ArrayList;
import java.util.Objects;

public class Blockchain {
    private static volatile Blockchain blockchain = null;
    private final Object lock = new Object();
    public ArrayList<ProofOfWorkBlock> chain;
    int zeroes = 0;
    public int maxTime = 3;
    public int minTime = 0;

    Blockchain() {
        this.chain = new ArrayList<>();
    }

    public static synchronized Blockchain getInstance() {
        if (blockchain == null) {
            synchronized (Blockchain.class) {
                if (blockchain == null) {
                    blockchain = new Blockchain();
                }
            }
        }
        return blockchain;
    }

    public int getBlockId() {
        if (chain.isEmpty()) {
            return 1;
        }
        return chain.size() + 1;
    }

    public String getPreviousBlockHash() {
        if (chain.isEmpty()) {
            return "0";
        }
        return chain.get(chain.size()-1).getCurrentBlockHash();
    }

    public String getCurrentBlockPrefix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zeroes; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    public boolean validateChain() {
        for (int i = 0; i < chain.size(); i++) {
            if (i == 0) {
                if (!Objects.equals(chain.get(0).getPreviousBlockHash(), "0")) {
                    return false;
                }
            } else {
                if (!Objects.equals(chain.get(i).getPreviousBlockHash(), chain.get(i - 1).getCurrentBlockHash())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateBlock(ProofOfWorkBlock block) {
        if (chain.isEmpty()) {
            block.setMessage("no data");
            return block.getCurrentBlockHash().startsWith("0");
            }
        if (!Objects.equals(block.getPreviousBlockHash(), chain.get(chain.size() - 1).getCurrentBlockHash())) {
            return false;
        }
        return (block.getCurrentBlockHash().startsWith(getCurrentBlockPrefix()));
    }

    public void addBlock(ProofOfWorkBlock block) {
        synchronized (lock) {
            if (validateBlock(block)) {
                int time = (int) block.getMilliseconds() / 1000;
                if (time > maxTime) {
                    zeroes--;
                } else if (time < minTime) {
                    zeroes++;
                }
                chain.add(block);
            }
        }
    }
}