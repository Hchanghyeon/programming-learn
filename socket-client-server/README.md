## **1. Socket**

### 1.1 정의

- TCP/IP 기반 네트워크 통신에서 **데이터 송수신의 마지막 접점**을 의미
- 소켓통신은 **소켓을 통해 서버-클라이언트**간 데이터를 주고받는 **양방향 연결 통신**을 의미
- 클라이언트 소켓과 서버 소켓으로 구분되며, 서버에서 소켓을 Open해두면 클라이언트에서 접속하는 방식(접속하기 위한 Port와 IP가 확인되어야 함)

### 1.2 Socket Server 구현(단일 요청 서버)

```java
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
```

- **BufferedReader**
    - 버퍼를 이용해서 입력을 모았다가 한번에 데이터를 전달하여, 효율적이고 속도가 빠름
- I**nputStreamReader**
    - InputStream을 **문자 단위의 데이터(char)**로 변환시키는 중간다리 역할
        - InputStream은 Byte단위로 데이터를 읽음
        - Stream은 데이터가 전송되는 통로라고 볼 수 있음
- **BufferedWriter와 OutputStreamReader도 동일함**
- **Java.util에 있는 기본 자바 Logger 사용**
    - System.out.print(), println() 등 의 경우 내부적으로 synchronized를 사용하여 성능 저하를 유발하기 때문에 가급적 Logger를 이용해서 사용
        
        **[PrintStream.class]**
        
        ![1](https://github.com/Hchanghyeon/programming-learn/assets/92444744/f320f9dd-aa91-4509-9e8f-99d5b010e5a6)

        
- **try-with-resources 사용**
    - 기본 **try-catch-finally**의 경우 사용 후 close 메서드 호출이 필요했었음
        - 자원 반납에 의해 코드 복잡성 증가
        - 작업이 번거로움
    - try-with-resources를 통해 **자원을 자동 반납 가능**
        - AutoCloseable을 구현하고 있는 클래스만 가능함
            
            ![2](https://github.com/Hchanghyeon/programming-learn/assets/92444744/251f7dca-ceba-4801-a0a5-90a810165819)

            
        - ServerSocket도 Closeable을 구현한 클래스이면서 Closeable은 AuthCloseable을 상속받은 인터페이스임
        - 이미 만들어져있는 클래스들이 AutoCloseable로 구현해야하는 번거로움을 피하기 위해 AutoCloseable을 Closedable의 부모로 만들어서 처리함

### 1.3 Socket Server 구현(멀티쓰레드 환경 서버)

위에서 구현된 서버는 현재 하나의 쓰레드에서만 동작하고 있기 때문에 여러 개의 요청을 받아서 처리할 수 없는 구조로, 여러 개의 클라이언트를 처리하기 위해서는 하나의 클라이언트당 하나의 쓰레드를 부여해서 처리할 수 있도록 해야 함

```java
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
```

- Executors로 ThreadPool을 10개 생성해두고, execute를 이용하여 새로운 스레드를 생성하여 소켓을 맵핑
- 나머지 구현 로직은 전부 동일

### 1.4 Socket Client 구현

```java
import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketClient {

    private static final Logger log = Logger.getLogger(SocketClient.class.getName());
    private static final int SERVER_PORT = 3000;
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
                    output.write(message + "\n"); // 서버쪽도 안전한 종료를 위해서 exit 명령어 전달
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
```

- **output으로 write시** 항상 **“\n”**을 붙여주어야 함
    - 반대쪽에서 BufferedReader로 읽어들이는데 readLine() 메서드 수행시 **\n**으로 입력의 끝을 인지하기 때문에 “\n”을 보내지 않으면 끝으로 인식하지 못하여 계속 대기상태에 빠짐

### 1.5 Socket Server/Client 실행 화면

![3](https://github.com/Hchanghyeon/programming-learn/assets/92444744/f990b4a7-c514-44d7-b583-aee10b8e3e92)


### 1.6 Socket Server/Client 멀티 쓰레드 환경 실행 화면

![4](https://github.com/Hchanghyeon/programming-learn/assets/92444744/199b1f46-8d52-4fcd-9e86-454384f5cc1c)
