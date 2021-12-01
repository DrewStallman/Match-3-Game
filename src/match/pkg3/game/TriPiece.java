package match.pkg3.game;
import java.awt.*;

public class TriPiece extends Piece {
    
    TriPiece(Color _color)
    {
        super(_color);
    }    
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta)
    {
        g.setColor(getColor());
        int xvals[] = {Window.getX(column*xdelta)+xdelta/2,Window.getX(column*xdelta),Window.getX(column*xdelta)+xdelta};
        int yvals[] = {Window.getY(row*ydelta),Window.getY(row*ydelta)+ydelta,Window.getY(row*ydelta)+ydelta};
        g.fillPolygon(xvals,yvals,xvals.length);         
    }    
}
