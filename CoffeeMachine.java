package machine;

import java.util.Scanner;



public class CoffeeMachine {

    public void menu() {
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Drink espresso = new Drink(250, 0, 16, 4);
        Drink latte = new Drink(350, 75, 20, 7);
        Drink cappuccino = new Drink(200, 100, 12, 6);
        Machine machine = new Machine();

        while(true) {
            String choice;
            String drink;
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            choice = scanner.next();

            switch (choice) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    drink = scanner.next();
                    switch (drink) {
                        case "1":
                            machine.canBrewDrink(espresso);
                            break;
                        case "2":
                            machine.canBrewDrink(latte);
                            break;
                        case "3":
                            machine.canBrewDrink(cappuccino);
                            break;
                        case "back":
                            break;

                    }
                    break;
                case "fill":
                    machine.refillMachine();
                    break;
                case "take":
                    machine.takeMoney();
                    break;
                case "remaining":
                    machine.status();
                    break;
                case "exit":
                    System.exit(0);
            }
        }
    }
}
