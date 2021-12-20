import java.awt.Graphics;
import java.awt.Graphics2D;

public class Target extends GameObject{
    
    public Target(double x, double y, String path){
        super(x, y, path);
    }

    //繪製目標
    public void drawSelf(Graphics2D g2){
	    g2.drawImage(img,(int)x,(int)y, null);
    }
}
