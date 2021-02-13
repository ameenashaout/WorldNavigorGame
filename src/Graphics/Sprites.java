package Graphics;

import java.awt.image.BufferedImage;

public class Sprites {

    public SpritesSheet spritessheet;
    public BufferedImage image;

    public Sprites(SpritesSheet spritessheet,int x,int y){
        image=spritessheet.getSprites(x, y);
    }
    public BufferedImage getImage(){
        return image;
    }
}
