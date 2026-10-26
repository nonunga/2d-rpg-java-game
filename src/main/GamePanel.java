package main;

import entity.Player;
import tile.Tile;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int tileSize = 16;
    public final int scale = 5;
    public final int scaledTileSize = tileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = scaledTileSize * maxScreenCol;
    public final int screenHeight = scaledTileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    public int fps = 144;

    private volatile int fpsCounter;

    TileManager tm = new TileManager(this);
    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,kh);

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
            Tile currentTile = tm.getTile(player.getPlayerRow(), player.getPlayerCol());

            update();
            repaint(); drawFps++;

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
                fpsCounter = drawFps;
                drawFps=0;
                timer = System.nanoTime();
            }

        }
    }

    public void update () {
        player.update();
    }

    private void drawGameMetrics(Graphics2D g2){
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        //FPS
        g2.drawString("FPS: " + fpsCounter,20,30 );
        //player pos top right
        String playerPos = (int) player.worldX + "," + (int) player.worldY;
        alignTextToRight(g2,playerPos,25);
        //player tileMatrixPos top right
        String tileMatrixPos = (int) player.worldX / scaledTileSize + "," + (int) player.worldY / scaledTileSize;
        alignTextToRight(g2,tileMatrixPos,50);

        String blockUnder = "Standing on: " + tm.getTile(player.getPlayerRow(), player.getPlayerCol()).type;
        alignTextToRight(g2,blockUnder,75);
    }

    private void alignTextToRight(Graphics2D g2, String text, int y){
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int x = screenWidth - textWidth - 12;
        g2.drawString(text,x,y);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        tm.draw(g2);
        player.draw(g2);
        drawGameMetrics(g2);
        g2.dispose();

    }
}
