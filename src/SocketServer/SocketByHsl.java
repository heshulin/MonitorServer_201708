package SocketServer;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by HeShulin on 2017/8/16.
 */
public class SocketByHsl {
    private static SocketByHsl socketByHsl = null;
    private static Socket socket = null;
    public static void setSocket(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
    }

    public static synchronized SocketByHsl getSocket(){
        if(socketByHsl==null){
            socketByHsl = new SocketByHsl();
        }
        return socketByHsl;
    }
    public static void postData(byte[] tmpByte) throws IOException {
        socket.getOutputStream().write(tmpByte);

    }
    public static void postData(int  tmpint) throws IOException {
        socket.getOutputStream().write(tmpint);
    }
    private SocketByHsl(){};

}
