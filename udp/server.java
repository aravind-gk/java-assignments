import java.io.IOException;  import java.net.DatagramPacket; import java.net.DatagramSocket; import java.net.InetAddress;

class ServerUDP
{
    public static void main(String[] agrs)throws IOException
    {
        try
        {
            DatagramSocket dsocket=new DatagramSocket(50000);
            byte[] buffer=new byte[2048];
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
            while(true){
            dsocket.receive(packet);
            byte[] bi=packet.getData();
            String msg=new String(bi,0,packet.getLength());
            System.out.println(msg + " From port: "+ packet.getPort());  
            InetAddress address=packet.getAddress();           
            String msg1="Acknowledged";
            DatagramPacket packet1=new DatagramPacket(msg1.getBytes(),msg1.getBytes().length,address, packet.getPort());
            dsocket.send(packet1);
            }        
        }   catch(Exception e)           { }
    }   
 }

