package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upIsPressed, downIsPressed, leftIsPressed, rightIsPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            upIsPressed = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            downIsPressed = true;
        }
        if (keyCode == KeyEvent.VK_A) {
            leftIsPressed = true;
        }
        if (keyCode == KeyEvent.VK_D) {
            rightIsPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            upIsPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            downIsPressed = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            leftIsPressed = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            rightIsPressed = false;
        }
    }
}
