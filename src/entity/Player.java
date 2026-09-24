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

    public Player (GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues () {
        //player default pos
        x = 100;
        y = 100;
        speed = 250.0 / gp.fps;
        direction = "down";
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
            direction ="up";
            y -= speed;
        } else if (kh.downIsPressed) {
            direction ="down";
            y += speed;
        } else if (kh.leftIsPressed) {
            direction ="left";
            x -= speed;
        }else if (kh.rightIsPressed){
            direction ="right";
            x += speed;
        }

        boolean wasMoving = moving;
        moving = kh.upIsPressed || kh.downIsPressed || kh.leftIsPressed || kh.rightIsPressed;

        // walk and idle have different frame counts, so restart the animation on state change
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
            case "up" -> moving ? walkUp : idleUp;
            case "left" -> moving ? walkLeft : idleLeft;
            case "right" -> moving ? walkRight : idleRight;
            default -> moving ? walkDown : idleDown;
        };
    }

    public void draw(Graphics2D g2){
        BufferedImage image = currentFrames()[spriteNum];
        g2.drawImage(image, (int) x, (int) y, gp.scaledTileSize, gp.scaledTileSize, null);
    }

}