package Title;

import Graphics.Sprites;
import WorldNavigator.GameMain;
import WorldNavigator.Handler;
import WorldNavigator.Id;

import java.awt.*;

public class TargetPowerUP extends Tile {

    private boolean poopedUp=false;
    private int spiritY=getY();
    private Sprites targetPowerUP;

    public TargetPowerUP(int x, int y, int height, int width, boolean solid, Id id, Handler handler,Sprites targetPowerUP) {
        super(x, y, height, width, solid, id, handler);
        this.targetPowerUP=targetPowerUP;
    }

    @Override
    public void render(Graphics graphics) {
        if (!poopedUp) graphics.drawImage(targetPowerUP.getImage(),x,spiritY,width,height,null);
        if (!activate) graphics.drawImage(GameMain.targetGraph.getImage(),x,y,width,height,null);
        else graphics.drawImage(GameMain.targetGraph.getImage(),x,y,width,height,null);
    }

    @Override
    public void tick() {
        if (activate&&!poopedUp)
        { spiritY--;
            if (spiritY<=y-height) {

        if (GameMain.keys >= 1) {
          poopedUp = true;
          GameMain.win=true;

          System.out.println("win");
      }else if (GameMain.keys==0){
            poopedUp=false;
            activate=false;
          System.out.println("loos");
               }
            }
        }
    }
}
