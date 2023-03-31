package battleship;

import java.util.Scanner;

public class Player {
    Gameboard gameboard;
    int hitpoints = 17;

    Player (Gameboard board) {
        this.gameboard = board;
    }

    private boolean isShipAfloat(Gameboard gameboard, int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < gameboard.board.length && j >= 1 && j < gameboard.board[i].length) {
                    if (i == x && j == y) {
                        continue;
                    } else {
                        if (gameboard.board[i][j] == 'O') {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    void Shoot(Gameboard gameboard, Player player){
        int x;
        int y;
        String[] input;

        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine().split(" ");
        x = input[0].charAt(0) - 65;
        y = Integer.parseInt(input[0].substring(1));

        while (!(0 <= x && x < 11 && 0 < y && y < 11)) {
            System.out.println("\nError: You entered the wrong coordinates! Try again:\n");
            input = scanner.nextLine().split(" ");
            x = input[0].charAt(0) - 65;
            y = Integer.parseInt(input[0].substring(1));
        }

        if (gameboard.board[x][y] == '~') {
            gameboard.board[x][y] = 'M';
            gameboard.showBoardForOpponent();
            System.out.println("\nYou missed!");
        } else if (gameboard.board[x][y] == 'O') {
            gameboard.board[x][y] = 'X';
            player.hitpoints--;
            if (isShipAfloat(gameboard, x, y)) {
                System.out.println("\nYou hit a ship! Try again:\n");
            } else {
                System.out.println("\nYou sank a ship! Specify a new target:\n");
            }
        } else if (gameboard.board[x][y] == 'X') {
            System.out.println("\nYou hit a ship! Try again:\n");
        }
        gameboard.showBoardForOpponent();
    }

    void passTurn(){
        Scanner scanner = new Scanner(System.in);
        String inp = "placeholder";
        System.out.println("Press enter and pass the move to another player");
        while (!inp.equals("")) {
            inp = scanner.nextLine();
        }
    }

}
