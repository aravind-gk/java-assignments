import java.net.DatagramPacket; import java.net.DatagramSocket; import java.net.InetAddress;

class ClientUDP
{
    public static void main(String[] args)
    {
        try         {            
            String s1="USER:"+System.getProperty("user.name")+" OS: "+System.getProperty("os.name");
            byte[] msg=s1.getBytes();            
            InetAddress address=InetAddress.getByName("localhost");
            DatagramPacket packet=new DatagramPacket(msg,msg.length,address,50000);
            DatagramSocket dsocket=new DatagramSocket();
            dsocket.send(packet);
            byte[] buffer=new byte[2048]; 
            DatagramPacket packet1=new DatagramPacket(buffer, buffer.length);
            dsocket.receive(packet1);
            byte[] b=packet1.getData();           
            String msg1=new String(b);
            System.out.println("From Server "+msg1); 
            dsocket.close();            
                   } catch(Exception e)      {}
    }    
}

