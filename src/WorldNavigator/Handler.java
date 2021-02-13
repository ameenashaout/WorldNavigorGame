package WorldNavigator;

import Entity.*;
import Title.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler {
    public LinkedList<Entity> entities = new LinkedList<Entity>();

    public LinkedList<Tile> tiles = new LinkedList<Tile>();

    public Handler() { }
    public synchronized void removePlayerMp(String username) {
        int index = 0;
        for (Entity e : entities) {
            if (e instanceof playerMp && ((playerMp) e).getUsername().equals(username)) {
                break;
            }
            index++;
        }
        this.entities.remove(index);
    }
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public void removeTile(Tile tile) {
        tiles.remove(tile);
    }

    public void render(Graphics graphics) {
        for (Entity entity : entities) {
            entity.render(graphics);
        }
        for (Tile tile : tiles) {
            tile.render(graphics);
        }

    }

    public void tick() {
        for (Entity entity : entities) {
            entity.tick();
        }
        for (Tile tile : tiles) {
            tile.tick();
        }
    }


    public void creatLevel(BufferedImage level) {
        int width=level.getWidth();
        int height=level.getHeight();
        for (int y=0;y<height;y++){
            for (int x=0;x<width;x++){
                int pixel=level.getRGB(x,y);
                int red=(pixel>>16) & 0xFF;
                int blue=(pixel>>8) & 0xFF;
                int green=(pixel) & 0xFF;
                if (red==0&&blue==0&&green==0)
                    addTile(new Wall(x*32,y*32,32,32,true,Id.wall,this));
                if(red==0&&green==255&&blue==0)
                    addTile(new Coin(x*32,y*32,32,32,true,Id.coins,this));
                if(red==255&&green==0&&blue==0)
                    addTile(new KeyDoor(x*32,y*32,32,32,true,Id.keyDoor,this));
                if(red==128&&green<=18&&blue==128)
                    addTile(new PowerUPBlock(x*32,y*32,32,32,true,Id.keyTarget,this,GameMain.keyGraph));
                   if(red==0&&green==0&&blue==255)
                addTile(new TargetPowerUP(x*32,y*32,32,32,true,Id.keyWin,this,GameMain.keyGraph));
                 }
               }
            }
}













