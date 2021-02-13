package Net.Packets;

import Net.GameClient;
import Net.GameSarver;


public class Packet00Login extends  Packet{
    public String userName;
    public int x,y;

    public Packet00Login(byte[] data) {
        super(00);
        this.userName= readData(data);

    }
    public Packet00Login(String username, int x, int y) {
        super(00);
        this.userName = username;
        this.x = x;
        this.y = y;
    }

    @Override
    public void writeData(GameClient client) {
client.sendData(getData());
    }

    @Override
    public void writeData(GameSarver sarver) {
sarver.sendDataToAllClient(getData());
    }

    @Override
    public byte[] getData() {
        return ("00"+this.userName).getBytes();
    }
    public String getUserName(){
        return this.userName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
