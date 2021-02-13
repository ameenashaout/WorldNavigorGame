package Graphics.GUI;

import WorldNavigator.GameMain;

import java.awt.*;

public class InternalButton {
    public Button[] buttons;

    public InternalButton(){
       buttons=new Button[2];
        buttons[0]=new Button(20,480,100,30,"Stop Game");
        buttons[1]=new Button(20,510,100,30,"Exit Game");
    }

    public void render(Graphics graphics){
        for (int i=0;i<buttons.length;i++){
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Courier", Font.PLAIN, 20));
            buttons[i].render(graphics);
        }
    }
}
