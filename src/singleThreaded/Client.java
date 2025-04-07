package singleThreaded;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public void run() throws Exception {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        try (Socket socket = new Socket(address, port)) {
            PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toSocket.println("hello from client");
            String response = fromSocket.readLine();
            System.out.println("response from the socket is:"+response);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        try {
            client.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
