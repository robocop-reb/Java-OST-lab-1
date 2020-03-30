package sample;

import java.net.*;
import java.io.*;


public class ServerOst {
    private ServerSocket socket = null;
    private Socket clientSocket = null;
    private DataInputStream consoleInput = null;

    public static int[] getNum(Socket socket) throws IOException {
        int num[]= {0,0,0,0,0,0};
        DataInputStream inFromClient = new DataInputStream(socket.getInputStream());
        String[] numUtf = inFromClient.readUTF().split(" ");
        for(int i = 0; i<numUtf.length; i++){
            num[i] = Integer.parseInt(numUtf[i]);
        }
        return num;

    }
    public static void sendRes(Socket socket,String res) throws IOException {
        DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
        toClient.writeUTF(res);

    }
    public static int checkNum(int num[]){
        int result = 0;
        for (int i = 0; i < num.length; i++) {
            if(num[i] % 3 == 0){
                result++;
            }
        }
        return result;
    }
    public Socket start(int port) throws IOException {
        try {
            socket = new ServerSocket(port);
            clientSocket = socket.accept();
            consoleInput = new DataInputStream(new BufferedInputStream(System.in));
            if (clientSocket != null) {
                System.out.println("connected");
            }
        }catch (UnknownHostException i){
            System.out.println(i);
        }
        return clientSocket;
    }

    public void stop(boolean des) throws IOException {
        if(des == false) {
            // input.close();
            // output.close();
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerOst server = new ServerOst();
        Socket socket = server.start(5000);
        System.out.println("Awaiting a numbers");
        sendRes(socket, "ammount of numbers divided by three " + checkNum(getNum(socket)));
      //  System.out.println(num[1]);
        System.out.println("enter zero to kill server");
        boolean des = server.consoleInput.readBoolean();
        server.stop(des);



    }
}