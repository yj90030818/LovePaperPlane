import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.lang.Math;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class Paperplane extends GameObject{
    boolean live = true , go = false, show  =  false ,dragFlag = false; 
    double original_x = 0.0,original_y = 0.0,adjustment = 4.0, direction = 1.0,x0 = 0.0,y0 = 0.0;
    
    public Paperplane(double x, double y, int type, String ImgResource){
        super(x, y, type, ImgResource);
        this.original_x = x;
        this.original_y = y;
    }

    //繪製紙飛機
    public void drawSelf(Graphics2D g2){
        if(show){		
           if(live){
                    if(go){
                	if(y >= 550 || x >= 850){
                    	    live = false;
                	}else{
                    	  
                    	    g2.rotate(Angle(x - x0,y - y0),x,y);
                    	    x0 = getX();
                    	    y0 = getY();
                	}
                    }
                    if(dragFlag){
                	g2.rotate(direction * Angle(Math.abs(original_x - x), (original_y - y)), x, y);
                    }
		}
              	g2.drawImage(img,(int)x,(int)y,width * (int)direction,height,null);
        }
    }

    //計算軌跡方程式總距離
    public double x_removing (double v0,double angle,double t){
        double radians = Math.toRadians(angle);
        return v0 * Math.cos(radians) * t;
    }

    //計算軌跡方程式總時間 useless
//     public double time(double v0,double angle){
//         double radians = Math.toRadians(angle);
//         return 2 * v0 * Math.sin(radians) / 9.8;
//     }

    //計算軌跡方程式最高點 useless
//     public double height(double v0,double angle){
//         double radians = Math.toRadians(angle);
//         return Math.pow(v0,2) * Math.pow(Math.sin(radians),2) / (2.0 * 9.8);
//     }

    //軌跡方程式
    public double locus(double x,double v0,double angle){
        double radians = Math.toRadians(angle);
        return (x * Math.tan(radians)) - (9.8 * Math.pow(x,2) / (2.0 * Math.pow(v0,2) * Math.pow(Math.cos(radians),2)));
    }

    //滑翔軌跡方程式
    public double fun(double y,double h){
        return Math.sqrt(-0.05 * Math.pow((y - h),3) + Math.pow((y - h),2));
    }

    //計算飛行過程角度
    public double Angle(double x,double y){
        return Math.atan(y / x);
    }

    //滑鼠放開事件
    public void mouseRelease(MouseEvent e){
        if(e.isMetaDown()){
            go = true; 
	    x0=x;
	    y0=y;
 	    original_x=x;
	    original_y=y;
        }
        dragFlag = false;
        
    }
    
    public void setDragging(boolean drag){
        if(!(go) && live)
            dragFlag = drag;
    }
    
    public boolean isDragging(){
            return dragFlag;
    }
    
    
    public double getoriginal_x(){
        return this.original_x;
    }
    
    public double getoriginal_y(){
        return this.original_y;
    }
   
    public void setdirection(double d){
        this.direction = d;
    }

    public double getdirection(){
        return direction;
    }  
}



