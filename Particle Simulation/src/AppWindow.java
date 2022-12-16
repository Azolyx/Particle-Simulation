import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AppWindow extends JFrame {
    AppWindow(AppManager appManager, String title, int width, int height, int numBuffers) {
        super(title);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        createBufferStrategy(numBuffers);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                appManager.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                appManager.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                appManager.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                appManager.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                appManager.mouseExited(e);
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                appManager.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                appManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                appManager.keyReleased(e);
            }
        });
    }
}
