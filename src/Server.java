import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8888; // Порт, на котором сервер будет слушать подключения

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен и ожидает подключений...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Получено новое подключение: " + clientSocket);

// Чтение данных от клиента
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String data = in.readLine();

                System.out.println("Полученные данные: " + data);

// Добавьте здесь код для сохранения данных в файл или их дальнейшей обработки

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}