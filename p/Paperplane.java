import java.awt.Graphics;
import java.awt.Image;
import java.lang.Math;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class Paperplane extends GameObject{
    boolean live = true , go = false;
    int i = 0;
    double original_x = 0.0,original_y = 0.0;

    public Paperplane(Image img,double x,double y,int v0,double angle){
        this.img = img;
        this.x = x;
        this.y = y;
        this.original_x = x;
        this.original_y = y;
        this.v0 = v0;
        this.angle = angle;
    }

    //繪製紙飛機
    public void drawSelf(Graphics g){
        if(go){
            if((original_y - locus(i,v0,angle)*5) <= 550 || original_x + i*5 <= 850){
            //if(i <= r(v0,angle)){
                x = original_x + i*5;
                y = original_y - locus(i,v0,angle)*5;
                g.drawImage(img,(int)x,(int)y, null);
                i = i + 2;
            }else{
                go = false;
            }
        }else{
            g.drawImage(img,(int)x,(int)y, null);
        }
    }

    //計算軌跡方程式總距離
    public double r (int v0,double angle){
        double radians = Math.toRadians(angle);
        return 2 * Math.pow(v0,2) * Math.sin(radians) * Math.cos(radians) / 9.8;
    }

    //計算軌跡方程式總時間
    public double time(int v0,double angle){
        double radians = Math.toRadians(angle);
        return 2 * v0 * Math.sin(radians) / 9.8;
    }

    //軌跡方程式
    public double locus(double x,int v0,double angle){
        double radians = Math.toRadians(angle);
        return (x * Math.tan(radians)) - (9.8 * Math.pow(x,2) / (2.0 * Math.pow(v0,2) * Math.pow(Math.cos(radians),2)));
    }

    //滑鼠放開事件
    public void mouseRelease(MouseEvent e){
        if(e.isMetaDown()){
            go = true;
        }
    }

    //按鍵放開事件
    public void keyRelease(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                setX(original_x);
                setY(original_y);
                go = false;
        }
    }
}

