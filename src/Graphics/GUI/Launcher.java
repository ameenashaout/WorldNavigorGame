package Graphics.GUI;

import WorldNavigator.GameMain;

import java.awt.*;

public class Launcher {
    public Button[] buttons;

    public Launcher(){
        buttons=new Button[2];
        buttons[0]=new Button(400,400,100,30,"Start Game");
        buttons[1]=new Button(400,450,100,30,"Exit Game");
    }
    public void render(Graphics graphics){
        graphics.drawImage(GameMain.background,0,0,GameMain.getFrameWidth(),GameMain.getFrameHeight(),null);
        for (int i=0;i<buttons.length;i++){
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Courier", Font.BOLD, 30));
            buttons[i].render(graphics);
        }
    }
}
