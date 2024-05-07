package handler;

import java.nio.charset.Charset;
import java.util.UUID;
import java.util.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SendToClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(SendToClientHandler.class.getName());

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        final ByteBuf inputByteBuf = (ByteBuf) msg;
        final String inputMessage = inputByteBuf.toString(Charset.defaultCharset());

        log.info(inputMessage);

        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) throws Exception { // 읽는 것이 끝났을 때 동작
        ctx.flush();
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        log.warning("예외 발생");
        cause.printStackTrace();
        ctx.close();
    }
}
