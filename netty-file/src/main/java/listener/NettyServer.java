package listener;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import repository.TextRepository;

import java.util.logging.Logger;

public class NettyServer {

    private static final Logger log = Logger.getLogger(NettyServer.class.getName());
    private static final int PORT = 8005;

    public static void main(String[] args) {
        final NettyServer nettyServer = new NettyServer();
        nettyServer.start();
    }

    private void start() {
        final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            final ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(final SocketChannel socketChannel) throws Exception {
                            final ChannelPipeline channelPipeline = socketChannel.pipeline();
                            channelPipeline.addLast(new StringDecoder(), new ServerSimpleInboundHandler(new TextRepository()));
                        }
                    });

            final ChannelFuture future = bootstrap.bind(PORT).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException("인터럽트 발생", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}