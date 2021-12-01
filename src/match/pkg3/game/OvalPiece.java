package match.pkg3.game;
import java.awt.*;

public class OvalPiece extends Piece {
    
    OvalPiece(Color _color)
    {
        super(_color);
    }    
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta)
    {
        g.setColor(getColor());
        g.fillOval(Window.getX(column*xdelta), Window.getY(row*ydelta), 
        xdelta, ydelta);        
    }
    
}