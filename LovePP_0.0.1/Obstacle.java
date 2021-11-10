public class Obstacle extends GameObject{
    public Obstacle(double x, double y, int type, String fileName){
        super(type, fileName); 
        this.setX(x);
        this.setY(y);       
    }
}
