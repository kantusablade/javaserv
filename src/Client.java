import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Адрес сервера
        int serverPort = 8888; // Порт сервера

        try {
// Получение данных о системе
            double cpuLoad = ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
            String OC = ManagementFactory.getOperatingSystemMXBean().getName();
            long freeMemory = Runtime.getRuntime().freeMemory();

// Формирование строки с данными
            String data = "CPU Load: " + cpuLoad + ", IP Address: " + OC + ", Free Memory: " + freeMemory;

// Сохранение данных в файл
            saveToFile("data.txt", data);

// Отправка данных на сервер
            Socket socket = new Socket(serverAddress, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(data);

            System.out.println("Данные отправлены на сервер.");

            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveToFile(String filePath, String data) {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            writer.println(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}