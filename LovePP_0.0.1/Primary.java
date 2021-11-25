import java.util.ArrayList;

public class Primary extends Thread{
    private ArrayList<PaperPlane> Planes;
    private ArrayList<Obstacle> Obstacles;
    private Slingshot Slingshot;
    private PaperPlane nowPlane = null;
    private boolean game = true;
    // private int sccore = 0;

    public Primary(Prepare prepare){
        Planes = prepare.getPlanes();
        Obstacles = prepare.getObstacles();
        Slingshot = prepare.getSlingshot(); 
        Planes.get(0).setV_x(5);
        Planes.get(0).setV_y(5);
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        while(game){
            
            nowPlane = Planes.get(0);
            if(nowPlane.getState()==0)
                Slingshot.setPlane1(nowPlane);
            // for(int index = 0; index <= Planes.size(); index++){
            //     if(Planes.get(index).getState() == 0){
            //         nowPlane = Planes.get(index);
            //         Slingshot.setPlane1(nowPlane);
            //         if(index < Planes.size()-1){
            //             Slingshot.setPlane2(Planes.get(index + 1));
            //         }else{
            //             Slingshot.setPlane2(null);
            //         }
            //         break;
            //     }
            // }
            currentTime=System.currentTimeMillis();
            System.out.println("AAAAAAAAAAAAAAAAAAAAAA time = "+(currentTime - time));
            time = System.currentTimeMillis();
            System.out.println("(X,Y)="+nowPlane.getX()+","+nowPlane.getY()+"|(V_x,V_y)="+nowPlane.getV_x()+","+nowPlane.getV_y());
            if(nowPlane == null)
                game = false;
            
            //dragging
            if(nowPlane.getState() == 1){
                
            }
            nowPlane.setState(2);
            //fly
            if(nowPlane.getState() == 2){
                try {
                    sleep(40);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                GameUtil.nextV_y(nowPlane);
                GameUtil.nextPosition(nowPlane);
                
                for (Obstacle obstacle : Obstacles) {
                    if(nowPlane.isHit(obstacle)){
                        //when plane hit some obstacle
                        break;
                    }
                }
                
            }

            //hit
            if(nowPlane.getState() == 3){
                
            }

            //dead
            if(nowPlane.getState() == 4){}
        }
    }

    public PaperPlane getNowPlane() {
        return nowPlane;
    }
}
