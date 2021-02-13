package Input;

import Entity.Entity;
import Graphics.GUI.Button;
import WorldNavigator.GameMain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    public static String direction="Stop ";

    public static String getDirection() {
        return direction;
    }

    @Override
    public void keyPressed (KeyEvent e) {
    int key=e.getKeyCode();
    for ( Entity entity: GameMain.handler.entities){
    switch (key){
    case KeyEvent.VK_W:
      if (Button.movement==true){
          direction="up";
        entity.setValY(-5);
        break;}
    case KeyEvent.VK_S:
        if (Button.movement==true){
            entity.setValY(5);
        direction=" down";
        break;}
    case KeyEvent.VK_D:
        if (Button.movement==true){
            direction="Right";
        entity.setValX(5);
        break;}
    case KeyEvent.VK_A:
        if (Button.movement==true){
            direction="left";
        entity.setValX(-5);
        break;}
       }
    }}

    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();
        for ( Entity entity: GameMain.handler.entities){
            switch (key){
                case KeyEvent.VK_W:
                    entity.setValY(0);
                    direction="Stop";
                    break;
                case KeyEvent.VK_S:
                    entity.setValY(0);
                    direction="Stop";
                    break;
                case KeyEvent.VK_D:
                    direction="Stop";
                    entity.setValX(0);
                    break;
                case KeyEvent.VK_A:
                    direction="Stop";
                    entity.setValX(0);
                    break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) { }
}
