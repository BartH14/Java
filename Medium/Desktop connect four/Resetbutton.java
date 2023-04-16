package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resetbutton implements ActionListener {
    JButton[][] gameboard;

    public Resetbutton(JButton[][] gameboard) {
        this.gameboard = gameboard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[0].length; j++) {
                gameboard[i][j].setBackground(Color.lightGray);
                gameboard[i][j].setText(" ");
                gameboard[i][j].setEnabled(true);

            }
        }
    }
}
