import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class MultiSocketServer {

    private static final Executor executor = Executors.newFixedThreadPool(10);
    private static final Map<Socket, BufferedWriter> sockets = new ConcurrentHashMap<>();
    private static final Logger log = Logger.getLogger(MultiSocketServer.class.getName());
    private static final int PORT = 5000;
    private static final String EXIT_MESSAGE = "exit";

    public static void main(String[] args) {
        final MultiSocketServer multiSocketServer = new MultiSocketServer();
        multiSocketServer.start();
    }

    private void start() {
        try (
                final ServerSocket serverSocket = new ServerSocket(PORT);
        ) {
            while (true) {
                final Socket socket = serverSocket.accept();
                createSocket(socket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createSocket(final Socket socket) {
        executor.execute(() -> {
            try (
                    final BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    final BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ) {
                log.info("Connected Socket: " + socket.getRemoteSocketAddress());
                sockets.put(socket, output);

                while (true) {
                    final String clientMessage = input.readLine();
                    log.info("[" + socket + "]님: " + clientMessage + "\n");

                    if (clientMessage.equals(EXIT_MESSAGE)) {
                        log.info("클라이언트가 종료 요청함에 따라 소켓을 종료 합니다.");
                        output.write("Socket Closed \n");
                        output.flush();
                        break;
                    }

                    sendToClients(clientMessage);
                }
            } catch (IOException e) {
                throw new RuntimeException("소켓 연결 중 입출력 오류 발생, {}", e);
            }
        });
    }

    private void sendToClients(final String message) throws IOException {
        for (final Map.Entry<Socket, BufferedWriter> socket : sockets.entrySet()) {
            socket.getValue().write("[" + socket.getKey() + "]님: " + message + "\n");
            socket.getValue().flush();
        }
    }
}
