package Entity;

import Net.Packets.Packet02Move;
import Title.Tile;
import Title.Wall;
import WorldNavigator.GameMain;
import WorldNavigator.Handler;
import WorldNavigator.Id;

import java.awt.*;

public class Player extends Entity {
public static String username;
public static int coinp =GameMain.coins;

    public static int getCoinp() {
        return coinp;
    }

    public Player(int x, int y, int height, int width, boolean solid, Id id, Handler handler, String username) {
        super(x, y, height, width, solid, id, handler);
        this.username=username;
    }


    @Override
    public void render(Graphics graphics) {

        if (username != null) {
            graphics.setColor(Color.ORANGE);
      graphics.drawString(this.username,this.x-1,this.y-2);

        }
        graphics.drawImage(GameMain.playerGraph.getImage(),x,y,width,height,null);
    }

    @Override
    public void tick() {
    x+=valX;
    y+=valY;

    for (int i=0;i<handler.tiles.size();i++){
    Tile tile=handler.tiles.get(i);
    if (!tile.solid) break;
    if (tile.getId()== Id.wall&& tile.getId()!=Id.coins &&handler !=null){
        if (getBoundTop().intersects(tile.getBounds())){
            setValY(0);
            y=tile.getY()+tile.height;
        }
        if (getBoundBottom().intersects(tile.getBounds())){
            setValY(0);
            y=tile.getY()-tile.height;
        }
        if (getBoundLeft().intersects(tile.getBounds())){
            setValX(0);
            x=tile.getX()+tile.width;
        }
        if (getBoundRight().intersects(tile.getBounds())){
            setValX(0);
            x=tile.getX()-tile.width;
        }
    }

    if (getBoundTop().intersects(tile.getBounds())&& tile.getId()==Id.coins){
        GameMain.coins++;
        tile.die();
         }
        if (getBoundTop().intersects(tile.getBounds())&& tile.getId()==Id.keyDoor){
            GameMain.coins=GameMain.coins+2;
            tile.die();
        }
        if (tile.getId()==Id.keyTarget){
          if (getBoundTop().intersects(tile.getBounds())) {tile.activate=true;

        }
        }
        if (tile.getId()==Id.keyWin){
            if (getBoundTop().intersects(tile.getBounds())) tile.activate=true;
         }

       }
    }
    public static String getUsername() {
        return username;
    }

}
