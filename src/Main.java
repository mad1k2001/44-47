import server.BookServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new BookServer("localhost", 8080).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
