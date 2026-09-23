package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public double x,y;
    public double speed;
    public BufferedImage[] walkUp, walkDown, walkLeft, walkRight;
    public BufferedImage[] idleUp, idleDown, idleLeft, idleRight;
    public String direction;
    public boolean moving = false;

    public int spriteCounter = 0;
    public int spriteNum = 0;
}
