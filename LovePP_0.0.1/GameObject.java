import java.awt.Image;
import java.awt.Graphics2D;

//Definition of all Game Object(Ex. PaperPlane)
public class GameObject{
    Image Img;
    int Width, Height, Type, X, Y;//(X,Y) if upper-left point of object
    double V0, Angle, V_x, V_y;

    //Constructor
    public GameObject(int type, String fileName){
        this.Type = type;
        this.Img = GameUtil.gen_Image(fileName);
        this.Width = Img.getWidth(null);
        this.Height = Img.getHeight(null);
    }

    public void drawSelf(Graphics2D g2){
        g2.drawImage(Img, X, Y, Width, Height,null);
    }
    
    //X mutator and accessor--
    public void setX(double x){
        System.out.println("now"+this.getClass().toString().substring(5)+".x="+x);
        this.X = GameUtil.Gui_X(x);
    }
    
    public int get_Gui_X(){
        return this.X;
    }

    public double getX(){
        return GameUtil.Coordinary_X(this.X);
    }
    //--


    //Y mutator and accessor--
    public void setY(double y){
        System.out.println("new"+this.getClass().toString().substring(5)+".y="+y);
        this.Y = GameUtil.Gui_Y(y);
    }

    public int get_Gui_Y(){
        return this.Y;
    }

    public double getY(){
        return GameUtil.Coordinary_Y(this.Y);
    }
    //--

    //V0 mutator and accessor--
    public void setV0(double v0){
        this.V0 = v0;
    }

    public double getV0(){
        return V0;
    }
    //--

    //Angle mutator and accessor--
    public void setAngle(double angle){
        this.Angle = angle;
    }

    public double getAngle(){
        return Angle;
    }
    //--

    //Img mutator and accessor--
    public void setImg(String fileName){
        this.Img = GameUtil.gen_Image(fileName);
    }

    public Image getImg(){
        return this.Img;
    }
    //--
    
    //Type mutator and accessor--
    public void setType(int type){
        this.Type = type;
    }

    public int getType(){
        return this.Type;
    }
    //--

    //Width mutator and accessor--
    public void setWidth(int width){
        this.Width = width;
    }

    public int getWidth(){
        return this.Width;
    } 
    //--

    //Height mutator and accessor--
    public void setHeight(int height){
        this.Height = height;
    }

    public int getHeight(){
        return this.Height;
    }
    //--

    public void setV_x(double v_x) {
        this.V_x = v_x;
    }

    public double getV_x() {
        return this.V_x;
    }

    public void setV_y(double v_y) {
        this.V_y = v_y;
    }

    public double getV_y() {
        return this.V_y;
    }
}
