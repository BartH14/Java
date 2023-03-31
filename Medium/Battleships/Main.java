package battleship;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int turn = 1;

        Gameboard playerOneBoard = new Gameboard();
        Player playerOne = new Player(playerOneBoard);
        Ship carrierOne = new Ship("Aircraft carrier", 5);
        Ship battleshipOne = new Ship("Battleship", 4);
        Ship submarineOne = new Ship("Submarine", 3);
        Ship cruiserOne = new Ship("Cruiser", 3);
        Ship destroyerOne = new Ship("Destroyer", 2);

        Ship carrierTwo = new Ship("Aircraft carrier", 5);
        Ship battleshipTwo = new Ship("Battleship", 4);
        Ship submarineTwo = new Ship("Submarine", 3);
        Ship cruiserTwo = new Ship("Cruiser", 3);
        Ship destroyerTwo = new Ship("Destroyer", 2);

        Gameboard playerTwoBoard = new Gameboard();
        Player playerTwo = new Player(playerTwoBoard);

        List<Ship> playerOneShips = new ArrayList<>();
        playerOneShips.add(carrierOne);
        playerOneShips.add(battleshipOne);
        playerOneShips.add(submarineOne);
        playerOneShips.add(cruiserOne);
        playerOneShips.add(destroyerOne);

        playerOneBoard.createBoard();
        playerTwoBoard.createBoard();
        playerOneBoard.showBoardForOpponent();

        List<Ship> playerTwoShips = new ArrayList<>();
        playerTwoShips.add(carrierTwo);
        playerTwoShips.add(battleshipTwo);
        playerTwoShips.add(submarineTwo);
        playerTwoShips.add(cruiserTwo);
        playerTwoShips.add(destroyerTwo);

        for(Ship ship : playerOneShips) {
            ship.placeShip(playerOneBoard);
            System.out.println();
            playerOneBoard.showBoardForPlayer();
        }

        playerOne.passTurn();
        playerTwoBoard.showBoardForPlayer();

        for (Ship ship : playerTwoShips) {
            ship.placeShip(playerTwoBoard);
            System.out.println();
            playerTwoBoard.showBoardForPlayer();
        }

        playerTwo.passTurn();

        while (playerOne.hitpoints > 0 && playerTwo.hitpoints > 0) {
            if (turn % 2 == 1) {
                playerTwoBoard.showBoardForOpponent();
                System.out.println("---------------------");
                playerOneBoard.showBoardForPlayer();
                System.out.println("Player 1, it's your turn:\n");
                playerOne.Shoot(playerTwoBoard, playerTwo);
            } else {
                playerOneBoard.showBoardForOpponent();
                System.out.println("---------------------");
                playerTwoBoard.showBoardForPlayer();
                System.out.println("Player 2, it's your turn:\n");
            }
            playerOne.passTurn();
            turn++;
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }
}
