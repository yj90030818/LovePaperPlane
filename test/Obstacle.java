import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.lang.Math;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class Obstacle extends GameObject{
    boolean live = true;
    double original_x = 0.0,original_y = 0.0,adjustment = 0.0,angle = 0.0;

//     public Obstacle(Image img,double x,double y){
//         this.img = img;
//         this.x = x;
//         this.y = y;
//         this.original_x = x;
//         this.original_y = y;
//         this.width = img.getWidth(null);
//         this.height = img.getHeight(null);
//     }
    
    public Obstacle(double x, double y, int type, String path){
        super(x, y, type, path);
        this.original_x = x;
        this.original_y = y;
    }

    //繪製紙飛機
    public void drawSelf(Graphics2D g2){
	if(!live){
		if(angle < Math.PI / 2.0){
		angle = angle + Math.PI / 180.0;
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

    //計算物理碰撞
    /*public double collision (int v0,double angle){
        double radians = Math.toRadians(angle);
        return 2 * Math.pow(v0,2) * Math.sin(radians) * Math.cos(radians) / 9.8;
    }*/
}
