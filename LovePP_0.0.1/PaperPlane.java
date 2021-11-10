public class PaperPlane extends GameObject{
    private int state = 0;//0->standby, 1->dragging, 2->fly, 3->hit, 4->dead
    public PaperPlane(int type, String fileName){
        super(type, fileName);
        this.X = -100;
        this.Y = -100;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public Boolean isHit(Obstacle o){
        boolean X_overLap = false, Y_overLap = false;
        if((this.X + this.Width) >= o.get_Gui_X() || this.X <= (o.get_Gui_X() + o.getWidth()))
            X_overLap = true;
        if((this.Y + this.Height) >= o.get_Gui_Y() || this.Y <= (o.get_Gui_Y() + o.Height))
            Y_overLap = true;
        return (X_overLap && Y_overLap);
    }
}