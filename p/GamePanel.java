
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    private Paperplane plane = new Paperplane(55, 400, 1, "images/pp.png");//ArrayList<Paperplane> planes;
    private Obstacle obstacle = new Obstacle(650, 300, 2,"images/po.png"); //ArrayList<Obstacle> obstacles;
//     private ArrayList<Target> targets;
    private Slingshot slingShot = new Slingshot(50, 400, 3,"images/ps.png", plane);
    private PaintThread thread = new PaintThread();
    
    
    
//     public GamePanel(ArrayList<Paperplane> planes, ArrayList<Obstacle> obstacles, Slingshot slingShot){}
    public GamePanel(){
        setBackground(Color.white);
        addKeyListener(new KeyMonitor());
        addMouseListener(new MouseMonitor());
        addMouseMotionListener(new MouseMonitor());
        thread.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
//         g.setColor(Color.WHITE);
//         g.fillRect(0,0,800,500);
        obstacle.drawSelf(g2);
        slingShot.drawSelf(g2);
        plane.drawSelf(g2);
        
        boolean crash = plane.getRect().intersects(obstacle.getRect());
        if(crash){
            plane.live = false;
            obstacle.live = false;
        }
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
//             plane.mouseRelease(e);
        }

        @Override
        public void mouseDragged(MouseEvent e){
            slingShot.middlepoint(e);
//             plane.mouseDrag(e);
        }
    }

    class KeyMonitor extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            //plane.keyRelease(e);
        }
    }
}
