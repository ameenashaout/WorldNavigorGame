package Net.Packets;

import Net.GameClient;
import Net.GameSarver;

public abstract class Packet {
    public static enum packetTypes{
        INVALID(-1), LOGIN(00), DISCONNE(01);
        private int packetId;
        private packetTypes(int packetId){
            this.packetId=packetId;
        }
        public int getId(){
            return packetId;
        }
    }
    public byte packetId;
    public Packet(int packetId){
        this.packetId=(byte) packetId;
    }
    public abstract void writeData(GameClient client);
    public abstract void writeData(GameSarver sarver);

    public String readData(byte[] data) {
        String message = new String(data).trim();
        return message.substring(2);
    }

    public abstract byte[] getData();
   public static packetTypes lookupPacket(String packetId){
       try {
           return lookupPacket(Integer.parseInt(packetId));
       }catch (NumberFormatException e){
           return packetTypes.INVALID;
       }}

    public static  packetTypes lookupPacket(int id){
        for (packetTypes p:packetTypes.values()){
            if (p.getId() ==id){
                return p;
            }
        }
        return packetTypes.INVALID;
    }

}
