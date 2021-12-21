import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.FlowLayout;
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
	boolean ending = false;
	int planeNum;
	int level;
	int time = 0;
	int back = 0;
	int sadtime = 1;
    private GameThread t;
    public ArrayList<Paperplane> planes;
    public ArrayList<Obstacle> obstacles;
    public Slingshot slingShot;
    private Target target;
    private PaintThread thread = new PaintThread();
	private Image planeNumImg = new ImageIcon("images/pp01.png").getImage();
	private Image backgroundImg = new ImageIcon("images/pbackground.png").getImage(); 
	private Image endImg = new ImageIcon("images/pend.png").getImage();
   	private JButton retry;
   	private JButton next;
   	private JButton BackToStartFrame;

    public GamePanel(LevelFrame lf,int level){
	    setLayout(new BorderLayout());

	    JPanel north = new JPanel();
	    north.setPreferredSize(new Dimension(800,360));
	    north.setOpaque(false);
	    add(north, BorderLayout.NORTH);

	this.level = level;
	planes = setPlanes();
	slingShot = new Slingshot(50, 380, "images/ps.png",planes);
	obstacles = setObstacles();
	if(level == 4){
		target = new Target(650, 380, "images/girl/Idle.png");
	}else{
		target = new Target(650, 400, "images/pt.png");
	}
	planeNum = planes.size();
        setBackground(Color.white);
        addMouseListener(new MouseMonitor());
        addMouseMotionListener(new MouseMonitor());
        addMouseWheelListener(new MouseMonitor());

	    next = new JButton();
	    next.setPreferredSize(new Dimension(150,100));
	    next.setVisible(false);
	    next.setIcon(new ImageIcon("images/bc.png"));
	    next.setContentAreaFilled(false);	
	    next.setBorderPainted(false);
	    next.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    if((level == 3) && win){
			LevelFrame l = new LevelFrame(1);
		    }else{		
		    	LevelFrame l = new LevelFrame(lf.ending);
		    }		
		    lf.setVisible(false);	
		}
	    });

	    BackToStartFrame = new JButton();
	    BackToStartFrame.setPreferredSize(new Dimension(150,100));
	    BackToStartFrame.setVisible(false);
	    BackToStartFrame.setIcon(new ImageIcon("images/btsf.png"));
	    BackToStartFrame.setBackground(Color.BLACK);	
	    BackToStartFrame.setBorderPainted(false);
	    BackToStartFrame.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){			
		    GameFrame g = new GameFrame();
		    g.setVisible(true);		
		    lf.setVisible(false);	
		}
	    });

	    retry = new JButton();
	    retry.setPreferredSize(new Dimension(150,100));
	    retry.setVisible(false);
	    retry.setIcon(new ImageIcon("images/br.png"));		
	    retry.setContentAreaFilled(false);
	    retry.setBorderPainted(false);
	    retry.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    LevelFrame l = new LevelFrame(lf.ending);	
		    GamePanel panel = new GamePanel(l,level);
		    l.add(panel);	
		    lf.setVisible(false);	    
		}
	    });

	    JPanel center = new JPanel();
	    center.setPreferredSize(new Dimension(800,100));
	    center.setOpaque(false);	
	    center.add(next);
	    center.add(new JLabel("         "));
	    center.add(BackToStartFrame);	
	    center.add(new JLabel("         "));
	    center.add(retry);
	    add(center, BorderLayout.CENTER);

        thread.start();
	t = new GameThread(this);
	t.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
	Graphics2D g2 = (Graphics2D)g;
	if(!finish && !ending){
        super.paintComponent(g);
        g.drawImage(backgroundImg,0,0,null);
	g.drawImage(planeNumImg,20,20,null);
	Font planeNumFont = new Font("Times New Roman",Font.BOLD,20);
	g.setFont(planeNumFont);
	g.setColor(Color.BLACK);
	g.drawString(" X "+String.valueOf(planeNum),100,48);

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
	
	if(level == 4){
		boolean love = planes.get(0).getRect().intersects(target.getRect());
		if(love){
			planes.get(0).go = false;
			ending = true;
		}		
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
				obstacles.get(i).stop.add(j);
				obstacles.get(j).stop.add(i);
			}else{
        			for(int index1 = 0;index1 < obstacles.get(i).stop.size();index1++){
        				for(int index2 = 0;index2 < obstacles.get(j).stop.size();index2++){
						if(obstacles.get(i).stop.get(index1) == j && obstacles.get(j).stop.get(index2) == i){
							obstacles.get(i).fall = true;
							obstacles.get(j).fall = true;
						}
					}
				}
			}

		}	
	}
	}
	else if(ending){
		if(time < 200){
			g.drawImage(backgroundImg,0,0,null);
			slingShot.drawSelf(g2);
		}else{
			g.setColor(Color.BLACK);			
			if(sadtime <= 110){
				g.fillRect(0,0,800,5 * sadtime);
			}else{
				g.drawImage(new ImageIcon("images/theEnd.jpg").getImage(),0,0,null);
				if(sadtime >= 150){
					BackToStartFrame.setVisible(true);
				}
			}
			sadtime++;
		}
	
		if((650 - time * 10) >= 200){		
			g.drawImage(new ImageIcon("images/girl/girl (" + String.valueOf((time + 1)%22) + ").png").getImage(),650 - time *10,380,null);
			time++;
			if(time%22 == 0){
				time++;
			}
		}else{
			if(time < 130){
				if(time > 60 && time < 80){				
					g.drawImage(new ImageIcon("images/girl/heart.png").getImage(),120,290,null);
				}
				if(time > 80 && time < 110){
					g.drawImage(new ImageIcon("images/girl/talk01.png").getImage(),280,150,null);
				}
				if(time > 90){
					g.drawImage(new ImageIcon("images/girl/heartbreak.png").getImage(),120,290,null);
				}
				g.drawImage(new ImageIcon("images/girl/girl (1).png").getImage(),200,380,null);
						
			}
			if(time >= 130){
				g.drawImage(new ImageIcon("images/girl/girl (" + String.valueOf((back + 1)%22) + ").png").getImage(),300 + back *10,380,-110,120,null);
				back++;
				if(back%22 == 0){
					back++;
				}
			}							
			time++;
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
		retry.setVisible(true);
		next.setVisible(true);
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
            for(int i = 1;i <= 2;i++){
                p.add(new Paperplane(55, 400, "images/pp0" + String.valueOf(i) + ".png"));
	    }
	}
	if(level == 4){
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
