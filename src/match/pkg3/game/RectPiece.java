package match.pkg3.game;
import java.awt.*;

public class RectPiece extends Piece {
    
    RectPiece(Color _color)
    {
        super(_color);
    }    
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta)
    {
        g.setColor(getColor());
        g.fillRect(Window.getX(column*xdelta+5), Window.getY(row*ydelta+5), xdelta-10, ydelta-10);        
    }
    
}