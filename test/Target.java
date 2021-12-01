import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.lang.Math;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class Target extends GameObject{
    //boolean live = true,fall = true;
    private double original_x = 0.0,original_y = 0.0,adjustment = 0.0,angle = 0.0;
    
    public Target(double x, double y, int type, String path){
        super(x, y, type, path);
        this.original_x = x;
        this.original_y = y;
    }

    //繪製紙飛機
    public void drawSelf(Graphics2D g2){
	    g2.drawImage(img,(int)x,(int)y, null);
    }
}
