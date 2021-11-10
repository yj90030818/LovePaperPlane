import java.util.ArrayList;

public class Prepare {
    private GameFrame GFrame;
    GamePanel Panel;
    private ArrayList<PaperPlane> Planes = new ArrayList<>();
    private ArrayList<Obstacle> Obstacles = new ArrayList<>();
    private Slingshot Slingshot;
    private Primary primary;

    public Prepare(int Stage_ID){
        Load_Object(Stage_ID);
        primary = new Primary(this);
        Panel = new GamePanel(this, Planes, Obstacles, Slingshot);
        GFrame = new GameFrame("LovePP_0.0.1", Panel); 
        GFrame.setVisible(true);

    } 

    //Load in GameObject [UNFINISHED!!!]
    public void Load_Object(int Stage_ID){
        PaperPlane plane = new PaperPlane(0, "pp.png");
        Planes.add(plane);
        Obstacle obstacle = new Obstacle(650, 100, 0, "po.png");
        Obstacles.add(obstacle);
        Slingshot = new Slingshot(75, 50, 0, "ps.png");    
    }

    public ArrayList<Obstacle> getObstacles() {
        return Obstacles;
    }

    public ArrayList<PaperPlane> getPlanes() {
        return Planes;
    }

    public Slingshot getSlingshot() {
        return Slingshot;
    }

    public void setPrimary(Primary primary) {
        this.primary = primary;
    }

    public Primary getPrimary() {
        return primary;
    }
}
