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
        speed = 300.0 / gp.fps;
        direction = "down";
    }

    public void getPlayerImage(){
        down = loadFrames("down");
        up = loadFrames("up");
        right = loadFrames("right");
        left = loadFrames("left");
    }

    private BufferedImage[] loadFrames(String name) {
        BufferedImage[] frames = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            String fileName = "/player/walk/walk_" + name + "_" + i + ".png";
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

        boolean moving = kh.upIsPressed || kh.downIsPressed || kh.leftIsPressed || kh.rightIsPressed;

        if (moving) {
            spriteCounter++;
            if (spriteCounter >= gp.fps / 10) {
                spriteNum = (spriteNum + 1) % 6;
                spriteCounter = 0;
            }
        } else {
            spriteNum = 0;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = switch (direction) {
            case "up" -> up[spriteNum];
            case "down" -> down[spriteNum];
            case "left" -> left[spriteNum];
            case "right" -> right[spriteNum];
            default -> null;
        };

        g2.drawImage(image, (int) x, (int) y, gp.scaledTileSize, gp.scaledTileSize, null);
    }

}