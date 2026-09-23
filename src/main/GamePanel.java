package main;

import entity.Player;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int tileSize = 16;
    final int scale = 5;
    public final int scaledTileSize = tileSize * scale; //48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = scaledTileSize * maxScreenCol;
    final int screenHeight = scaledTileSize * maxScreenRow;
    public final int fps = 60;


    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,kh);



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @SuppressWarnings("BusyWait")
    @Override
    public void run() {

        long drawInterval = 1_000_000_000L / fps;
        long nextDrawTime = System.nanoTime() + drawInterval;
        long timer =  System.nanoTime();
        int drawFps = 0;

        while (gameThread != null) {
            update();
            repaint();
            drawFps++;

            long remainingTime = nextDrawTime - System.nanoTime();

            if (remainingTime > 0) {
                try {
                    Thread.sleep(remainingTime / 1_000_000, (int) (remainingTime % 1_000_000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                nextDrawTime += drawInterval;
            } else {
                nextDrawTime = System.nanoTime() + drawInterval;
            }

            if ((System.nanoTime() - timer) >= 1_000_000_000L){
                System.out.println("FPS: " + drawFps);
                drawFps=0;
                timer = System.nanoTime();
            }

        }
    }

    public void update () {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        player.draw(g2);
        g2.dispose();

    }
}
