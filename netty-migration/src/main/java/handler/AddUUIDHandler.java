package handler;

import java.nio.charset.Charset;
import java.util.UUID;
import java.util.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AddUUIDHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = Logger.getLogger(AddUUIDHandler.class.getName());

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
        log.info("channelRegistered");
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive");
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        final ByteBuf inputByteBuf = (ByteBuf) msg;
        final String inputMessage = inputByteBuf.toString(Charset.defaultCharset());

        final String modifiedMessage = UUID.randomUUID() + ": " + inputMessage;
        final byte[] newByteBuf = modifiedMessage.getBytes(Charset.defaultCharset());

        ctx.write(ctx.alloc().buffer().writeBytes(newByteBuf));
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) throws Exception { // 읽는 것이 끝났을 때 동작
        ctx.flush();
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        log.warning("exception occurred");
        cause.printStackTrace();
        ctx.close();
    }
}
