import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class Sender {

    public static void main(String[] args) throws Exception {
        // 127.0.0.1 代表本机地址，在 8888 端口上监听
        Sender sender = new Sender("127.0.0.1", 8888);
        String test="FE050000FF009835";
        byte[] bytes=hexStringToBytes(test);
        //byte[] bytes = {15, 16, 17, 120}; // 对应的十六进制是 0F 10 11 78
        sender.send(bytes);
        System.out.println("发送" + Arrays.toString(bytes) + "完毕！");
    }

    private final String host;
    private final int port;

    public Sender(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void send(byte[] bytes) throws IOException {
        Socket socket = new Socket(host, port); // 建立和服务端的 socket

        try (DataOutputStream dos // 建立输出流
                     = new DataOutputStream(socket.getOutputStream())) {
            dos.write(bytes, 0, bytes.length); // 向输出流写入 bytes
        }
    }
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}