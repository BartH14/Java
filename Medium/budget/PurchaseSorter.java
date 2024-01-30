package budget;

import java.lang.reflect.Array;
import java.util.*;

public class PurchaseSorter {
    Scanner scanner;
    BudgetCounter budgetCounter;

    public PurchaseSorter(Scanner scanner, BudgetCounter budgetCounter) {
        this.scanner = scanner;
        this.budgetCounter = budgetCounter;
    }

    public void getSortingMethod() {
        boolean running = true;
        while (running) {
            System.out.println("""
                
                How do you want to sort?
                1) Sort all purchases
                2) Sort by type
                3) Sort certain type
                4) Back""");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    sortAllPurchases();
                    break;
                case 2:
                    sortByType();
                    break;
                case 3:
                    sortPurchasesOfType();
                    break;
                case 4:
                    System.out.println();
                    running = false;
                    break;
            }
        }
    }

    private void sortAllPurchases() {
        if (budgetCounter.foodPurchases.isEmpty() && budgetCounter.clothingPurchases.isEmpty() && budgetCounter.entertainmentPurchases.isEmpty()
                && budgetCounter.otherPurchases.isEmpty()) {
            emptyListMessage();
        } else {
            ArrayList<Purchase> allPurchases = new ArrayList<>();
            allPurchases.addAll(budgetCounter.foodPurchases);
            allPurchases.addAll(budgetCounter.clothingPurchases);
            allPurchases.addAll(budgetCounter.entertainmentPurchases);
            allPurchases.addAll(budgetCounter.otherPurchases);

            bubbleSortArray(allPurchases);
            System.out.println("\nAll:");
            printCategory(allPurchases);
        }
    }

    private void sortByType() {
        ArrayList<ExpensesWrapper> allExpenses = getExpensesWrappers();
        float totalsum = 0.00F;

        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < allExpenses.size(); i++) {
                if (allExpenses.get(i-1).expenses < allExpenses.get(i).expenses) {
                    ExpensesWrapper temp = allExpenses.get(i-1);
                    allExpenses.set(i-1, allExpenses.get(i));
                    allExpenses.set(i, temp);
                    swapped = true;
                }
            }
        } while (swapped);
        System.out.println("\nTypes:");
        for (ExpensesWrapper wrapper : allExpenses) {
            totalsum += wrapper.expenses;
            if (wrapper.expenses != 0.00) {
                System.out.printf("%s%.2f\n", wrapper.category, wrapper.expenses);
            } else {
                System.out.printf("%s0\n", wrapper.category);
            }
        }
        if (totalsum == 0.00) {
            System.out.println("Total sum: $0\n");
        } else {
            System.out.printf("Total sum: $%.2f\n", totalsum);
        }
    }

    private ArrayList<ExpensesWrapper> getExpensesWrappers() {
        ArrayList<ExpensesWrapper> allExpenses = new ArrayList<>();
        ExpensesWrapper foodExpenses = new ExpensesWrapper(getCategoryExpenses(budgetCounter.foodPurchases),
                "Food - $");
        ExpensesWrapper clothingExpenses = new ExpensesWrapper(getCategoryExpenses(budgetCounter.clothingPurchases),
                "Clothes - $");
        ExpensesWrapper entertainmentExpenses = new ExpensesWrapper(getCategoryExpenses(budgetCounter.entertainmentPurchases),
                "Entertainment - $");
        ExpensesWrapper otherExpenses = new ExpensesWrapper(getCategoryExpenses(budgetCounter.otherPurchases),
                "Other - $");

        allExpenses.add(foodExpenses);
        allExpenses.add(clothingExpenses);
        allExpenses.add(entertainmentExpenses);
        allExpenses.add(otherExpenses);
        return allExpenses;
    }

    private void sortPurchasesOfType() {
        System.out.println("""
            
            Choose the type of purchase
            1) Food
            2) Clothes
            3) Entertainment
            4) Other""");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                if (!budgetCounter.foodPurchases.isEmpty()) {
                    bubbleSortArray(budgetCounter.foodPurchases);
                    System.out.println("\nFood:");
                    printCategory(budgetCounter.foodPurchases);
                } else {
                    System.out.println("\nThe purchase list is empty!\n");
                }
                break;
            case 2:
                if (!budgetCounter.clothingPurchases.isEmpty()) {
                    bubbleSortArray(budgetCounter.clothingPurchases);
                    System.out.println("\nClothes:");
                    printCategory(budgetCounter.clothingPurchases);
                } else {
                    System.out.println("\nThe purchase list is empty!\n");
                }
                break;
            case 3:
                if (!budgetCounter.entertainmentPurchases.isEmpty()) {
                    bubbleSortArray(budgetCounter.entertainmentPurchases);
                    System.out.println("\nEntertainment:");
                    printCategory(budgetCounter.entertainmentPurchases);
                } else {
                    System.out.println("\nThe purchase list is empty!\n");
                }
                break;
            case 4:
                if (!budgetCounter.otherPurchases.isEmpty()) {
                    bubbleSortArray(budgetCounter.otherPurchases);
                    System.out.println("\nOther:");
                    printCategory(budgetCounter.otherPurchases);
                } else {
                    System.out.println("\nThe purchase list is empty!\n");
                }
                break;
        }
    }

    private void bubbleSortArray(ArrayList<Purchase> purchases) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < purchases.size(); i++) {
                if (purchases.get(i-1).cost < purchases.get(i).cost) {
                    Purchase temp = purchases.get(i-1);
                    purchases.set(i-1, purchases.get(i));
                    purchases.set(i, temp);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    private float getCategoryExpenses(ArrayList<Purchase> purchases) {
        float expenses = 0;
        for (Purchase purchase : purchases) {
            expenses += purchase.cost;
        }
        return expenses;
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
        System.out.println("\nThe purchase list is empty!\n");
    }

    public class ExpensesWrapper {
        float expenses;
        String category;

        public ExpensesWrapper(float expenses, String category) {
            this.expenses = expenses;
            this.category = category;
        }
    }
}
