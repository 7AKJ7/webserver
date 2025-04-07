package singleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException {
        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            try{

            System.out.println("server listening on port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("connection accepted");
            PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toClient.println("hello from the server");
                String clientMessage = fromClient.readLine();
                System.out.println("Received from client: " + clientMessage);
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try{
            server.run();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
