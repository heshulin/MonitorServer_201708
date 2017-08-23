package SocketServer;

/**
 * Created by HeShulin on 2017/8/16.
 */
public class ByteAndInt {
    private static ByteAndInt byteAndInt = null;

    public synchronized ByteAndInt getByteAndInt(){
        if(byteAndInt==null){
            byteAndInt=new ByteAndInt();
        }
        return byteAndInt;
    }
    private ByteAndInt(){};
    public static byte[] int2byte(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);
        targets[1] = (byte) ((res >> 8) & 0xff);
        targets[2] = (byte) ((res >> 16) & 0xff);
        targets[3] = (byte) (res >>> 24);
        return targets;
    }

    public static int byte2int(byte[] res) {
        // 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000

        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或
                | ((res[2] << 24) >>> 8) | (res[3] << 24);
        return targets;
    }
}
