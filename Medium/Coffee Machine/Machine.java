package machine;

import java.util.Arrays;
import java.util.Scanner;

public class Machine {
    static int cups = 9;
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int money = 550;

    Machine() {
    }

    void refillMachine() {
        Scanner scanner = new Scanner(System.in);
        int water;
        int milk;
        int beans;
        int cups;

        System.out.println("Write how many ml of water you want to add:");
        water = scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beans = scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        cups = scanner.nextInt();

        Machine.water += water;
        Machine.milk += milk;
        Machine.beans += beans;
        Machine.cups += cups;
    }

    void canBrewDrink(Drink drink) {
        int[] cupsPerIngredientType = new int[4];

        cupsPerIngredientType[0] = Machine.water - drink.water;
        cupsPerIngredientType[1] = Machine.milk - drink.milk;
        cupsPerIngredientType[2] = Machine.beans - drink.beans;
        cupsPerIngredientType[3] = Machine.cups - drink.cup;


        if (cupsPerIngredientType[0] <= 0) {
            System.out.println("\nSorry, not enough water!\n");
        } else if (cupsPerIngredientType[1] < 0) {
            System.out.println("\nSorry, not enough milk!\n");
        } else if (cupsPerIngredientType[2] < 0) {
            System.out.println("\nSorry, not enough coffee beans!\n");
        } else if (cupsPerIngredientType[3] < 0) {
            System.out.println("\nSorry, not enough cups!\n");
        } else {
            brewDrink(drink);
        }
    }

    void brewDrink(Drink drink) {
        cups -= drink.cup;
        water -= drink.water;
        milk -= drink.milk;
        beans -= drink.beans;
        money += drink.cost;
    }

    void status() {
        System.out.printf("%nThe coffee machine has:%n" +
                "%d ml of water%n" +
                "%d ml of milk%n" +
                "%d g of coffee beans%n" +
                "%d disposable cups%n" +
                "$%d of money%n",water, milk, beans, cups, money);
    }

    void takeMoney() {
        System.out.printf("I gave you $%d", money);
        money = 0;
    }
}
