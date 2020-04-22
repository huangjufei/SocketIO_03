package bhz.twoday.netty.ende1;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {


    /**
     * 服务连接上了就会调用这个方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" server channel active... ");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //因为定义了String类型解码器所以这里不需要转String类型，Client端就需要转
        System.out.println("Server :" +  msg);
        //准备发给客服端的消息，且还多加了一些分隔符进去
        String response = "服务器响应：" + msg + "$_=$*$_";
        //向客服端写消息
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) throws Exception {
        ctx.close();
    }


}
