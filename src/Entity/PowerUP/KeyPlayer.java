package Entity.PowerUP;

import Entity.Entity;
import WorldNavigator.Handler;
import WorldNavigator.Id;

import java.awt.*;

public class KeyPlayer extends Entity {

    public KeyPlayer(int x, int y, int height, int width, boolean solid, Id id, Handler handler) {
        super(x, y, height, width, solid, id, handler);
    }

    @Override
    public void render(Graphics graphics) {
    }

    @Override
    public void tick() {
    }
}
