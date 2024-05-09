package listener;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import repository.TextRepository;

import java.util.logging.Logger;

public class ServerInboundHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(ServerInboundHandler.class.getName());
    private final TextRepository textRepository;

    public ServerInboundHandler(final TextRepository textRepository){
        this.textRepository = textRepository;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final String inputMessage = (String) msg;

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("호출됨");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warning(cause.getMessage());
    }
}
