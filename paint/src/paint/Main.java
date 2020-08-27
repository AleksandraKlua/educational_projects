package paint;

import javax.swing.*;
 
public class Main {
    public static void main(String[] args) { //запуск интерфейса
        SwingUtilities.invokeLater(() -> {
            new Frame();
        });
    }
} 