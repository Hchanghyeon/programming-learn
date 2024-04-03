package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClient {

    private static final String serverAddress = "127.0.0.1";
    private static final int serverPort = 3000;
    private static String fromServer;
    private static String fromUser;

    public static void main(String[] args) {
        try (final ExecutorService executorService = Executors.newSingleThreadExecutor();
             final Socket socket = new Socket(serverAddress, serverPort);
             final PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);
             final BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             final BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            // 별도의 스레드에서 소켓 서버로부터 오는 값 받아서 출력
            executorService.submit(() -> {
                try {
                    while ((fromServer = socketInput.readLine()) != null) {
                        System.out.println("서버: " + fromServer);
                    }
                } catch (IOException e) {
                    System.out.println("서버로부터 메시지를 읽는 도중 오류가 발생했습니다: " + e.getMessage());
                }
            });

            // 메인 함수 스레드에서 사용자로부터 값 입력받아서 소켓으로 전달
            while ((fromUser = userInput.readLine()) != null) {
                socketOutput.println(fromUser);
            }
        } catch (IOException e) {
            System.out.println("클라이언트 오류: " + e.getMessage());
        }
    }
}
