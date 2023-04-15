package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JListener implements ActionListener {
    private Boolean isPlayerTurn = true;
    private JButton[][] gameboard;
    private final Color winningColor = Color.green;

    public JListener(JButton[][] gameboard) {
        this.gameboard = gameboard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int col = getColumn(button);
        for (int i = gameboard.length; i >= 0; i--) {
            if (i == 0) {
                if (gameboard[i][col].getText().equals(" ")) {
                    gameboard[i][col].setText(this.isPlayerTurn ? "X" : "O");
                }
            } else if (!gameboard[i - 1][col].getText().equals(" ")) {
                if (gameboard[i][col].getText().equals(" ")) {
                    gameboard[i][col].setText(this.isPlayerTurn ? "X" : "O");
                }
            }
        }
        if (isGameWon(gameboard)) {
            for (int i = 0; i < gameboard.length; i++) {
                for (int j = 0; j < gameboard[0].length; j++) {
                    gameboard[i][j].setEnabled(false);
                    this.isPlayerTurn = true;
                }
            }
        } else {
            this.isPlayerTurn = !this.isPlayerTurn;
        }
    }

    public int getColumn(JButton button) {
        for (int row = 0; row < gameboard.length; row++) {
            for (int col = 0; col < gameboard[0].length; col++) {
                if (gameboard[row][col] == button) {
                    return col;
                }
            }
        }
        return -1;
    }

    public boolean isGameWon(JButton[][] gameboard) {
        // check horizontally
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j <= gameboard[0].length - 4; j++) {
                if (!gameboard[i][j].getText().equals(" ")) {
                    if (gameboard[i][j].getText().equals(gameboard[i][j + 1].getText()) &&
                            gameboard[i][j + 1].getText().equals(gameboard[i][j + 2].getText()) &&
                            gameboard[i][j + 2].getText().equals(gameboard[i][j + 3].getText())) {
                        gameboard[i][j].setBackground(winningColor);
                        gameboard[i][j + 1].setBackground(winningColor);
                        gameboard[i][j + 2].setBackground(winningColor);
                        gameboard[i][j + 3].setBackground(winningColor);
                        return true;
                    }
                }
            }
        }
        //check vertically
        for (int i = 0; i <= gameboard.length - 4; i++) {
            for (int j = 0; j < gameboard[0].length; j++) {
                if (!gameboard[i][j].getText().equals(" ")) {
                    if (gameboard[i][j].getText().equals(gameboard[i + 1][j].getText()) &&
                            gameboard[i + 1][j].getText().equals(gameboard[i + 2][j].getText()) &&
                            gameboard[i + 2][j].getText().equals(gameboard[i + 3][j].getText())) {
                        gameboard[i][j].setBackground(winningColor);
                        gameboard[i + 1][j].setBackground(winningColor);
                        gameboard[i + 2][j].setBackground(winningColor);
                        gameboard[i + 3][j].setBackground(winningColor);
                        return true;
                    }
                }
            }
        }
        //check diagonal (left to right)
        for (int i = 0; i <= gameboard.length - 4; i++) {
            for (int j = 0; j <= gameboard[0].length - 4; j++) {
                if (!gameboard[i][j].getText().equals(" ")){
                    if (gameboard[i][j].getText().equals(gameboard[i + 1][j + 1].getText()) &&
                            gameboard[i + 1][j + 1].getText().equals(gameboard[i + 2][j + 2].getText()) &&
                            gameboard[i + 2][j + 2].getText().equals(gameboard[i + 3][j + 3].getText())) {
                        gameboard[i][j].setBackground(winningColor);
                        gameboard[i + 1][j + 1].setBackground(winningColor);
                        gameboard[i + 2][j + 2].setBackground(winningColor);
                        gameboard[i + 3][j + 3].setBackground(winningColor);
                        return true;
                    }
                }
            }
        }
        //check diagonal (right to left)
        for (int i = 0; i <= gameboard.length - 4; i++) {
            for (int j = 3; j < gameboard[0].length; j++) {
                if (!gameboard[i][j].getText().equals(" ")){
                    if (gameboard[i][j].getText().equals(gameboard[i + 1][j - 1].getText()) &&
                            gameboard[i + 1][j - 1].getText().equals(gameboard[i + 2][j - 2].getText()) &&
                            gameboard[i + 2][j - 2].getText().equals(gameboard[i + 3][j - 3].getText())) {
                        gameboard[i][j].setBackground(winningColor);
                        gameboard[i + 1][j - 1].setBackground(winningColor);
                        gameboard[i + 2][j - 2].setBackground(winningColor);
                        gameboard[i + 3][j - 3].setBackground(winningColor);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
