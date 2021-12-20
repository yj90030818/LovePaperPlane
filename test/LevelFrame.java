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

public class LevelFrame extends JFrame{
GamePanel gamepanel;
    public LevelFrame(){
        this.setTitle("Love PaperPlane");
        this.setSize(800 , 540);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
        this.setResizable(false);
      
        LevelPanel levelpanel = new LevelPanel(gamepanel);
	this.add(levelpanel, BorderLayout.CENTER);
    }
	
     public class LevelPanel extends JPanel{

	public LevelPanel(GamePanel gamepanel){
	    super();
	    setLayout(new BorderLayout());

	    JPanel north = new JPanel();
	    north.setPreferredSize(new Dimension(800,120));
	    north.setOpaque(false);
	    add(north, BorderLayout.NORTH);
	    
	    JButton one = new JButton("1 - 1");
	    one.setPreferredSize(new Dimension(100,50));
	    one.setContentAreaFilled(false);
	    one.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    GamePanel panel = new GamePanel(LevelFrame.this,1);	
		    LevelFrame.this.add(panel);
		    panel.setVisible(true);
		    LevelPanel.this.setVisible(false);
		}
	    });

	    JButton two = new JButton("1 - 2");
	    two.setPreferredSize(new Dimension(100,50));
	    two.setContentAreaFilled(false);
	    two.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    GamePanel panel = new GamePanel(LevelFrame.this,2);	
		    LevelFrame.this.add(panel);
		    panel.setVisible(true);
		    LevelPanel.this.setVisible(false);
		}
	    });

	    JButton three = new JButton("1 - 3");
	    three.setPreferredSize(new Dimension(100,50));
	    three.setContentAreaFilled(false);
	    three.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    GamePanel panel = new GamePanel(LevelFrame.this,3);	
		    LevelFrame.this.add(panel);
		    panel.setVisible(true);
		    LevelPanel.this.setVisible(false);
		}
	    });
			    
	    JPanel center = new JPanel();
	    center.setPreferredSize(new Dimension(400,200));
	    center.setOpaque(false);
	    center.add(one);
	    center.add(two);
	    center.add(three);
            add(center, BorderLayout.CENTER);	        
	}

	@Override
	public void paintComponent(Graphics g){
	    g.drawImage(new ImageIcon("images/pbackground.png").getImage(),0,0,800,540,null);	
	}	
     }
}
