import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class GameThread extends Thread{
    double time = 0.0,plane_x = 0.0;
int count = 1;
    Paperplane plane;
    GamePanel p;
        int planeNo = 0;

public GameThread(GamePanel panel){
	p = panel;
	plane = p.slingShot.planes.get(0);
}

        @Override
        public void run(){
            while(true){
            	if(plane.go){
			if(plane.fall){
				plane.setX(plane.getX() - 2);
                    	    	plane.setY(plane.getY() + 10);						
            		}else{
                	plane_x = plane.x_removing(plane.getV0(),plane.getAngle(),time);
                	plane.setX(plane.getoriginal_x() + plane_x * 4.0 * plane.direction);
                	plane.setY(plane.getoriginal_y() - plane.locus(plane_x,plane.getV0(),plane.getAngle()) * 4.0);			
                	time = time + 0.1;
			}
		}else{
			time = 0.0;
			count = 1;
		}
                try{
                    Thread.sleep(50);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        }

        public void Clicked(MouseEvent e){
		if(!plane.live){
			if(planeNo < p.planes.size() - 1){
			count = 1;
                        planeNo = planeNo + 1;
            plane = p.slingShot.planes.get(planeNo);
			}else{
				p.loss = true;
			}
		}
        }
        public void Released(MouseEvent e){
		if(plane.go){
			if((planeNo <= p.planes.size() - 1)  && (count == 1)){
				p.planeNum = p.planeNum - 1;
				count ++;
			}
		}
        }


        public void WheelMoved(MouseWheelEvent e){
            plane = p.slingShot.planes.get(planeNo);
        }
}
