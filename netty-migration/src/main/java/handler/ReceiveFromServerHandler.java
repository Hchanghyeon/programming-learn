package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;
import java.util.logging.Logger;

public class ReceiveFromServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(ReceiveFromServerHandler.class.getName());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final ByteBuf inputByteBuf = (ByteBuf) msg;
        final String inputMessage = inputByteBuf.toString(Charset.defaultCharset());

        log.info(inputMessage);
    }
}
