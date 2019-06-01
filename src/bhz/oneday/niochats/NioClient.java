package bhz.oneday.niochats;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * NIO客户端
 * 功能有兩個:
 * 1,发送消息到服务端
 * 2,单独线程接受服务端返回的消息
 */
public class NioClient {

    /**
     * 启动(nickname这个参数只是在发送消息的时候使用了)
     */
    public void start(String nickname) throws IOException {
        /**
         * 连接服务器端
         */
        SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("127.0.0.1", 8100));

        /**
         * 接收服务器端响应
         */
        // 新开线程，专门负责来接收服务器端的响应数据
        // 连接服务器的通道,注解到自己的selector上
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();

        /**
         * 向服务器端发送数据
         */
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String request = scanner.nextLine();
            if (request != null && request.length() > 0) {
                socketChannel.write(
                        Charset.forName("UTF-8")
                                .encode(nickname + " : " + request));
            }
        }

    }


}
