package multiThreaded;

import singleThreaded.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
    public Consumer<Socket> getconsumer(){
        return(clientSocket)->{
            try{
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                toClient.println("hello from the server");
                toClient.close();
                clientSocket.close();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        };
    };

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        int port = 8010;
        try{ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Waiting for client on port " + port);
        while (true) {
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(()->server.getconsumer().accept(socket));
            thread.start();

        }

    }catch (IOException ex){
        ex.printStackTrace();}
    }
}
