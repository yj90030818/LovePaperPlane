import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame{
GamePanel panel;
    
    public static void main(String[] args){
        GameFrame f = new GameFrame();
        f.setVisible(true);
    }

    public GameFrame(){
        this.setTitle("Love PaperPlane");
        this.setSize(800 , 540);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GamePanel();        
        this.add(panel, BorderLayout.CENTER);
    }
}
