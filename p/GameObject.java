import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

//遊戲物件的父類別，讓子類別可以調用父類別的方法
public class GameObject{
    Image img;
    int v0,width,height;
    double x,y,angle;
    Paperplane plane;

    public GameObject(){}

    public GameObject(Image img, double x, double y, Paperplane plane){
        super();
        this.img = img;
        this.x = x;
        this.y = y;
        this.plane = plane;
    }

    public GameObject(Image img, double x, double y, int v0, double angle){
        super();
        this.img = img;
        this.x = x;
        this.y = y;
        this.v0 = v0;
        this.angle = angle;
    }

    public void drawSelf(Graphics g){
        g.drawImage(img,(int)x,(int)y, null);
    }

    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,width,height);
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double plane_y){
        this.y = y;
    }

    public void setV0(int v0){
        this.v0 = v0;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }
    
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
