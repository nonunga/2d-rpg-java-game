package main;

import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int tileSize = 16;
    public final int scale = 5;
    public final int scaledTileSize = tileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = scaledTileSize * maxScreenCol;
    public final int screenHeight = scaledTileSize * maxScreenRow;
    public final int fps = 60;

    TileManager tm = new TileManager(this);
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
        tm.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
}
