package nus;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkIO {
    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;
    private BufferedInputStream bis;
    private BufferedOutputStream bos;
    private FileInputStream fis;
    private ObjectInputStream ois;
    

    public NetworkIO(Socket socket) throws IOException{
        //input
        is = socket.getInputStream();
        bis = new BufferedInputStream(is);
        dis = new DataInputStream(bis);
        
        //output
        os = socket.getOutputStream();
        bos = new BufferedOutputStream(os);
        dos = new DataOutputStream(bos);
    }

    public String read() throws IOException{
        return dis.readUTF();
    }

    public String readObject() throws IOException{
        
        return dis.readUTF();
    }

    public void write(String msg)throws IOException{
        dos.writeUTF(msg);
        dos.flush();
    }

    public void close() throws IOException{
        dis.close();
        bis.close();
        is.close();

        dos.close();
        bos.close();
        os.close();
    }
}
