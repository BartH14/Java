package battleship;

import java.util.Scanner;

public class Ship {
    String name;
    int length;
    int firstX;
    int lastX;
    int firstY;
    int lastY;
    Orientation orientation = Orientation.VERTICAL;

    public Ship(String name, int length){
        this.name = name;
        this.length = length;
    }

    private void getLocation() {
        Scanner scanner = new Scanner(System.in);
        String[] coords = scanner.nextLine().split(" ");
        firstX = coords[0].charAt(0) - 65;
        firstY = Integer.parseInt(coords[0].substring(1));
        lastX = coords[1].charAt(0) - 65;
        lastY = Integer.parseInt(coords[1].substring(1));

        realignInput();

    }

    private void realignInput() {
        if (firstX > lastX) {
            int temp = lastX;
            lastX = firstX;
            firstX = temp;
        }
        if (firstY > lastY) {
            int temp = lastY;
            lastY = firstY;
            firstY = temp;
        }
        if (firstX != lastX) {
            orientation = Orientation.HORIZONTAL;
        }
        validateInput();
    }

    private void validateInput() {
        if (orientation == Orientation.VERTICAL) {
            if (lastY - firstY != (length - 1)) {
                System.out.printf("%nError: Wrong length of the %s%nTry again:%n%n", name);
                getLocation();
            }
        } else if (orientation == Orientation.HORIZONTAL) {
            if (lastX - firstX != (length - 1)) {
                System.out.printf("%nError: Wrong length of the %s%nTry again:%n%n", name);
                getLocation();
            }
        }
        if (firstX != lastX && firstY != lastY) {
            System.out.println("Error: Wrong ship location");
            getLocation();
        }
    }

    private boolean isLocationValid(Gameboard gameboard) {
        for (int i = firstX - 1; i <= lastX + 1; i++) {
            for (int j = firstY -1; j <= lastY + 1; j++) {
                if (i >= 0 && i < gameboard.board.length && j >= 1 && j < gameboard.board[i].length) {
                    if (gameboard.board[i][j] != '~') {
                        System.out.println("\nError: Wrong ship location! Try again:\n");
                        getLocation();
                    }
                }
            }
        }
        return true;
    }

    void placeShip(Gameboard gameboard) {
        System.out.printf("%nEnter the coordinates of the %s (%d cells)%n", name, length);
        getLocation();
        if (isLocationValid(gameboard)) {
            if (orientation.equals(Orientation.HORIZONTAL)) {
                for (int i = firstX; i <= lastX; i ++) {
                    gameboard.board[i][firstY] = 'O';
                }
            } else {
                for (int i = firstY; i <= lastY; i++) {
                    gameboard.board[firstX][i] = 'O';
                }
            }
        }
    }


    enum Orientation {
        VERTICAL,
        HORIZONTAL
    }
}
