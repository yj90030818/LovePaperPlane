import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame{

//     Image planeImg = GameUtil.getImage(1,"images/pp.png");
//     Image obstacleImg = GameUtil.getImage(2,"images/po.png");
//     Image slingshotImg = GameUtil.getImage(3,"images/ps.png");
    
//     Paperplane plane = new Paperplane(planeImg,55,400,);
//     Obstacle obstacle = new Obstacle(obstacleImg,650,300);
//     Slingshot slingShot = new Slingshot(slingshotImg,50,400,plane);
    
//     Paperplane plane = new Paperplane(55, 400, 1, "images/pp.png");
//     Obstacle obstacle = new Obstacle(650, 300, 2,"images/po.png");
//     Slingshot slingShot = new Slingshot(50, 400, 3,"images/ps.png", plane);
    
    public static void main(String[] args){
        GameFrame f = new GameFrame();
        f.setVisible(true);
        
//         f.launchFrame();
    }

//     public void launchFrame(){
    public GameFrame(){
        this.setTitle("Love PaperPlane");
        this.setSize(800 , 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel();        
//         setVisible(true);
//         addWindowListener(new WindowAdapter(){
//             @Override
//             public void windowClosing(WindowEvent e){
//                 System.exit(0);
//             }
//         });

//         PaintThread thread = new PaintThread();
//         thread.start();
//         addKeyListener(new KeyMonitor());
//         addMouseListener(new MouseMonitor());
//         addMouseMotionListener(new MouseMonitor());
        this.add(panel, BorderLayout.CENTER);
    }
    
//     public void update(Graphics g){
//         paint(g);
//     }
    
//     @Override
//     public void paint(Graphics g){
//         super.paint(g);
//         Graphics2D g2 = (Graphics2D)g;
//         g.setColor(Color.WHITE);
//         g.fillRect(0,0,800,500);
//         obstacle.drawSelf(g2);
//         slingShot.drawSelf(g2);
//         plane.drawSelf(g2);
        
//         //矩形相交判斷是否撞上
//         boolean crash = plane.getRect().intersects(obstacle.getRect());
//         if(crash){
//             plane.live = false;
//             obstacle.live = false;
//         }
//     }

//     class PaintThread extends Thread{
//         @Override
//         public void run(){
//             while(true){
//                 repaint();
//                 try{
//                     Thread.sleep(40);
//                 }catch(InterruptedException e){
//                     e.printStackTrace();
//                 }
//             }
//         }
//     }

//     class MouseMonitor extends MouseAdapter{
//         @Override
//         public void mousePressed(MouseEvent e){
//             slingShot.firstpoint(e);
//         }
        
//         @Override
//         public void mouseReleased(MouseEvent e){
//             slingShot.lastpoint(e);
// //             plane.mouseRelease(e);
//         }

//         @Override
//         public void mouseDragged(MouseEvent e){
//             slingShot.middlepoint(e);
// //             plane.mouseDrag(e);
//         }
//     }

//     class KeyMonitor extends KeyAdapter{
//         @Override
//         public void keyReleased(KeyEvent e){
//             //plane.keyRelease(e);
//         }
//     }
}
