package Entity;

import java.awt.*;

import WorldNavigator.Handler;
import WorldNavigator.Id;


public abstract class Entity {

    public int x;
    public int y;
    public int height;
    public int width;
    public boolean solid;
    public int valX;
    public int valY;

    public Id id;
    public Handler handler;

    public void setValX(int valX) {
        this.valX = valX;
    }

    public void setValY(int valY) {
        this.valY = valY;
    }

    public Entity(int x, int y, int height, int width, boolean solid,Id id,Handler handler){
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
        this.solid=solid;
        this.id=id;
        this.handler=handler;
    }
    public void die(){
        handler.removeEntity(this);
    }

    public abstract void render(Graphics graphics);

    public abstract void tick();

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
    public Rectangle getBoundTop(){
        return new Rectangle(getX()+10,getY(),width-20,5);
    }
    public Rectangle getBoundBottom(){
        return new Rectangle(getX()+10,getY()+height-5,width-20,5);
    }
    public Rectangle getBoundLeft(){
        return new Rectangle(getX(),getY()+10,5,height-20);
    }
    public Rectangle getBoundRight(){
        return new Rectangle(getX()+width-5,getY()+10,5,height-20);
    }
}
