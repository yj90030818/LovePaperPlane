import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class Obstacle extends GameObject{
    boolean live = true,fall = true;
    ArrayList<Integer> stop = new ArrayList<Integer>();
    double original_x = 0.0,original_y = 0.0,adjustment = 0.0,angle = 0.0;
    
    public Obstacle(double x, double y, String path){
        super(x, y, path);
        this.original_x = x;
        this.original_y = y;
    }

    //繪製障礙物
    public void drawSelf(Graphics2D g2){
	if(fall){
		if(y < 300){
		setY(y+10);
		}else{
			fall = false;
		}
	}
	if(!live){
		if(angle < Math.PI / 2.0){
		angle = angle + Math.PI / 90.0;
		}
		g2.translate(width,height);
		g2.rotate(angle,x,y);
		g2.translate(-width,-height);	
		g2.drawImage(img,(int)x,(int)y, null);
		g2.translate(width,height);
		g2.rotate(-angle,x,y);
		g2.translate(-width,-height);	
	}else{
        	g2.drawImage(img,(int)x,(int)y, null);
    	}
    }

    public Rectangle getRect(){
	if(!live && (angle >= Math.PI / 2.0)){
        return new Rectangle((int)x + width, (int)y + height / 2, height, width);
	}	
        return new Rectangle((int)x, (int)y, width, height);
    }
}
