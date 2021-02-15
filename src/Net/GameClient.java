package Net;

import Entity.playerMp;
import Net.Packets.Packet;
import Net.Packets.Packet00Login;
import Net.Packets.Packet01Disconnect;
import Net.Packets.Packet02Move;
import WorldNavigator.GameMain;
import WorldNavigator.Id;

import java.io.IOException;
import java.net.*;

public class GameClient extends Thread{
    private InetAddress ipaddress;
    private DatagramSocket socket;
    private GameMain gameMain;

    public GameClient(String ipaddress, GameMain gameMain) {

        this.gameMain = gameMain;
        try {
            this.ipaddress = InetAddress.getByName(ipaddress);
            this.socket=new DatagramSocket();
        }catch (SocketException socketException){
            socketException.printStackTrace();
        }
        catch (UnknownHostException unknownHostException){
            unknownHostException.printStackTrace();
        }
    }

    public void run(){

        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        Packet.packetTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet = null;

        switch (type) {
            default:
            case INVALID:
                break;
            case LOGIN:
                packet = new Packet00Login(data);
                handleLogin((Packet00Login) packet, address, port);
                break;
            case DISCONNE:
                packet = new Packet01Disconnect(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                        + ((Packet01Disconnect) packet).getUsername() + " has left the world...");
                gameMain.handler.removePlayerMp(((Packet01Disconnect) packet).getUsername());
                break;

        }
    }
    public void sendData(byte[] data){
        DatagramPacket packet=new DatagramPacket(data,data.length,ipaddress,1331);
        try {
            socket.send(packet);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void handleLogin(Packet00Login packet, InetAddress address, int port) {
        System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUserName()
                + " has joined the game...");
        playerMp player = new playerMp( 400, 600,32,32,true,Id.Player , address, port,packet.getUserName());
        gameMain.handler.addEntity(player);
    }
}
