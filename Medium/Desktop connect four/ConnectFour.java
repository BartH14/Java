package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConnectFour extends JFrame {
    private JButton[][] gameboard = new JButton[6][7];
    private ActionListener listener = new JListener(gameboard);
    private ActionListener resetter = new Resetbutton(gameboard);

    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 360);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("Connect Four");

        JPanel grid = new JPanel(new GridLayout(6, 7));
        for (int i = 5; i >= 0; i--) {
            for (char c = 'A'; c <= 'G'; c++) {
                JButton cell = new JButton(" ");
                gameboard[i][(int) c - 65] = cell;
                cell.setBackground(Color.lightGray);
                cell.setFocusPainted(false);
                cell.setName("Button" + c + (i + 1));
                cell.addActionListener(listener);
                grid.add(cell);
            }
        }
        JPanel buttonholder = new JPanel();
        JButton reset = new JButton("reset");
        reset.setName("ButtonReset");
        reset.addActionListener(resetter);
        setLayout(new FlowLayout());
        buttonholder.add(reset);

        add(grid);
        add(buttonholder);

    }
}
