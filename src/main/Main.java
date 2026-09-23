import main.GamePanel;

import javax.swing.*;

void main() {

    JFrame window = new JFrame("2D Game");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    GamePanel panel = new GamePanel();
    window.add(panel);
    window.pack();
    window.setLocationRelativeTo(null);
    window.setVisible(true);

    panel.startGameThread();

}