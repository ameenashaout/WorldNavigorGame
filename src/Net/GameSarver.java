package Net;

import Entity.Player;
import Entity.playerMp;
import Net.Packets.Packet;
import Net.Packets.Packet00Login;
import Net.Packets.Packet01Disconnect;
import WorldNavigator.GameMain;
import WorldNavigator.Handler;
import WorldNavigator.Id;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class GameSarver extends Thread {
    private DatagramSocket socket;
    private GameMain gameMain;
    private List<playerMp> connectedPlayers=new ArrayList<playerMp>();

    public GameSarver( GameMain gameMain) {

        this.gameMain = gameMain;

        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
    public void addConnection(playerMp player, Packet00Login packet) {
        boolean alreadyConnected = false;
        for (playerMp p : this.connectedPlayers) {
            if (player.getUsername().equalsIgnoreCase(p.getUsername())) {
                if (p.ipAddress == null) {
                    p.ipAddress = player.ipAddress;
                }
                if (p.port == -1) {
                    p.port = player.port;
                }
                alreadyConnected = true;
            } else {
                // relay to the current connected player that there is a new
                // player
                sendData(packet.getData(), p.ipAddress, p.port);

                // relay to the new player that the currently connect player
                // exists
                packet = new Packet00Login(p.getUsername(), p.x, p.y);
                sendData(packet.getData(), player.ipAddress, player.port);
            }
        }
        if (!alreadyConnected) {
            this.connectedPlayers.add(player);
        }
    }
    public void run(){
        byte[] data=new byte[1024];
        DatagramPacket packet=new DatagramPacket(data,data.length);
        try{
            socket.receive(packet);
        }
        catch (IOException e){
            e.printStackTrace();
        }
     parsePacket(packet.getData(),packet.getAddress(),packet.getPort());
      }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        String massage=new String(data).trim();
        Handler handler=new Handler();
        Packet.packetTypes type= Packet.lookupPacket(massage.substring(0,2));
        Packet packet1 = null;
      switch (type) {
          default:
          case INVALID:
              break;
          case LOGIN:
              packet1 = new Packet00Login(data);
              System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                      + ((Packet00Login) packet1).getUserName() + " has connected...");
              playerMp player = new playerMp(250,800,32,32,true, Id.Player,address,port,((Packet00Login)  packet1).getUserName());
              this.addConnection(player, (Packet00Login) packet1);
              break;
          case DISCONNE:
              packet1 = new Packet01Disconnect(data);
              System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                      + ((Packet01Disconnect) packet1).getUsername() + " has left...");
              this.removeConnection((Packet01Disconnect) packet1);
              break;

              }
              }

        public void removeConnection(Packet01Disconnect packet) {
        this.connectedPlayers.remove(getPlayerMPIndex(packet.getUsername()));
        packet.writeData(this);
          }

       public playerMp getPlayerMP(String username) {
        for (playerMp player : this.connectedPlayers) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
       }
          public int getPlayerMPIndex(String username) {
        int index = 0;
        for (playerMp player : this.connectedPlayers) {
            if (player.getUsername().equals(username)) {
                break;
            }
            index++;
        }
        return index;
    }
    public void sendData(byte[] data, InetAddress ipaddress,int port){
        DatagramPacket packet=new DatagramPacket(data,data.length,ipaddress,port);
        try {
            socket.send(packet);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendDataToAllClient(byte[] data) {
     for (playerMp p: connectedPlayers){
     sendData(data,p.ipAddress,p.port);
      }
     }
}
