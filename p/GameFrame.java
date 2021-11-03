import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame{

    Image planeImg = GameUtil.getImage(1,"images/pp.png");
    Image slingshotImg = GameUtil.getImage(2,"images/ps.png");
    
    Paperplane plane = new Paperplane(planeImg,55,400,0,0);
    Slingshot slingShot = new Slingshot(slingshotImg,50,400,plane);

    public static void main(String[] args){
        GameFrame f = new GameFrame();
        f.launchFrame();
    }

    public void launchFrame(){
        setTitle("Love PaperPlane");
        setSize(800 , 500);
        setVisible(true);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        PaintThread thread = new PaintThread();
        thread.start();
        addKeyListener(new KeyMonitor());
        addMouseListener(new MouseMonitor());
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,800,500);
        plane.drawSelf(g);
        slingShot.drawSelf(g);
    }

    class PaintThread extends Thread{
        @Override
        public void run(){
            while(true){
                repaint();
                try{
                    Thread.sleep(40);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class MouseMonitor extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            slingShot.firstpoint(e);
        }
        
        @Override
        public void mouseReleased(MouseEvent e){
            slingShot.lastpoint(e);
            plane.mouseRelease(e);
        }

        @Override
        public void mouseDragged(MouseEvent e){
            slingShot.middlepoint(e);
        }
    }

    class KeyMonitor extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            plane.keyRelease(e);
        }
    }
}
