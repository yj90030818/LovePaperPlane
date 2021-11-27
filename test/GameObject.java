import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

//遊戲物件的父類別，讓子類別可以調用父類別的方法
public class GameObject{
    Image img;
    int width, height, type;
    double x, y, v0, angle;
    String ImgResource;

    public GameObject(double x, double y, int type, String ImgResource){
        this.x = x;
        this.y = y;
        this.type = type;
        this.ImgResource = ImgResource;
        this.img = GameUtil.getImage(type, ImgResource);
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }
    
    public Rectangle getRect(){
        return new Rectangle((int)x, (int)y, width, height);
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setV0(double v0){
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
    
    public double getV0(){
        return v0;
    }
    
    public double getAngle(){
        return angle;
    }
    
//     public String getImgResource(){
//         return this.ImgResource;
//     }
    
    public int getType(){
        return this.type;
    } 
    
    public int getWidth(){
        return this.width;
    } 
    
    public int getHeight(){
        return this.height;
    }
}
