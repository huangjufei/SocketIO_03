package bhz.twoday.netty.serial;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.File;
import java.io.FileOutputStream;

import bhz.threeday.utils.GzipUtils;

public class ServerHandler extends ChannelHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Req req = (Req)msg;
		System.out.println("Server : " + req.getId() + ", " + req.getName() + ", " + req.getRequestMessage());
		byte[] attachment = GzipUtils.ungzip(req.getAttachment());
		//写的时候是receive路径下的一个图片
		String path = System.getProperty("user.dir") + File.separatorChar + "receive" +  File.separatorChar + "001.jpg";
		System.out.println("图片保存在="+path);
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(attachment);
        fos.close();
		
		Resp resp = new Resp();
		resp.setId(req.getId());
		resp.setName("resp" + req.getId());
		resp.setResponseMessage("响应内容(都是取的id)" + req.getId());
		ctx.writeAndFlush(resp);//.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	
	
}
