package battleship;

public class Gameboard {
    char[][] board = new char[10][11];
    char[] identifiers = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    public Gameboard() {
    }

    public void createBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '~';
            }
        }
        for (int i = 0; i < identifiers.length; i++) {
            board[i][0] = identifiers[i];
        }
    }

    public void showBoardForPlayer() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] chars : board) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }

    public void showBoardForOpponent() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] chars : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (chars[j] == chars[0]) {
                    System.out.print(chars[j] + " ");
                } else if (chars[j] == 'X' || chars[j] == 'M') {
                    System.out.print(chars[j] + " ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
