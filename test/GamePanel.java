import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel{
    private GameThread t;
    private ArrayList<Paperplane> planes = setPlanes();
    private Obstacle obstacle = new Obstacle(650, 300, 2,"images/po.png"); //ArrayList<Obstacle> obstacles;
    public Slingshot slingShot = new Slingshot(50, 400, 3,"images/ps.png",planes);
    private PaintThread thread = new PaintThread();
    
    
        public GamePanel(){
	setPlanes();
        setBackground(Color.white);
        addMouseListener(new MouseMonitor());
        addMouseMotionListener(new MouseMonitor());
        addMouseWheelListener(new MouseMonitor());
        thread.start();
	t = new GameThread(this);
	t.start();
	
	}
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g.drawRect(0,0,800,500);
        g.drawString(String.valueOf(planes.size()),100,100);
        slingShot.drawSelf(g2);
   	obstacle.drawSelf(g2);
        for(int i = 0;i < planes.size();i++){
        if(planes.get(i).show){	
	
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
        public void mouseClicked(MouseEvent e){
            slingShot.setPlane();
            t.Clicked();
        }

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

        @Override
        public void mouseWheelMoved(MouseWheelEvent e){
            slingShot.changePlane(e);
	    t.WheelMoved();
        }

    }

   
	
}
