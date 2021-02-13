package Title;

import WorldNavigator.GameMain;
import WorldNavigator.Handler;
import WorldNavigator.Id;

import java.awt.*;

public class Coin extends Tile{

    public Coin(int x, int y, int height, int width, boolean solid, Id id, Handler handler) {
        super(x, y, height, width, solid, id, handler);
    }
    @Override
    public void render(Graphics graphics) {
    graphics.drawImage(GameMain.coinGraph.getImage(),x,y,width,height,null);
    }

    @Override
    public void tick() {

    }
}
