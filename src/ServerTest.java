import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

        private ServerSocket ss;
        private Socket socket;
        PrintWriter out;
        private int i = 0;

    public ServerTest() {
            try {
                ss = new ServerSocket(7838);
                while (true) {
                    System.out.println(0);
                    socket = ss.accept();
                    ss.setSoTimeout(50000);
                    byte[] b = new byte[1024];
                    b[0] = (byte) 0x55;
                    b[1] = (byte) 0xAA;
                    b[2] = (byte) 0x01;
                    b[3] = (byte) 0x00;
                    b[4] = (byte) 0x00;
                    b[5] = (byte) 0x00;
                    b[6] = (byte) 0x00;
                    b[7] = (byte) 0x00;
                    b[8] = (byte) 0x00;
                    b[9] = (byte) 0x00;
                    b[10] = (byte) 0x00;
                    b[11] = (byte) 0x00;
                    b[12] = (byte) 0x00;
                    b[13] = (byte) 0x00;
                    b[14] = (byte) 0x00;
                    b[15] = (byte) 0x00;
                    b[16] = (byte) 0x00;

                    InputStream socketReader = socket.getInputStream();
                    OutputStream socketWriter = socket.getOutputStream();
                    System.out.println(b);
                    socketWriter.write(b);
                    System.out.println("OK");
                    socketWriter.flush();
                    i = i + 1;
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            new ServerTest();
        }

}
