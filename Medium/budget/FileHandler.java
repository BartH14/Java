package budget;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    BudgetCounter budgetCounter;
    StringParser stringParser;

    public FileHandler(BudgetCounter budgetCounter) {
        this.budgetCounter = budgetCounter;
        stringParser = new StringParser();
    }
    public void writeToFile() throws IOException {
        File file = new File("purchases.txt");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.printf("Balance: $%.2f\n",budgetCounter.balance);
            writer.println("\nFood:");
            writeCategory(writer, budgetCounter.foodPurchases);
            writer.println("\nClothes:");
            writeCategory(writer, budgetCounter.clothingPurchases);
            writer.println("\nEntertainment:");
            writeCategory(writer, budgetCounter.entertainmentPurchases);
            writer.println("\nOther:");
            writeCategory(writer, budgetCounter.otherPurchases);
        } catch (IOException e) {
            System.out.printf("An unexpected error occurred %s", e.getMessage());
        }
        System.out.println("\nPurchases were saved!\n");
    }

    public void readFromFile() {
        String currentCategory = "";
        try(Scanner scanner = new Scanner(new File("purchases.txt"))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.contains("Balance:")) {
                    budgetCounter.balance = stringParser.getDollarAmount(line);
                } else if (line.contains("Food")) {
                    currentCategory = "Food";
                } else if (line.contains("Clothes")) {
                    currentCategory = "Clothes";
                } else if (line.contains("Entertainment")) {
                    currentCategory = "Entertainment";
                } else if (line.contains("Other")) {
                    currentCategory = "Other";
                } else {
                    String name = stringParser.getProductName(line);
                    float cost = stringParser.getDollarAmount(line);
                    Purchase purchase = new Purchase(name, cost);

                    switch (currentCategory) {
                        case "Food":
                            budgetCounter.foodPurchases.add(purchase);
                            break;
                        case "Clothes":
                            budgetCounter.clothingPurchases.add(purchase);
                            break;
                        case "Entertainment":
                            budgetCounter.entertainmentPurchases.add(purchase);
                            break;
                        case "Other":
                            budgetCounter.otherPurchases.add(purchase);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nPurchases were loaded!\n");
    }

    public void writeCategory(PrintWriter writer, ArrayList<Purchase> category) {
        for (Purchase purchase : category) {
            writer.printf("%s $%.2f\n", purchase.name, purchase.cost);
        }
    }
}
