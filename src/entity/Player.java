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
        /*le resto esa vara pq me centra la imagen usando la esquina izqueirda superior del sprite
        * entoces hay que restar queda en el centro queda offset.
        * */
        this.screenX = gp.screenWidth/2 - (gp.scaledTileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.scaledTileSize/2);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues () {
        worldX = 12 * gp.scaledTileSize;
        worldY = 27 * gp.scaledTileSize;
        speed = 250.0 / gp.fps;
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
            String fileName = "/player/" + state + "/" + state + "_" + dir + "_" + i + ".png";
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
            worldY -= speed;
        } else if (kh.downIsPressed) {
            direction = Direction.DOWN;
            worldY += speed;
        } else if (kh.leftIsPressed) {
            direction = Direction.LEFT;
            worldX -= speed;
        }else if (kh.rightIsPressed){
            direction = Direction.RIGHT;
            worldX += speed;
        }

        boolean wasMoving = moving;
        moving = kh.upIsPressed || kh.downIsPressed || kh.leftIsPressed || kh.rightIsPressed;

        // different frame counts
        if (moving != wasMoving) {
            spriteNum = 0;
            spriteCounter = 0;
        }


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

    public void draw(Graphics2D g2){
        BufferedImage image = currentFrames()[spriteNum];
        int w = image.getWidth() * gp.scale;
        int h = image.getHeight() * gp.scale;
        int x = screenX - (w - gp.scaledTileSize) / 2;
        int y = screenY - (h - gp.scaledTileSize) / 2;
        g2.drawImage(image, x, y, w, h, null);
    }

}