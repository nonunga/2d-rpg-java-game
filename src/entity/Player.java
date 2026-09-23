package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler kh;

    public Player (GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;
        setDefaultValues();
    }

    public void setDefaultValues () {
        //player default pos
        x = 100;
        y = 100;
        speed = 300.0 / gp.fps;
    }

    public void update(){
        if (kh.upIsPressed) {
            y -= (int) speed;
        } else if (kh.downIsPressed) {
            y += (int) speed;
        } else if (kh.leftIsPressed) {
            x -= (int) speed;
        }else if (kh.rightIsPressed){
            x += (int) speed;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y,gp.scaledTileSize,gp.scaledTileSize);
    }

}
