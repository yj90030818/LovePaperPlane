import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

//遊戲物件的父類別，讓子類別可以調用父類別的方法
public class GameObject{
    Image img;
    int width, height, type, x, y;
    double v0, angle;
    String ImgResource;
    Paperplane plane = null;


    public GameObject(double x, double y, int type, String path){
        this.x = GameUtil.GUI_x(x);
        this.y = GameUtil.GUI_y(y);
        this.type = type;
        this.ImgResource = path;
        this.img = GameUtil.getImage(type, path);
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }
    
    public GameObject(double x, double y, int type, String path, Paperplane plane){
        this(x, y, type, path);
        this.plane = plane;
    }
    
    public void drawSelf(Graphics g){
        g.drawImage(img, x, y, null);
    }

    public void drawSelf(Graphics2D g2){
        g2.drawImage(img,x,y, null);
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, width, height);
    }

    public void setX(double x){
        this.x = GameUtil.GUI_x(x);
    }

    public void setY(double y){
        this.y = GameUtil.GUI_y(y);
    }

    public void setV0(double v0){
        this.v0 = v0;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }
    
    public double getX(){
        return CO_x(x);
    }

    public double getY(){
        return CO_y(y);
    }
    
    public double getV0(){
        return v0;
    }
    
    public double getAngle(){
        return angle;
    }
    
    public void setImg(Image img){
        this.img = img;
    }
    
    public String getImgResource(){
        return this.ImgResource;
    }
    
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
