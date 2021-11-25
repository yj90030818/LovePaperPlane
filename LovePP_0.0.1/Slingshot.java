
public class Slingshot extends GameObject{
    private PaperPlane Plane = null;
    private double[2] first_p; //plane initial position
    
     

    public Slingshot(double x, double y, int type, String fileName){
        super(type, fileName);     
        this.setX(x);
        this.setY(y);   
        this.first_p[0] = x;
        this.first_p[1] = y;
    }

    public void setPlane(PaperPlane pp){
        this.Plane = pp;
        if(pp != null){
        this.Plane.setX(first_p[0]);
        this.Plane.setY(first_p[1]);
        }
    }

    public PaperPlane getPlane(){
        return this.Plane;
    }
    
   public void DragMove(int x_drag, int y_darg){
       int range = 100;
       x_drag = (Math.abs(x_drag) < range ? x_drag : range);
       y_drag = (Math.abs(y_drag) < range ? y_drag : range);
       this.Plane.setX(this.Plane.getX() + x_drag);
       this.Plame.setY(this.Plane.getY() + y_drag);
        
   }
   
    
}
