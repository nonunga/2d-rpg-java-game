package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public double x,y;
    public double speed;
    public BufferedImage[] up, down, left, right;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 0;
}
