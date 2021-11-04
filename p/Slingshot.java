import java.awt.Graphics;
import java.awt.Image;
import java.lang.Math;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class Slingshot extends GameObject{
//     Paperplane plane;
    boolean shoot = false;
    double adjustment = 4.0;
    //double start_x = 0.0, start_y = 0.0; //飛機起飛座標
    double pressed_x = 0.0, pressed_y = 0.0, released_x = 0.0,released_y = 0.0;   //滑鼠按住和放開的座標參數

//     public Slingshot(Image img,double x,double y,Paperplane plane){
//         this.img = img;
//         this.x = x;
//         this.y = y;
//         setPlane(plane);
//     }
    
    public Slingshot(double x, double y, int type, String path, Paperplane plane){
        super( x, y, type, path, plane);
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
       System.out.println("aaaaaa");
        
        released_x = e.getX();
        released_y = e.getY();
        
        
        
        plane.setX(plane.getoriginal_x() + (released_x - pressed_x));
        plane.setY(plane.getoriginal_y() + (released_y - pressed_y));
        plane.setV0(Force());
        plane.setAngle(Angle());
    }
    
    //滑鼠放開事件
    public void lastpoint(MouseEvent e){
        shoot = false;
        released_x = e.getX();
        released_y = e.getY();
//         plane.setX = start_x;
//         plane.setY = start_y;
        plane.setdirection(pressed_x > released_x ? 1.0 : -1.0);
        plane.setV0(Force());
        plane.setAngle(Angle());
    }
    
    //準備飛機
    public void setPlane(Paperplane plane){
        this.plane = plane;
    }
}

