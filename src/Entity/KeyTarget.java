package Entity;

import WorldNavigator.Handler;
import WorldNavigator.Id;

import java.awt.*;

public class KeyTarget extends Entity {

    public KeyTarget(int x, int y, int height, int width, boolean solid, Id id, Handler handler) {
        super(x, y, height, width, solid, id, handler);
    }

    @Override
    public void render(Graphics graphics) {
    }

    @Override
    public void tick() {
    }
}
