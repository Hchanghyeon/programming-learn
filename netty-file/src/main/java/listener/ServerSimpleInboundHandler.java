package listener;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import repository.TextRepository;

import java.util.logging.Logger;

public class ServerSimpleInboundHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger log = Logger.getLogger(ServerSimpleInboundHandler.class.getName());
    private TextRepository textRepository;
    private StringBuffer stringBuffer;


    public ServerSimpleInboundHandler(final TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        stringBuffer = new StringBuffer();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String text) throws Exception {
        // 데이터 수신 시, 임시 버퍼에 데이터 추가
        stringBuffer.append(text);

        // 데이터 끝 확인
        if (stringBuffer.toString().endsWith("END")) {
            textRepository.save(stringBuffer.toString());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
