package client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import listener.NettyServer;

import java.util.logging.Logger;

public class NettyClient {
    private static final Logger log = Logger.getLogger(NettyServer.class.getName());
    private static final String HOST_NAME = "localhost";
    private static final int PORT = 8005;

    public static void main(String[] args) {
        final NettyClient nettyClient = new NettyClient();
        nettyClient.connectToServer();
    }

    private void connectToServer() {
        final EventLoopGroup clientBootstrap = new NioEventLoopGroup();

        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientBootstrap)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .remoteAddress(HOST_NAME, PORT)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(final Channel channel) throws Exception {
                            final ChannelPipeline channelPipeline = channel.pipeline();
                            channelPipeline.addLast(new StringEncoder(), new ClientInboundHandler());
                        }
                    });

            final ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            throw new RuntimeException("Exception Occurred", e);
        } finally {
            clientBootstrap.shutdownGracefully();
        }
    }
}