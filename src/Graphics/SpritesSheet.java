package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpritesSheet {

    private BufferedImage sheet;

    public SpritesSheet(String path){
        try {
            sheet= ImageIO.read(getClass().getResource(path));
        }catch (IOException E){
            System.out.println(E);
        }
    }

    public BufferedImage getSprites(int x,int y){
        return sheet.getSubimage(x*32-32,y*32-32,32,32);
    }
}
