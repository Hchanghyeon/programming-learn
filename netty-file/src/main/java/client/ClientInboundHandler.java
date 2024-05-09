package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Logger;

public class ClientInboundHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(ClientInboundHandler.class.getName());
    private static final String FILE_PATH = "C:/Users\\changhyeon\\Desktop\\ojt\\netty\\data.txt";
    private static final int MAX_LINE = 100000;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       final File file = new File(FILE_PATH);
       final BufferedReader bufferedReader  = new BufferedReader(new FileReader(file));
       final String data = bufferedReader.readLine();

        System.out.println(data.length());
       ctx.writeAndFlush(data);
    }
}
