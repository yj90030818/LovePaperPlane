import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelFrame extends JFrame{
GamePanel gamepanel;
LevelPanel levelpanel;
int ending;
    public LevelFrame(int ending){
	this.ending = ending;
        this.setTitle("Love Story Papper Plane");
        this.setSize(800 , 540);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
        this.setResizable(false);
      
        levelpanel = new LevelPanel(gamepanel);
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

	    Font f = new Font("Times New Roman",Font.BOLD,40);
	    
	    JButton one = new JButton("1 - 1");
	    one.setPreferredSize(new Dimension(150,200));
	    one.setFont(f);
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
	    two.setPreferredSize(new Dimension(150,200));
	    two.setFont(f);
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
	    three.setPreferredSize(new Dimension(150,200));
	    three.setFont(f);
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

	    JButton special = new JButton("1 - ?");
	    special.setPreferredSize(new Dimension(150,200));
	    special.setFont(f);
	    special.setContentAreaFilled(false);
	    special.addActionListener(new ActionListener(){
	    	@Override
		public void actionPerformed(ActionEvent e){
		    GamePanel panel = new GamePanel(LevelFrame.this,4);	
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
	    if(ending == 1){
	    	center.add(special);
	    }
            add(center, BorderLayout.CENTER);
	    
	    /*JPanel south = new JPanel();
	    south.setPreferredSize(new Dimension(400,200));
	    south.setOpaque(false);
	    south.add(special);
            add(south, BorderLayout.SOUTH);*/
    
	}

	@Override
	public void paintComponent(Graphics g){
	    g.drawImage(new ImageIcon("images/pbackground.png").getImage(),0,0,800,540,null);	
	}	
     }
}
