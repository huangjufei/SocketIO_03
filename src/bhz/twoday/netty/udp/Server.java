
package bhz.twoday.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;


/**
 * 功能：使用的NioDatagramChannel协议来建立链接
 *  客服端发送请求来后，服务端随机返回一段谚语
 *  服务端不会自动关闭服务，而客户端自动关闭
 */
public class Server {
    public void run(int port) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();

            b.group(group)
                    //这里变为了datagram
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ServerHandler());
            b.bind(port).sync().channel().closeFuture().await();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new Server().run(8765);
        //new Server().run(8764);
    }
}
