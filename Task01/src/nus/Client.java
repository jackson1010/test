package nus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;

public class Client {
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("Usage: java ... .Main <server> <port>\n");
            return;
        }

        String host = args[0];
        int serverPort = Integer.parseInt(args[1]);
        Socket socket = null;
        List<Float> numList = new LinkedList<>();

        try {
            socket = new Socket(host, serverPort);
            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected\n");

            String input = objectIn.readUTF();
            System.out.println("The Object has been read from the socket");
            System.out.println(input);
            String[] numbers = input.split(",");
            float sum = 0;
            float num1 = 0;
            float standardDeviation = 0;
            String name = "KYAW ZIN WIN";
            String email = "zinwin.1994@gmail.com";

            for (String number : numbers) {
                numList.add(Float.parseFloat(number));
                sum += Float.parseFloat(number);
            }
            float mean = sum / numList.size();
            System.out.println(mean);

            for (float num : numList) {
                num1 += Math.pow(num - mean, 2);
            }
            standardDeviation = (float) Math.sqrt(num1 / numList.size());
            System.out.println(standardDeviation);

            ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
            objectOut.writeUTF(name);
            objectOut.flush();
            objectOut.writeUTF(email);
            objectOut.flush();
            objectOut.writeFloat(mean);
            objectOut.flush();
            objectOut.writeFloat(standardDeviation);
            objectOut.flush();

            objectIn.close();
            objectOut.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}