package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("ALL")
public class Player extends Entity {

    GamePanel gp;
    KeyHandler kh;
    public final int screenX;
    public final int screenY;

    public Player (GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;
        //pos del jugador en pantalla no del mundo.
        this.screenX = gp.screenWidth/2 - (gp.scaledTileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.scaledTileSize/2);
        this.w = 20;
        this.h = 20;
        setDefaultValues();
        getPlayerImage();
    }

    Rect rect = new Rect(300, 300, 100, 100);

    private void setDefaultValues () {
        //spawn mapa 2
        // poscicion en el mundo no pantalla
        worldX = 12 * gp.scaledTileSize;
        worldY = 27 * gp.scaledTileSize;
        speed = 300.0 / gp.fps;
        direction = Direction.DOWN;
    }
    public void getPlayerImage(){
        walkDown = loadFrames("walk", "down", 6);
        walkUp = loadFrames("walk", "up", 6);
        walkRight = loadFrames("walk", "right", 6);
        walkLeft = loadFrames("walk", "left", 6);
        idleDown = loadFrames("idle", "down", 4);
        idleUp = loadFrames("idle", "up", 4);
        idleRight = loadFrames("idle", "right", 4);
        idleLeft = loadFrames("idle", "left", 4);
    }

    private BufferedImage[] loadFrames(String state, String dir, int count) {
        BufferedImage[] frames = new BufferedImage[count];
        for (int i = 0; i < count; i++) {
            String fileName = "/animations/player/" + state + "/" + state + "_" + dir + "_" + i + ".png";
            try {
                InputStream stream = getClass().getResourceAsStream(fileName);
                if (stream == null) {
                    throw new RuntimeException("image not found: " + fileName);
                }
                frames[i] = ImageIO.read(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return frames;
    }

    public void update(){
        if (kh.upIsPressed) {
            direction = Direction.UP;
            if (!would_colide(rect, 0, -speed)) {
                worldY -= speed;
            }
        } else if (kh.downIsPressed) {
            direction = Direction.DOWN;
            if (!would_colide(rect, 0, speed)) {
                worldY += speed;
            }
        } else if (kh.leftIsPressed) {
            direction = Direction.LEFT;
            if (!would_colide(rect, -speed, 0)) {
                worldX -= speed;
            }
        }else if (kh.rightIsPressed){
            direction = Direction.RIGHT;
            if (!would_colide(rect, speed, 0)) {
                worldX += speed;
            }
        }

        boolean wasMoving = moving;
        moving = kh.upIsPressed || kh.downIsPressed || kh.leftIsPressed || kh.rightIsPressed;

        // different frame counts
        if (moving != wasMoving) {
            spriteNum = 0;
            spriteCounter = 0;
        }

        //ajuste por la diff de frames del walking y idle animation
        int animFps = moving ? 10 : 4;
        spriteCounter++;
        if (spriteCounter >= gp.fps / animFps) {
            spriteNum = (spriteNum + 1) % currentFrames().length;
            spriteCounter = 0;
        }
    }

    private BufferedImage[] currentFrames() {
        return switch (direction) {
            case Direction.UP -> moving ? walkUp : idleUp;
            case Direction.LEFT -> moving ? walkLeft : idleLeft;
            case Direction.RIGHT -> moving ? walkRight : idleRight;
            default -> moving ? walkDown : idleDown;
        };
    }

    public int getPlayerRow() {
        return (int)this.worldY / gp.scaledTileSize;
    }
    public int getPlayerCol() {
        return (int)this.worldX / gp.scaledTileSize;
    }

    public class Rect extends Entity {
        public Rect(int x, int y, int w, int h) { this.worldX = x; this.worldY = y; this.w = w; this.h = h;}
    }
    public boolean would_colide(Rect rect, double dx, double dy) {
        return (this.worldX + dx < rect.worldX + rect.w &&
                this.worldX + dy + this.w > rect.worldX &&
                this.worldY + dx < rect.worldY + rect.h &&
                this.worldY + dy + this.h > rect.worldY);
    }
    //el player sprite mide 32x32 y el world tile 16x16
    public void draw(Graphics2D g2){
        BufferedImage image = currentFrames()[spriteNum];
        int w = image.getWidth() * gp.scale; // 160 con scale en 5
        int h = image.getHeight() * gp.scale; // 160

        int x = screenX - (w - gp.scaledTileSize) / 2;
        int y = screenY - (h - gp.scaledTileSize) / 2;
        g2.drawImage(image, x, y, w, h, null);
        g2.drawRect(x, y, w, h);
        g2.drawRect(screenX, screenY, (int)this.w, (int)this.h);
        g2.drawRect(((int) rect.worldX - (int) worldX) + screenX, ((int) rect.worldY - (int) worldY) + screenY, (int)rect.w, (int)rect.h);
    }

}