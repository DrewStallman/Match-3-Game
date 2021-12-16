
package match.pkg3.game;
import java.awt.*;
public class GreenCrystal extends Piece {
    private static Image StaticImage;
    GreenCrystal(CrystalType _type,int _row,int _col)
    {
        super(_type,_row,_col);
        StaticImage = Toolkit.getDefaultToolkit().getImage("./Green Crystal.PNG");
    }
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta)
    {
        g.drawImage(StaticImage,Window.getX(column*xdelta),Window.getY(row*ydelta),xdelta,ydelta,null); 
    }    
}
