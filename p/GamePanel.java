
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    private Paperplane plane = new Paperplane(55, 400, 1, "images/pp.png");//ArrayList<Paperplane> planes;
    private Obstacle obstacle = new Obstacle(650, 300, 2,"images/po.png"); //ArrayList<Obstacle> obstacles;
//     private ArrayList<Target> targets;
    private Slingshot slingShot = new Slingshot(50, 400, 3,"images/ps.png", plane);
    
    
    
//     public GamePanel(ArrayList<Paperplane> planes, ArrayList<Obstacle> obstacles, Slingshot slingShot){}
    public GamePanel(){
        setBackground(Color.white);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
//         g.setColor(Color.WHITE);
//         g.fillRect(0,0,800,500);
        obstacle.drawSelf(g2);
        slingShot.drawSelf(g2);
        plane.drawSelf(g2);
        
        boolean crash = plane.getRect().intersects(obstacle.getRect());
        if(crash){
            plane.live = false;
            obstacle.live = false;
        }
    }
    
    
}
