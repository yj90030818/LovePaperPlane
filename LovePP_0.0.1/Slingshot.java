
public class Slingshot extends GameObject{
    private PaperPlane Plane1 = null;//, Plane2 = null;
    private double P1_x, P1_y; //plane initial position
    private double P2_x, P2_y;   

    public Slingshot(double x, double y, int type, String fileName){
        super(type, fileName);     
        this.setX(x);
        this.setY(y);   
        this.P1_x = x;
        this.P1_y = 100;//y - this.Height/4;
        this.P2_x = this.X - 100;
        this.P2_y = GameFrame.HEIGHT - 100;
    }

    public void setPlane1(PaperPlane pp){
        this.Plane1 = pp;
        if(pp != null){
        this.Plane1.setX(P1_x);
        this.Plane1.setY(P1_y);
        }
    }

    public PaperPlane getPlane1(){
        return this.Plane1;
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
