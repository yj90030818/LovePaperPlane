import javax.swing.JFrame;
// import javax.swing.JPanel;
// import javax.swing.JLabel;
// import javax.swing.JButton;
// import javax.swing.ImageIcon;
// import java.awt.Color;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.Image;
import java.awt.BorderLayout;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
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
