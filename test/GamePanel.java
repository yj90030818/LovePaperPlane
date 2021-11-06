
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel{
    private ArrayList<Paperplane> planes = setPlanes();
//    private ArrayList<Obstacle> obstacles = new ArrayList<>();

//    private Paperplane plane = new Paperplane(55, 400, 1, "images/pp.png");//ArrayList<Paperplane> planes;
    private Obstacle obstacle = new Obstacle(650, 300, 2,"images/po.png"); //ArrayList<Obstacle> obstacles;
//     private ArrayList<Target> targets;
    private Slingshot slingShot = new Slingshot(50, 400, 3,"images/ps.png",planes);
    private PaintThread thread = new PaintThread();
    
    
    
//     public GamePanel(ArrayList<Paperplane> planes, ArrayList<Obstacle> obstacles, Slingshot slingShot){}
    public GamePanel(){
	setPlanes();
        setBackground(Color.white);
        //addKeyListener(new KeyMonitor());
        addMouseListener(new MouseMonitor());
        addMouseMotionListener(new MouseMonitor());
        thread.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g.drawRect(0,0,800,500);
        g.drawString(String.valueOf(planes.size()),100,100);
        obstacle.drawSelf(g2);
        slingShot.drawSelf(g2);
        for(int i = 0;i < planes.size();i++){
        if(planes.get(i).shoot){	
        planes.get(i).drawSelf(g2);
        
        boolean crash = planes.get(i).getRect().intersects(obstacle.getRect());
        if(crash){
            planes.get(i).live = false;
            obstacle.live = false;
        }
        }
        }
    }

    public ArrayList<Paperplane> setPlanes(){
	ArrayList<Paperplane> p = new ArrayList<>();
        for(int i = 1;i <= 3;i++){
            p.add(new Paperplane(55, 400, 1, "images/pp0" + String.valueOf(i) + ".png"));
	}
	return p;
    }
    
    class PaintThread extends Thread{
        @Override
        public void run(){
            while(true){
                repaint();
                try{
                    Thread.sleep(50);
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
        }

        @Override
        public void mouseDragged(MouseEvent e){
            slingShot.middlepoint(e);
        }
    }

    /*class KeyMonitor extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            plane.keyRelease(e);
        }
    }*/
}
