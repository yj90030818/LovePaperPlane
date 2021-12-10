
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel{
	boolean win = false;
	boolean loss = false;
	boolean finish = false;
	boolean allStop = false;
    private GameThread t;
    private ArrayList<Paperplane> planes = setPlanes();
	int planeNum = planes.size();
    private ArrayList<Obstacle> obstacles = setObstacles();

//    private Paperplane plane = new Paperplane(55, 400, 1, "images/pp.png");//ArrayList<Paperplane> planes;
//    private Obstacle obstacle = new Obstacle(650, 300, 2,"images/po.png"); //ArrayList<Obstacle> obstacles;
//     private ArrayList<Target> targets;
    public Slingshot slingShot = new Slingshot(50, 400, 3,"images/ps.png",planes);
    public Target target = new Target(650, 400, 4,"images/pt.png");
    private PaintThread thread = new PaintThread();
	Image planeNumImg = GameUtil.getImage(4, "images/pp01.png");
	Image backgroundImg = GameUtil.getImage(4, "images/pbackground.png");    
    
    
//     public GamePanel(ArrayList<Paperplane> planes, ArrayList<Obstacle> obstacles, Slingshot slingShot){}
    public GamePanel(){
	setPlanes();
        setBackground(Color.white);
        //addKeyListener(new KeyMonitor());
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
	g.drawImage(backgroundImg,0,0,null);
	g.drawImage(planeNumImg,20,20,null);
	Font planeNumFont = new Font("Times New Roman",Font.BOLD,20);
	g.setFont(planeNumFont);
	g.setColor(Color.BLACK);
	g.drawString(" X "+String.valueOf(planeNum),100,48);

        Graphics2D g2 = (Graphics2D)g;
        //g.setColor(Color.BLACK);
        //g.drawRect(0,0,800,500);
        //g.drawString(String.valueOf(planes.size()),100,100);

        target.drawSelf(g2);
	for(int i = 0;i < obstacles.size();i++){
		boolean ko = obstacles.get(i).getRect().intersects(target.getRect());
		if(ko){
			win = true;
		}
	}

        slingShot.drawSelf(g2);

        for(int i = 0;i < obstacles.size();i++){
	   	obstacles.get(i).drawSelf(g2);
	}
        for(int i = 0;i < planes.size();i++){
        	if(planes.get(i).show){	

        		planes.get(i).drawSelf(g2);
        		for(int j = 0;j < obstacles.size();j++){
        			boolean crash = planes.get(i).getRect().intersects(obstacles.get(j).getRect());
        			if(crash){
            				//planes.get(i).live = false;
            				obstacles.get(j).live = false;
        			}
        		}
		}
        }
	for(int i = 0;i < obstacles.size();i++){
		if(obstacles.get(i).fall){
			allStop = false;
			break;
		}
		allStop = true;
	}
		if(allStop){
			if(win){
				finish = true;	
				Font f = new Font("Times New Roman",Font.BOLD,50);
				g.setFont(f);
				g.setColor(Color.RED);
				g.drawString("You Win!",280,250);
			}
			if(loss){
				finish = true;	
				Font f = new Font("Times New Roman",Font.BOLD,50);
				g.setFont(f);
				g.setColor(Color.RED);
				g.drawString("Try Next Time!",200,250);
			}
			
		}


        for(int i = 0;i < obstacles.size();i++){
        	for(int j = i + 1;j < obstacles.size();j++){
			boolean fall = obstacles.get(i).getRect().intersects(obstacles.get(j).getRect());
			if(fall){
				obstacles.get(i).fall = false;
				obstacles.get(j).fall = false;
			}else{
				obstacles.get(i).fall = true;
				obstacles.get(j).fall = true;	
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
    
    public ArrayList<Obstacle> setObstacles(){
	ArrayList<Obstacle> o = new ArrayList<>();
        for(int i = 0;i < 2;i++){
            o.add(new Obstacle(500, 300 - i * 850, 1, "images/po.png"));
	}
	return o;
    }

    class PaintThread extends Thread{
        @Override
        public void run(){
            while(!finish){
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
            slingShot.setPlane(e);
            t.Clicked(e);
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
	        t.WheelMoved(e);
        }

    }

    /*class KeyMonitor extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            plane.keyRelease(e);
        }
    }*/
}
