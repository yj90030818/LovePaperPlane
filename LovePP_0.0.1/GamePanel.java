import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel{
    private Prepare Prepare;
    private ArrayList<PaperPlane> Planes;
    private ArrayList<Obstacle> Obstacles;
    private Slingshot Slingshot;
    private PaintThread Thread = new PaintThread();

    public GamePanel(Prepare prepare, ArrayList<PaperPlane> planes, ArrayList<Obstacle> obstacles, Slingshot slingshot){
        this.Prepare = prepare;
        this.Planes = planes;
        this.Obstacles = obstacles;
        this.Slingshot = slingshot;
        
        this.addMouseListener(new MouseAdapter() {
                @Override
            public void mousePressed(MouseEvent e){
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
        public void mouseDragged(MouseEvent e){
            
        }
        });
        
        setBackground(Color.WHITE);
        Thread.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        Planes.forEach((PaperPlane pp) -> pp.drawSelf(g2));
        Obstacles.forEach((Obstacle ob) -> ob.drawSelf(g2));
        Slingshot.drawSelf(g2);
    }

    class PaintThread extends Thread{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            while(true){
                GamePanel.this.repaint();
            }
        }
    }

    
}
