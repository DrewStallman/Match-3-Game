package match.pkg3.game;
import java.awt.*;

public class RedCrystal extends Piece {
    private static Image StaticImage;
    RedCrystal(CrystalType _type)
    {
        super(_type);
        StaticImage = Toolkit.getDefaultToolkit().getImage("./Red Crystal.PNG");
    }    
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta)
    {
        g.drawImage(StaticImage,Window.getX(column*xdelta),Window.getY(row*ydelta),xdelta,ydelta,null);  
    }
}