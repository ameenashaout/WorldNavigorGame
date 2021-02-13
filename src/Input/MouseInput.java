package Input;

import Graphics.GUI.Button;
import WorldNavigator.GameMain;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

public int x;
public int y;

    @Override
    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    for (int i=0;i< GameMain.launcher.buttons.length;i++){
    Button button=GameMain.launcher.buttons[i];
    if (x>=button.getX()&&y>=button.getY()&&x<=button.getX()+button.getWidth()&&y<=button.getY()+button.getHeight())
        button.triggerEvent();
    }
    for (int i=0;i< GameMain.internalButton.buttons.length;i++){
        Button button=GameMain.internalButton.buttons[i];
        if (x>=button.getX()&&y>=button.getY()&&x<=button.getX()+button.getWidth()&&y<=button.getY()+button.getHeight())
            button.triggerEvent();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        x=e.getX();
        y=e.getY();
    }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {
        x=e.getX();
        y=e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    x=e.getX();
    y=e.getY();
    }
}
