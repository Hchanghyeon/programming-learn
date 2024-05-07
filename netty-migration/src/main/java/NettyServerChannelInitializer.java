import handler.AddUUIDHandler;
import handler.SendToClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(final SocketChannel ch) throws Exception {
        final ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new AddUUIDHandler());
        channelPipeline.addLast(new SendToClientHandler());
    }
}
