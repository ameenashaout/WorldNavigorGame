package Title;

import Entity.KeyTarget;
import WorldNavigator.GameMain;
import WorldNavigator.Handler;
import WorldNavigator.Id;
import Graphics.Sprites;

import java.awt.*;

public class PowerUPBlock extends Tile {

    private Sprites powerUP;
    private boolean poopedUp=false;
    private int spiritY=getY();

    public PowerUPBlock(int x, int y, int height, int width, boolean solid, Id id, Handler handler, Sprites sprites) {
        super(x, y, height, width, solid, id, handler);
        this.powerUP=sprites;
    }

    @Override
    public void render(Graphics graphics) {
    if (!poopedUp) graphics.drawImage(powerUP.getImage(),x,spiritY,width,height,null);
    if (!activate) graphics.drawImage(GameMain.mirrorGraph.getImage(),x,y,width,height,null);
    else graphics.drawImage(GameMain.mirrorGraph.getImage(),x,y,width,height,null);
    }

    @Override
    public void tick() {
    if (activate&&!poopedUp) {
    spiritY--;
    if (spiritY<=y-height) {
        handler.addEntity(new KeyTarget(x,spiritY,width,height,false,Id.keyDoor,handler));
        poopedUp=true;
        GameMain.keys+=1;
        GameMain.coins=GameMain.coins+10;
          }
       }
    }
}
