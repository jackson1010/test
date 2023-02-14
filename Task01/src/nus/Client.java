package nus;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;

public class Client {
    public static void main(String[] args) throws ClassNotFoundException {
        if(args.length <= 0) {
            System.out.println("Usage: java ... .Main <server> <port>\n");
            return;
        } 

        String host = args[0];
        int serverPort = Integer.parseInt(args[1]);
        Socket socket = null;
        List<Double> numList = new LinkedList<>();

        try {
            socket = new Socket(host, serverPort);
            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                System.out.println("Connected\n");
                
                String input = objectIn.readUTF();
                System.out.println("The Object has been read from the socket");
                System.out.println(input);
                String[] numbers = input.split(","); 
                double sum =0;
                double num1=0;
                double standardDeviation =0;

                for(String number: numbers){
                    numList.add(Double.parseDouble(number));
                    sum += Double.parseDouble(number);
                }
                double mean = sum/numList.size();
                System.out.println(mean);

                for(double num: numList){
                    num1 += Math.pow(num- mean,2);
                }
                standardDeviation = Math.sqrt(num1/numList.size());
                System.out.println(standardDeviation);

                

            objectIn.close();
            socket.close();   
    
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}