package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public int worldX,worldY;
    public double speed;
    public BufferedImage[] walkUp, walkDown, walkLeft, walkRight;
    public BufferedImage[] idleUp, idleDown, idleLeft, idleRight;
    protected Direction direction;
    public boolean moving = false;
    public int spriteCounter = 0;
    public int spriteNum = 0;
}
