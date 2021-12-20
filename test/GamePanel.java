import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class GamePanel extends JPanel{
	boolean win = false;
	boolean loss = false;
	boolean finish = false;
	boolean allStop = false;
	int planeNum;
	int level;
    private GameThread t;
    public ArrayList<Paperplane> planes;
    public ArrayList<Obstacle> obstacles;
    public Slingshot slingShot;
    private Target target = new Target(650, 400, "images/pt.png");
    private PaintThread thread = new PaintThread();
	private Image planeNumImg = new ImageIcon("images/pp01.png").getImage();
	private Image backgroundImg = new ImageIcon("images/pbackground.png").getImage(); 
	private Image endImg = new ImageIcon("images/pend.png").getImage();
   	private JButton one;
   	private JButton two;

    public GamePanel(LevelFrame lf,int level){
	    setLayout(new BorderLayout());

	    JPanel north = new JPanel();
	    north.setPreferredSize(new Dimension(800,400));
	    north.setOpaque(false);
	    add(north, BorderLayout.NORTH);
	this.level = level;
	planes = setPlanes();
	slingShot = new Slingshot(50, 400, "images/ps.png",planes);
	obstacles = setObstacles();
	planeNum = planes.size();
        setBackground(Color.white);
        addMouseListener(new MouseMonitor());
        addMouseMotionListener(new MouseMonitor());
        addMouseWheelListener(new MouseMonitor());
	    one = new JButton("retry");
	    one.setPreferredSize(new Dimension(100,50));
	    one.setLocation(100,100);
	    one.setVisible(false);
	    one.setBackground(Color.WHITE);
	    one.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    LevelFrame l = new LevelFrame();
		    GamePanel panel = new GamePanel(l,level);
		    l.add(panel);	
		    //panel.setVisible(true);
		    lf.setVisible(false);	    
		}
	    });

	    two = new JButton("next");
	    two.setPreferredSize(new Dimension(100,50));
	    two.setLocation(200,100);
	    two.setVisible(false);		
	    //two.setContentAreaFilled(false);
	    two.setBackground(Color.WHITE);
	    two.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    LevelFrame l = new LevelFrame();
		    lf.setVisible(false);	
		}
	    });
	    JPanel center = new JPanel();
	    center.setPreferredSize(new Dimension(800,100));
	    center.setOpaque(false);	
	    center.add(one);
	    center.add(two);
	    add(center, BorderLayout.CENTER);

        thread.start();
	t = new GameThread(this);
	t.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
	if(!finish){
        super.paintComponent(g);
	g.drawImage(backgroundImg,0,0,null);
	g.drawImage(planeNumImg,20,20,null);
	Font planeNumFont = new Font("Times New Roman",Font.BOLD,20);
	g.setFont(planeNumFont);
	g.setColor(Color.BLACK);
	g.drawString(" X "+String.valueOf(planeNum),100,48);

        Graphics2D g2 = (Graphics2D)g;

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
					planes.get(i).fall = true;
            				obstacles.get(j).live = false;
					obstacles.get(j).setX(obstacles.get(j).getX() + 5);
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
		}
		if(loss){	
			finish = true;			
		}
			
	}

        for(int i = 0;i < obstacles.size();i++){
        	for(int j = i + 1;j < obstacles.size();j++){
			boolean pile = obstacles.get(i).getRect().intersects(obstacles.get(j).getRect());
			if(pile){
				obstacles.get(i).fall = false;
				obstacles.get(j).fall = false;
			}else{
				if(!obstacles.get(i).stop){
				obstacles.get(i).fall = true;
				}
				if(!obstacles.get(j).stop){
				obstacles.get(j).fall = true;
				}
				if(!obstacles.get(i).live){
				obstacles.get(i).fall = true;
				}
				if(!obstacles.get(j).live){
				obstacles.get(j).fall = true;
				}	
       			}
		}
	}
	}else{
		g.drawImage(backgroundImg,0,0,null);
		g.drawImage(endImg,200,70,null);
		if(win){	
			Font f = new Font("Times New Roman",Font.BOLD,50);
			g.setFont(f);
			g.setColor(Color.RED);
			g.drawString("You Win!",280,250);
		}
		if(loss){	
			Font f = new Font("Times New Roman",Font.BOLD,50);
			g.setFont(f);
			g.setColor(Color.RED);
			g.drawString("Try Again!",260,250);
		}
		one.setVisible(true);
		two.setVisible(true);
	}
    }

    public ArrayList<Paperplane> setPlanes(){
	ArrayList<Paperplane> p = new ArrayList<>();
	if(level == 1){
            for(int i = 1;i <= 3;i++){
                p.add(new Paperplane(55, 400, "images/pp0" + String.valueOf(i) + ".png"));
	    }
	}
	if(level == 2){
            for(int i = 1;i <= 2;i++){
                p.add(new Paperplane(55, 400, "images/pp0" + String.valueOf(i) + ".png"));
	    }
	}
	if(level == 3){
            for(int i = 1;i <= 1;i++){
                p.add(new Paperplane(55, 400, "images/pp0" + String.valueOf(i) + ".png"));
	    }
	}
	return p;
    }
    
    public ArrayList<Obstacle> setObstacles(){
	ArrayList<Obstacle> o = new ArrayList<>();
	if(level == 1){	
            for(int i = 0;i < 2;i++){
                o.add(new Obstacle(500, 300 - i * 250, "images/po.png"));
	    }
	}
	if(level == 2){	
	    o.add(new Obstacle(500, 300, "images/po.png"));
	     for(int i = 0;i < 2;i++){
                o.add(new Obstacle(230, 300 - i * 250, "images/po.png"));
	    }

	}
	if(level == 3){	
	    for(int i = 0;i < 2;i++){
                o.add(new Obstacle(500, 300 - i * 250, "images/po.png"));
	    }
	    for(int i = 0;i < 2;i++){
                o.add(new Obstacle(230, 300 - i * 250, "images/po.png"));
	    }
	}
	return o;
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
            t.Released(e);
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
}
