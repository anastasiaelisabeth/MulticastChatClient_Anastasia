import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class MulticastChatClient {
    public static void main(String args[])
            throws Exception {

        //Default port number we are going to use
        int portnumber = 5010;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }
        //create multicastSocket
        MulticastSocket chatMulticastSocket = new MulticastSocket(portnumber);
        System.out.println("MulticastSocket is created at port " + portnumber);

        //Determine the IP address of a host, given the host name
        InetAddress group = InetAddress.getByName("225.4.5.6")  ;

        //Joins a Multicast group
        chatMulticastSocket.joinGroup(group);

        //Prompt user to enter a message
        String msg = " ";
        System.out.println("Type a message for the server:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        msg = br.readLine();

        //Send thge message to Multicast Address
        DatagramPacket data = new DatagramPacket(msg.getBytes(),0, msg.length(), group, portnumber);
        chatMulticastSocket.send(data);

        //Close the socket
        chatMulticastSocket.close();
    }
}
