package budget;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BudgetCounter budgetCounter = new BudgetCounter(scanner);

        budgetCounter.mainMenu();
    }
}
