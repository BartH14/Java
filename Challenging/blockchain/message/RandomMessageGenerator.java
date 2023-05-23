package blockchain.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMessageGenerator {

    public static String generateRandomMessage() {
        Random random = new Random();

        String[] words = {"Hello", "World", "Java", "Programming", "Hyperskill", "Blockchain", "Random", "Message"};

        StringBuilder messageBuilder = new StringBuilder();
        int length = random.nextInt(10) + 1;

        for (int j = 0; j < length; j++) {
            int randomIndex = random.nextInt(words.length);
            String word = words[randomIndex];
            messageBuilder.append(word).append(" ");
        }

        return messageBuilder.toString().trim();
    }
}