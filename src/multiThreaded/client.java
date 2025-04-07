package multiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
    public Runnable getRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                int port = 8010;
                InetAddress address = null;
                try {
                    address = InetAddress.getByName("localhost");
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
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
        };

    }
    public static void main(String args[]) throws IOException {

        client client = new client();
        for (int i=0;i<100;i++){
            try{
                Thread thread=new Thread(client.getRunnable());
                thread.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
