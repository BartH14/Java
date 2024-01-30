package budget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetCounter {
    Scanner scanner;
    float balance = 0;
    public ArrayList<Purchase> foodPurchases = new ArrayList<>();
    public ArrayList<Purchase> clothingPurchases = new ArrayList<>();
    public ArrayList<Purchase> entertainmentPurchases = new ArrayList<>();
    public ArrayList<Purchase> otherPurchases = new ArrayList<>();
    FileHandler fileHandler;
    PurchaseSorter purchaseSorter;
    public BudgetCounter(Scanner scanner) {
        this.scanner = scanner;
        fileHandler = new FileHandler(this);
        purchaseSorter = new PurchaseSorter(scanner, this);
    }

    public void mainMenu() throws IOException {
        boolean running = true;
        while (running) {
            System.out.println("""
                    Choose your action:
                    1) Add income
                    2) Add purchase
                    3) Show list of purchases
                    4) Balance
                    5) Save
                    6) Load
                    7) Analyze (sort)
                    0) Exit""");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    while (true){
                        String category = getPurchaseCategory();
                        if (category.equals("5")) {
                            System.out.println();
                            break;
                        }
                        Purchase purchase = getPurchase();
                        addPurchase(purchase, category);
                    }
                    break;
                case 3:
                    if (foodPurchases.isEmpty() && clothingPurchases.isEmpty() && entertainmentPurchases.isEmpty()
                            && otherPurchases.isEmpty()) {
                        emptyListMessage();
                        break;
                    }
                    while (true) {
                        String category = getListCategory();
                        if (category.equals("6")) {
                            System.out.println();
                            break;
                        }
                        showPurchases(category);
                    }
                    break;
                case 4:
                    showBalance();
                    break;
                case 5:
                    fileHandler.writeToFile();
                    break;
                case 6:
                    fileHandler.readFromFile();
                    break;
                case 7:
                    purchaseSorter.getSortingMethod();
                    break;
                case 0:
                    System.out.println("\nBye!");
                    running = false;
                    break;
            }
        }
    }

    void addIncome() {
        System.out.println("\nEnter income:");
        float income = Float.parseFloat(scanner.nextLine());
        balance += income;
        System.out.println("Income was added!\n");
    }

    private String getPurchaseCategory() {
        System.out.println("""
                
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back""");
        return scanner.nextLine();
    }

    private Purchase getPurchase() {
        System.out.println("\nEnter purchase name:");
        String name = scanner.nextLine();
        System.out.println("Enter its price:");
        String price = scanner.nextLine();
        System.out.println("Purchase was added!");
        return new Purchase(name, Float.parseFloat(price));
    }

    private void addPurchase(Purchase purchase, String category) {
        switch (category) {
            case "1":
                foodPurchases.add(purchase);
                handlePurchase(purchase);
                break;
            case "2":
                clothingPurchases.add(purchase);
                handlePurchase(purchase);
                break;
            case "3":
                entertainmentPurchases.add(purchase);
                handlePurchase(purchase);
                break;
            case "4":
                otherPurchases.add(purchase);
                handlePurchase(purchase);
                break;
        }
    }

    private void handlePurchase(Purchase purchase) {
        balance = balance - purchase.cost > 0 ? balance - purchase.cost : 0;
    }

    private String getListCategory() {
        System.out.println("""
                
                Choose the type of purchases
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back""");
        return scanner.nextLine();
    }
    private void showPurchases(String category) {
        switch (category) {
            case "1":
                System.out.println("\nFood:");
                printPurchases(foodPurchases);
                break;
            case "2":
                System.out.println("\nClothing:");
                printPurchases(clothingPurchases);
                break;
            case "3":
                System.out.println("\nEntertainment:");
                printPurchases(entertainmentPurchases);
                break;
            case "4":
                System.out.println("\nOther:");
                printPurchases(otherPurchases);
                break;
            case "5":
                printAllPurchases();
                break;
        }
    }

    private void printPurchases(ArrayList<Purchase> purchases) {
        if (!purchases.isEmpty()) {
            printCategory(purchases);
        } else {
            emptyListMessage();
        }
    }

    private void printAllPurchases() {
        float totalCost = 0;
        System.out.println("\nAll:");
        if (!foodPurchases.isEmpty()) {
            for (Purchase purchase : foodPurchases) {
                System.out.printf("%s $%.2f\n", purchase.name, purchase.cost);
                totalCost += purchase.cost;
            }
        }
        if (!clothingPurchases.isEmpty()) {
            for (Purchase purchase : clothingPurchases) {
                System.out.printf("%s $%.2f\n", purchase.name, purchase.cost);
                totalCost += purchase.cost;
            }
        }
        if (!entertainmentPurchases.isEmpty()) {
            for (Purchase purchase : entertainmentPurchases) {
                System.out.printf("%s $%.2f\n", purchase.name, purchase.cost);
                totalCost += purchase.cost;
            }
        }
        if (!otherPurchases.isEmpty()) {
            for (Purchase purchase : otherPurchases) {
                System.out.printf("%s $%.2f\n", purchase.name, purchase.cost);
                totalCost += purchase.cost;
            }
        }
        System.out.printf("Total sum: $%.2f\n", totalCost);
    }

    private void printCategory(ArrayList<Purchase> purchases) {
            float totalCost = 0;
            for (Purchase purchase : purchases) {
                System.out.printf("%s $%.2f\n", purchase.name, purchase.cost);
                totalCost += purchase.cost;
            }
            System.out.printf("Total sum: $%.2f\n", totalCost);
    }

    private void emptyListMessage() {
        System.out.println("\nThe purchase list is empty\n");
    }

    private void showBalance() {
        System.out.printf("\nBalance: $%.2f\n\n", balance);
    }
}
