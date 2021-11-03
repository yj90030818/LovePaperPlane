import java.awt.Graphics;
import java.awt.Image;
import java.lang.Math;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class Paperplane extends GameObject{
    boolean live = true , go = false;
    int i = 0;
    double original_x = 0.0,original_y = 0.0,adjustment = 4.0;

    public Paperplane(Image img,double x,double y){
        this.img = img;
        this.x = x;
        this.y = y;
        this.original_x = x;
        this.original_y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    //繪製紙飛機
    public void drawSelf(Graphics g){
        if(live){
            if(go){
                if(y <= 550 || x <= 850){
                //if(i <= removing(getV0(),getAngle())){
                    x = original_x + i * adjustment;
                    y = original_y - locus(i,getV0(),getAngle()) * adjustment;
                    i = i + 2;
                }else{
                    go = false;
                }
            }
        }else{
            y = y + 1;
        }
        
        g.drawImage(img,(int)x,(int)y, null);
    }

    //計算軌跡方程式總距離
    public double removing (double v0,double angle){
        double radians = Math.toRadians(angle);
        return 2.0 * Math.pow(v0,2.0) * Math.sin(radians) * Math.cos(radians) / 9.8;
    }

    //計算軌跡方程式總時間 useless
    public double time(double v0,double angle){
        double radians = Math.toRadians(angle);
        return 2 * v0 * Math.sin(radians) / 9.8;
    }

    //軌跡方程式
    public double locus(double x,double v0,double angle){
        double radians = Math.toRadians(angle);
        return (x * Math.tan(radians)) - (9.8 * Math.pow(x,2.0) / (2.0 * Math.pow(v0,2.0) * Math.pow(Math.cos(radians),2.0)));
    }

    //滑鼠放開事件
    public void mouseRelease(MouseEvent e){
        if(e.isMetaDown()){
            go = true;
        }
    }
}

