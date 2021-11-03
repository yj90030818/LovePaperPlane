import java.awt.Graphics;
import java.awt.Image;
import java.lang.Math;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class Slingshot extends GameObject{
    Paperplane plane;
    boolean shoot = false;
    double adjustment = 4.0;
    double start_x = 0.0,start_y = 0.0,end_x = 0.0,end_y = 0.0;   //滑鼠按住和放開的座標參數

    public Slingshot(Image img,double x,double y,Paperplane plane){
        this.img = img;
        this.x = x;
        this.y = y;
        this.plane = plane;
    }

    //繪製彈弓
    public void drawSelf(Graphics g){
        g.drawImage(img,(int)x,(int)y, null);
    }

    //藉由三角形斜邊長計算初速度
    public double Force(){
        double v = 0.0;
        v = (Math.sqrt(Math.pow((start_x - end_x) / adjustment , 2) + Math.pow((start_y - end_y) / adjustment , 2)));
        if(v >= 50.0){
            v = 50.0;
        }
        return v;
    }

    //藉由反正切值計算仰角
    public double Angle(){
        return Math.toDegrees(Math.atan((start_y - end_y) / (end_x - start_x)));
    }
    
    //滑鼠按住事件
    public void firstpoint(MouseEvent e){
        shoot = true;
        start_x = e.getX();
        start_y = e.getY();
    }

    //滑鼠拖移事件 useless
    /*public void middlepoint(MouseEvent e){
        plane.setX(e.getX());
        plane.setY(e.getY());
    }*/
    
    //滑鼠放開事件
    public void lastpoint(MouseEvent e){
        shoot = false;
        end_x = e.getX();
        end_y = e.getY();
        plane.setV0(Force());
        plane.setAngle(Angle());
    }
}

