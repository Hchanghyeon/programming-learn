import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketClient {

    private static final Logger log = Logger.getLogger(SocketClient.class.getName());
    private static final int SERVER_PORT = 5000;
    private static final String SERVER_IP = "localhost";
    private static final String EXIT_MESSAGE = "exit";

    public static void main(String[] args) {
        final SocketClient socketClient = new SocketClient();
        socketClient.connectToServer();
    }

    private void connectToServer() {
        try (
                final Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                final BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                final BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
                final BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            while (true) {
                final String message = clientInput.readLine();

                if (message.equals(EXIT_MESSAGE)) {
                    log.info("EXIT 입력으로 소켓 연결 종료됩니다.");
                    output.write(message + "\n");
                    output.flush();
                    break;
                }

                output.write(message + "\n");
                output.flush();

                final String serverMessage = serverInput.readLine();
                log.info(serverMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException("소켓 연결 중 입출력 오류 발생, {}", e);
        }
    }
}
