
import handler.ReceiveFromServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class NettyClient {
    private static final Logger log = Logger.getLogger(NettyServer.class.getName());
    private static final String HOST_NAME = "localhost";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        final NettyClient nettyClient = new NettyClient();
        nettyClient.connectToServer();
    }

    private void connectToServer() {
        final EventLoopGroup clientBootstrap = new NioEventLoopGroup(); // 클라이언트는 1개만 생성

        try {
            final Bootstrap bootstrap = new Bootstrap(); // 네티 클라이언트는 ServerBootstrap이 아닌 일반 Bootstrap을 사용
            bootstrap.group(clientBootstrap) // group 등록
                    .channel(NioSocketChannel.class)  // 클라이언트는 SocketChannel로 설정
                    .remoteAddress(HOST_NAME, PORT) // 접속하려는 네티 서버 주소 입력
                    .handler(new ChannelInitializer<Channel>() { // 클라이언트는 Channel로 초기화
                        @Override
                        protected void initChannel(final Channel channel) throws Exception {
                            final ChannelPipeline channelPipeline = channel.pipeline();
                            channelPipeline.addLast(new StringEncoder(), new ReceiveFromServerHandler()); // 채널의 파이프라인에 핸들러 등록
                        }
                    });

            final ChannelFuture future = bootstrap.connect().sync(); // 서버에 연결할 때까지 대기
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                final String message = bufferedReader.readLine();

                if(message.equals("EXIT")){
                    break;
                }

                future.channel().writeAndFlush(message + "\r\n");
            }
            future.channel().closeFuture().sync(); // 서버 종료도 Blocking을 하여 동기적으로 처리하도록 구현
        } catch (Exception e) {
            throw new RuntimeException("Exception Occurred", e);
        } finally {
            clientBootstrap.shutdownGracefully();
        }
    }
}
