package Entity;

import WorldNavigator.Handler;
import WorldNavigator.Id;

import java.net.InetAddress;

public class playerMp extends Player{

    public InetAddress ipAddress;
    public int port;
    public playerMp(int x, int y, int height, int width, boolean solid, Id id, Handler handler, InetAddress ipAddress,int port,String username) {
        super(x, y, height, width, solid, id, handler,username);
        this.ipAddress=ipAddress;
        this.port=port;
    }
    public playerMp(int x, int y,int height, int width,boolean solid, Id id,  InetAddress ipAddress,int port,String username) {
        super(x, y, height, width, true, id, null,username);
        this.ipAddress=ipAddress;
        this.port=port;
    }



    @Override
    public void tick(){
        super.tick();
    }
}
