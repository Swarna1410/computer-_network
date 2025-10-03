import java.net.*;
import java.util.*;

public class DatagramSocketServer {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        // Create datagram socket at server side, bind to port 9000
        DatagramSocket serverSocket = new DatagramSocket(9000);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        System.out.println("*** Server Side ***");

        // First receive packet from client
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);

        System.out.println(new String(receivePacket.getData()));

        // Get client IP and port
        InetAddress IPAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();

        while (true) {
            System.out.print("Type some message to display at client end: ");
            String message = in.nextLine();

            sendData = message.getBytes();

            System.out.println("Message sent from the server: " + message);

            // Create send packet
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

            // Send to client
            serverSocket.send(sendPacket);
        }
    }
}
