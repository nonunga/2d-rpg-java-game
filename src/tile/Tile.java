package tile;

import java.awt.image.BufferedImage;


public class Tile {
    public enum Type {
        WATER, GRASS, DIRT
    }
    public BufferedImage image;
    public boolean collision;
    public Type type;
    public Tile(Type type) {this.type = type;}
}