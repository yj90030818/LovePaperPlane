import java.awt.BorderLayout;
import javax.swing.JFrame;


public class GameFrame extends JFrame{
    final static int WIDTH = 800;
    final static int HEIGHT = 500;
    public GameFrame(String title, GamePanel panel){
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.add(panel, BorderLayout.CENTER);
    }
}
