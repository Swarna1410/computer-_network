import java.net.*;

public class DatagramSocketClient {
    public static void main(String[] args) throws Exception {
        String line = "Connected with Client";

        // Create datagram socket at client side
        DatagramSocket clientSocket = new DatagramSocket();

        // Get IP Address of server
        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        sendData = line.getBytes();

        // Create send packet
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9000);

        // Send packet
        clientSocket.send(sendPacket);

        System.out.println("***** Client Display Terminal *****");

        while (true) {
            // Create receive packet
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Receive from server
            clientSocket.receive(receivePacket);

            // Convert received data to string
            String messageReceived = new String(receivePacket.getData(),
                    receivePacket.getOffset(),
                    receivePacket.getLength());

            System.out.println("Message typed at server side is : " + messageReceived);
        }
    }
}
