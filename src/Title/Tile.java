package Title;

import WorldNavigator.Handler;
import WorldNavigator.Id;

import java.awt.*;

public abstract class Tile {
    public int x;
    public int y;
    public int height;
    public int width;
    public int valX;
    public int valY;

    public boolean activate=false;
    public boolean solid;

    public Handler handler;
    public Id id;


    public void setValX(int valX) {
        this.valX = valX;
    }

    public void setValY(int valY) {
        this.valY = valY;
    }

    public Tile(int x, int y, int height, int width, boolean solid,Id id,Handler handler){
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
        this.solid=solid;
        this.id=id;
        this.handler=handler;
    }

    public abstract void render(Graphics graphics);

    public abstract void tick();

    public void die(){handler.removeTile(this); }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSolid() {
        return solid;
    }

    public Id getId(){
        return id;
    }

    public Rectangle getBounds(){
        return new Rectangle(getX(),getY(),width,height);
    }
}
