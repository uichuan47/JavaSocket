import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Socket client = new Socket("localhost", 6666);
        while (true) {
            OutputStream out = client.getOutputStream();
            System.out.println("客户端发送数据");
            String str = input.next();
            out.write(str.getBytes());
            if (str.equals("over")) {
                System.out.println("客户端已关闭");
                out.close();
                client.close();
                break;
            }
            //-------------------客户器端读取回复信息----------------------//
            InputStream in = client.getInputStream();
            //4.读取数据
            byte[] b = new byte[1024];
            int len = in.read(b);
            String info = new String(b, 0, len);
            System.out.println("这是从客户端接收的数据：" + info);
        }
    }
}
