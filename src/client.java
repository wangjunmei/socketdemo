import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.net.Socket;


public class client {
    ClientLaunch cl = new ClientLaunch();
    Socket socket;
    socketWriter socketWriter = new socketWriter();
    InputStream socketReader;
    @Test
    public void cardFlow() throws Exception {
        // 建立 socket链接，连接服务器192.168.1.1:8080
        socket = new Socket("192.168.161.69", 7838);

        //String charger="00000000136";
        //登陆，通过验证输出报文中的响应报文是否为 "000000"，判断是否登陆成功，其中loginInput为输出报文字符串
        String loginInput="abc";
        String outlogin = cl.Client(socket, socketWriter, socketReader, loginInput);
        System.out.println(outlogin);
        Assert.assertEquals(outlogin.substring(30,36), "000000");

        //心跳，通过验证输出报文中的响应报文是否为 "000000"，判断是否交互成功
        String heartbeatInput="283400130001";
        String outheartbeat = cl.Client(socket, socketWriter, socketReader, heartbeatInput);
        Assert.assertEquals(outheartbeat.substring(30,36), "000000");

        socket.close();
    }
}
