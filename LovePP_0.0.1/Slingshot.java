
public class Slingshot extends GameObject{
    private PaperPlane Plane = null;//, Plane2 = null;
    private double P1_x, P1_y; //plane initial position
    private double P2_x, P2_y;   

    public Slingshot(double x, double y, int type, String fileName){
        super(type, fileName);     
        this.setX(x);
        this.setY(y);   
        this.P1_x = x;
        this.P1_y = 100;//y - this.Height/4;
        this.P2_x = 0;
        this.P2_y = 0;
    }

    public void setPlane(PaperPlane pp){
        this.Plane = pp;
        if(pp != null){
        this.Plane.setX(P1_x);
        this.Plane.setY(P1_y);
        }
    }

    public PaperPlane getPlane(){
        return this.Plane;
    }
    
   

    // public void setPlane2(PaperPlane plane2) {
    //     this.Plane2 = plane2;
    //     if(plane2 != null){
    //         this.Plane2.setX(P2_x);
    //         this.Plane2.setY(P2_y);
    //     }
    // }

    // public PaperPlane getPlane2() {
    //     return this.Plane2;
    // }
}
