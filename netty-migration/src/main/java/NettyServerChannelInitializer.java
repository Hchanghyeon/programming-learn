import handler.AddUUIDHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    // 채널 초기화
    @Override
    protected void initChannel(final SocketChannel ch) throws Exception {
        final ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new AddUUIDHandler()); // 메시지에 UUID 추가하는 핸들러
    }
}
