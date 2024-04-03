package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class SocketServer {

    private static final Map<Long, Socket> socketMap = new ConcurrentHashMap<>();
    private static final AtomicLong socketNumber = new AtomicLong(0L);
    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        try (final ServerSocket serverSocket = new ServerSocket(PORT);
             final ExecutorService executorService = Executors.newFixedThreadPool(5)
        ) {
            while (!serverSocket.isClosed()) {
                final Socket connectedSocket = serverSocket.accept();
                socketNumber.compareAndSet(socketNumber.get(), socketNumber.get() + 1);
                final Long connectedSocketNumber = socketNumber.get();

                socketMap.put(connectedSocketNumber, connectedSocket);

                createClientSocketThread(executorService, connectedSocket, connectedSocketNumber);
            }
        }
    }

    private static void createClientSocketThread(
            final ExecutorService executorService,
            final Socket connectedSocket,
            final Long connectedSocketNumber
    ) {
        executorService.submit(() -> {
            try (connectedSocket;
                 BufferedReader reader = new BufferedReader(
                         new InputStreamReader(connectedSocket.getInputStream())
                 );
            ) {
                String fromClient;
                while ((fromClient = reader.readLine()) != null) {
                    final String message = "[수신된 메시지 - " + connectedSocketNumber + "번]: " + fromClient;

                    if ("close".equals(fromClient)) {
                        break;
                    }

                    sendToClientMessage(connectedSocket, message);
                }
            } catch (IOException e) {
                System.out.println("오류: " + e.getMessage());
            } finally {
                socketMap.remove(connectedSocketNumber);
                try {
                    connectedSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println(connectedSocketNumber + "번 유저 종료");
                }
            }
        });
    }

    private static void sendToClientMessage(final Socket connectedSocket, final String fromClient) throws IOException {
        for (Socket socket : socketMap.values()) {
            if (connectedSocket.equals(socket)) {
                continue;
            }

            if (!socket.isClosed()) {
                new PrintWriter(socket.getOutputStream(), true).println(fromClient);
            }
        }
    }
}
