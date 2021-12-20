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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        this.setResizable(false);
        this.setBackground(Color.PINK);
      
        StartPanel startpanel = new StartPanel(panel);
	this.add(startpanel, BorderLayout.CENTER);
    }
	
     public class StartPanel extends JPanel{

	public StartPanel(GamePanel gamepanel){
	    super();
	    setLayout(new BorderLayout());

	    JPanel north = new JPanel();
	    north.setPreferredSize(new Dimension(800,120));
	    north.setOpaque(false);
	    add(north, BorderLayout.NORTH);
	    
	    JButton start = new JButton("Start");
	    start.setPreferredSize(new Dimension(100,50));
	    start.setBackground(Color.WHITE);
	    start.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    LevelFrame level = new LevelFrame();
		    /*GamePanel panel = new GamePanel();	
		    GameFrame.this.add(panel);
		    panel.setVisible(true);*/
		    GameFrame.this.setVisible(false);
		}
	    });
	    	    
	    JButton exit = new JButton("Exit");
	    exit.setPreferredSize(new Dimension(100,50));
	    exit.setBackground(Color.WHITE);
	    exit.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    System.exit(0);
		}
	    });
 
	    JPanel south = new JPanel();
	    south.setPreferredSize(new Dimension(200,140));
	    south.setOpaque(false);        
	    south.add(start);
	    south.add(new JLabel("            "));
	    south.add(exit);
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
