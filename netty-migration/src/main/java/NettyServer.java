import java.util.logging.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    private static final Logger log = Logger.getLogger(NettyServer.class.getName());
    private static final int PORT = 8000;

    public static void main(String[] args) {
        final NettyServer nettyServer = new NettyServer();
        nettyServer.start();
    }

    private void start() {
        final EventLoopGroup bossGroup = new NioEventLoopGroup(1); // 부모 그룹은 클라이언트의 연결을 수락하는 그룹
        final EventLoopGroup workerGroup = new NioEventLoopGroup(); // 연결된 클라이언트의 소켓으로부터 데이터 입출력 및 이벤트를 처리

        try {
            final ServerBootstrap bootstrap = new ServerBootstrap(); // 네티 서버에 대한 역할 설정(채널과 이벤트 루프 그룹의 초기화 등)
            bootstrap.group(bossGroup, workerGroup) // group 등록
                    .channel(NioServerSocketChannel.class) // 소켓 채널을 비동기로 할 것인지 동기로 할 것인지
                    .childHandler(new NettyServerChannelInitializer()); // 채널 초기화하면서 파이프라인 및 핸들러 등록

            final ChannelFuture future = bootstrap.bind(PORT).sync(); // 비동기로 수행되는 bind() 처리를 sync를 통해 Blocking
            future.channel().closeFuture().sync(); // 서버 종료도 Blocking을 하여 동기적으로 처리하도록 구현
        } catch (InterruptedException e) {
            throw new RuntimeException("인터럽트 발생", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
