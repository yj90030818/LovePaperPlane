import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;



public class GameUtil {
    final static double G = 9.8;

    //generate Image object for all GameObject
    public static Image gen_Image(String fileName){
        Image img = null;
        try{
            URL u = GameUtil.class.getClassLoader().getResource("images/"+fileName) ;
            img = ImageIO.read(u);
        }catch(IOException e){
            //TODE: Pop up the warning window
            System.out.println("Error!!! : Can't find Image resourse.");
            System.exit(0);
        }
        return img;
    }

    //Convert Gui_X to Coordinary_X
    public static double Coordinary_X(int x){
        return (double)x;
    }

    //Convert Gui_Y to Coordinary_Y
    public static double Coordinary_Y(int y){
        return (double)(GameFrame.HEIGHT - y);
    }

    //Convert Coordinary_X to Gui_X 
    public static int Gui_X(double x){
        return (int)x;
    }

    //Convert Coordinary_Y to Gui_Y 
    public static int Gui_Y(double y){
        return (int)(GameFrame.HEIGHT - y);
    }

    //Movement--
    public static void nextPosition(GameObject go){
        // System.out.println("(X,Y)="+go.getX()+","+go.getY()+"|(V_x,V_y)="+go.getV_x()+","+go.getV_y());   
        go.setX(go.getX() + go.getV_x()/ 10.0);
        go.setY(go.getY() + go.getV_y()/ 10.0);
    }

    public static void nextV_y(GameObject go){
        go.setV_y(go.getV_y() - G);
    }

    //--
}
