
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameThread extends Thread{
    //idle = 1,dragging = 2,moving = 3,collision = 4,dead = 5
    double time = 0.0,plane_x = 0.0;
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
                    	    plane_x = plane.x_removing(plane.getV0(),plane.getAngle(),time);
                    	    plane.setX(plane.getoriginal_x() + plane_x * 4.0 * plane.direction);
                    	    plane.setY(plane.getoriginal_y() - plane.locus(plane_x,plane.getV0(),plane.getAngle()) * 4.0);			
                    	    time = time + 0.1;
            	}else{
			time = 0.0;
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
			if(planeNo < 2){
			p.planeNum = p.planeNum - 1;
                        planeNo = planeNo + 1;
            plane = p.slingShot.planes.get(planeNo);
			}else{
				if(p.planeNum == 1){
				p.planeNum = p.planeNum - 1;
				}
				p.loss = true;
			}
		}
        }

        public void WheelMoved(MouseWheelEvent e){
            plane = p.slingShot.planes.get(planeNo);
        }
}
