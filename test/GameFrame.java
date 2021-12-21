import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameFrame extends JFrame{
GamePanel panel;
StartPanel  startpanel;
   
    public static void main(String[] args){
        GameFrame f = new GameFrame();
        f.setVisible(true);
    }

    public GameFrame(){
        this.setTitle("Love PaperPlane");
        this.setSize(800 , 540);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.PINK);
      
        startpanel = new StartPanel(panel);
	this.add(startpanel, BorderLayout.CENTER);
    }
	
     public class StartPanel extends JPanel{

	public StartPanel(GamePanel gamepanel){
	    super();
	    setLayout(new BorderLayout());

	    JPanel north = new JPanel();
	    north.setPreferredSize(new Dimension(800,80));
	    north.setOpaque(false);
	    add(north, BorderLayout.NORTH);
	    
	    JButton start = new JButton();
	    start.setPreferredSize(new Dimension(150,50));
	    start.setIcon(new ImageIcon("images/bp.png"));	
	    start.setBackground(Color.PINK);
	    start.setBorderPainted(false);
	    start.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    LevelFrame level = new LevelFrame(0);
		    /*GamePanel panel = new GamePanel();	
		    GameFrame.this.add(panel);
		    panel.setVisible(true);*/
		    GameFrame.this.setVisible(false);
		}
	    });
	    	    
	    JButton exit = new JButton();
	    exit.setPreferredSize(new Dimension(150,50));
	    exit.setIcon(new ImageIcon("images/be.png"));	
	    exit.setBackground(Color.PINK);
	    exit.setBorderPainted(false);
	    exit.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    System.exit(0);
		}
	    });

	    JPanel south = new JPanel();
	    south.setPreferredSize(new Dimension(200,210));
	    south.setLayout(new GridLayout(3,1));
	    JPanel southup = new JPanel();
	    southup.setPreferredSize(new Dimension(200,70));
	    JPanel southdown = new JPanel();
	    southdown.setPreferredSize(new Dimension(200,70));

	    south.setOpaque(false);
	    southup.setOpaque(false);
	    southdown.setOpaque(false);
	    southup.add(start);
	    southdown.add(exit);        
	    south.add(southup);
	    south.add(southdown);
	    south.add(new JLabel("      "));
	    add(south, BorderLayout.SOUTH);
			    
	    JPanel center = new JPanel();
	    center.setPreferredSize(new Dimension(400,200));
	    center.setOpaque(false);
	    JLabel logo = new JLabel("");
            Image logoicon = (new ImageIcon("images/pb.png")).getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT);
	    logo.setIcon(new ImageIcon(logoicon));
	    center.add(logo);
            add(center, BorderLayout.CENTER);
	        
	}

	@Override
	public void paintComponent(Graphics g){
	    //g.drawImage(new ImageIcon("images/pbackground.png").getImage(),0,0,800,540,null);	
	}	
     }
}
