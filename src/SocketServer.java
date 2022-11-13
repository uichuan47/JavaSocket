import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
    public static void main(String[] args) throws Exception {
        System.out.println("server启动，等待连接");
        //1.创建ServerSocket对象，绑定端口，开始等待连接
        ServerSocket ss = new ServerSocket(6666);
        //2.接受连接，accept方法，返回Socket对象
        Socket server = ss.accept();
        while (true) {
            //3.通过Socket获取输入流
            InputStream in = server.getInputStream();
            //4.读取数据
            byte[] b = new byte[1024];
            int len = in.read(b);
            //接收到的数据
            String info = new String(b, 0, len);
            System.out.println("客户端接收的数据：" + info);

            if (info.equals("over")) {

                System.out.println("服务器已关闭");
                in.close();
                server.close();
                break;
            }
//-------------------服务器端回复信息----------------------//
            System.out.println("服务器回复：");
            OutputStream out = server.getOutputStream();
            Scanner input = new Scanner(System.in);
            String str = input.next();
            out.write(str.getBytes());

        }
    }
}
