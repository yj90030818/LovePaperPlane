import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.lang.Math;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Slingshot extends GameObject{
    Paperplane plane = new Paperplane();
    boolean shoot = false,collison = false;
    int i = 0;
    double adjustment = 4.0;
    double pressed_x = 0.0, pressed_y = 0.0, released_x = 0.0,released_y = 0.0;   //滑鼠按住和放開的座標參數

    
    public Slingshot(double x, double y, int type, String ImgResource, ArrayList<Paperplane> planes){
        super( x, y, type, ImgResource, planes);
        this.plane = planes.get(i);
        plane.show = true;
    }

    //繪製彈弓
    public void drawSelf(Graphics g){
        g.drawImage(img,(int)x,(int)y, null);        
    }

    //藉由三角形斜邊長計算初速度
    public double Force(){
        double v = 0.0;
        v = (Math.sqrt(Math.pow((pressed_x - released_x) / adjustment , 2) + Math.pow((pressed_y - released_y) / adjustment , 2)));
        if(v >= 50.0){
            v = 50.0;
        }
        return v;
    }

    //藉由反正切值計算仰角
    public double Angle(){
        return Math.toDegrees(Math.atan((pressed_y - released_y) / (released_x - pressed_x))) * plane.getdirection();
    }
    
    //滑鼠按下事件
    public void firstpoint(MouseEvent e){
            shoot = true;
            pressed_x = e.getX();
            pressed_y = e.getY();
    }

    //滑鼠拖移事件 useless
    public void middlepoint(MouseEvent e){
        if(!(e.isMetaDown()))
           return; 
        plane.setDragging(true);
        if(!(plane.isDragging()))
                    return;
        if(Math.abs(e.getX() - pressed_x) > 100 || Math.abs(e.getY() - pressed_y) > 100){
            
            
            released_x = e.getX();
            released_y = e.getY();
            double limit = 100.0;
            double drag_x =(released_x - pressed_x)/4, drag_y=(released_y - pressed_y)/4;

            plane.setX(plane.getoriginal_x() + (Math.abs(drag_x) > limit ? (drag_x > 0 ? limit : -limit) : drag_x));
            plane.setY(plane.getoriginal_y() + (Math.abs(drag_y) > limit ? (drag_y > 0 ? limit : -limit) : drag_y));
            plane.setdirection(pressed_x > released_x ? 1.0 : -1.0);
        }else{
            plane.setDragging(false);
            plane.setX(plane.getoriginal_x());
            plane.setY(plane.getoriginal_y());
        }
    }
    
    //滑鼠放開事件
    public void lastpoint(MouseEvent e){
        if(plane.isDragging()){
            shoot = false;
            plane.setV0(Force());
            plane.setAngle(Angle());
            plane.mouseRelease(e);	
        }
    }

    //準備飛機
    public void setPlane(MouseEvent e){
	if(!plane.live){
            if(i < planes.size()-1){
            	i = i + 1;          	
            	this.plane = planes.get(i);
            	plane.show = true;
            }
        }
    }

    //替換飛機
    public void changePlane(MouseWheelEvent e){
	//滑鼠滾輪向下 123 -> 231
	if(e.getWheelRotation() == 1){
            if(!plane.isDragging() && !plane.go){
            plane.show = false;
            planes.add(plane);
            planes.remove(i);
	            	
            Paperplane nextPlane = planes.get(i);
            this.plane = nextPlane;
            nextPlane.show = true;
            }
        }
	//滑鼠滾輪向上 123 -> 312
	if(e.getWheelRotation() == -1){
            if(!plane.isDragging() && !plane.go){
            plane.show = false;
            planes.add(i,planes.get(planes.size() - 1));
            planes.remove(planes.size() - 1);
	            	
            Paperplane nextPlane = planes.get(i);
            this.plane = nextPlane;
            nextPlane.show = true;
            }
        }
    }
    

}

