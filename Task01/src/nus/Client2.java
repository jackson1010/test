// package myapp;

// import java.io.Console;
// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.net.Socket;

// public class Client {

//     public static void main(String[] args) {
//         Console cons = System.console();
//         String[] arrSplit = args[0].split(",");
//         // name
//         System.out.println(arrSplit[0]);
//         // port no.
//         System.out.println(arrSplit[1]);
        
//         try {
//             while (true) {
//                 // create a new socket with the server's host and port no.
//                 Socket sock = new Socket(arrSplit[0], Integer.parseInt(arrSplit[1]));
//                 // open input stream from the socket to read from server
//                 InputStream is = sock.getInputStream();
//                 DataInputStream dis = new DataInputStream(is);
//                 // open output stream from the socket to send data to the client
//                 OutputStream os = sock.getOutputStream();
//                 DataOutputStream dos = new DataOutputStream(os);

//                 String response = dis.readUTF();
//                 System.out.println(response);

//                 String[] array = response.trim().split("_");
//                 int count = 0;
//                 for (String num : array) {
//                     count += Integer.parseInt(num);
//                 }
//                 // String str = Integer.toString(count);

//                 // Write the user input to the client
//                 dos.writeUTF(Integer.toString(count));
//                 // flush the output stream
//                 dos.flush();

//                 is.close();
//                 os.close();
//                 sock.close();
//             }

//         } catch (NumberFormatException | IOException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//     }

// }