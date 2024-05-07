import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketServer {

    private static final Logger log = Logger.getLogger(SocketServer.class.getName());
    private static final int PORT = 3000;
    private static final String EXIT_MESSAGE = "exit";

    public static void main(String[] args) {
        final SocketServer socketServer = new SocketServer();
        socketServer.start(); // 소켓 서버 시작
    }

    private void start() {
        try (
                final ServerSocket serverSocket = new ServerSocket(PORT); // 서버 소켓 오픈
                final Socket socket = serverSocket.accept(); // 서버 소켓에 대한 연결을 허용하고, 접속까지 대기
                final BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                final BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            log.info("Socket Connected: " + socket.getRemoteSocketAddress());

            while (true) { // 소켓 연결 후 무한 입력 대기
                final String clientMessage = input.readLine();
                log.info("[Client]: " + clientMessage);

                if (clientMessage.equals(EXIT_MESSAGE)) {
                    log.info("클라이언트가 종료 요청함에 따라 소켓을 종료 합니다.");
                    output.write("Socket Closed \n");
                    output.flush();
                    break;
                }

                output.write("[Server]: " + clientMessage + "\n");
                output.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("소켓 연결 중 입출력 오류 발생, {}", e);
        }
    }
}
