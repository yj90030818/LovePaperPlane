import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
 
public class GameUtil{
    private GameUtil(){} 
    
    //從指定路徑取出圖片
    public static Image getImage(int num,String path){
        BufferedImage bi = null;
        Image image = null;
        try{
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
            //image = bi.getScaledInstance(60,40,Image.SCALE_DEFAULT);
        }catch(IOException e){
            e.printStackTrace();
        }

        if(num == 1){ //plane
            image = bi.getScaledInstance(60,40,Image.SCALE_DEFAULT);
        }
        else if(num == 2){ //obstacle
            image = bi.getScaledInstance(60,200,Image.SCALE_DEFAULT);
        }
        else if(num == 3){ //slingshot
            image = bi.getScaledInstance(60,100,Image.SCALE_DEFAULT);
        }

        return image;
    }
    
    public int GUI_y(double cy){
        return (int)(GameFrame.F_HEIGHT-cy);
    }
    
    public double CO_y(int gy){
        return (double)(GameFrame.F_HEIGHT-gy);
    }
        
 
}
