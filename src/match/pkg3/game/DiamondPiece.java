
package match.pkg3.game;
import java.awt.*;
public class DiamondPiece extends Piece {
    
    DiamondPiece(Color _color)
    {
        super(_color);
    }    
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta)
    {
        g.setColor(getColor());
        int xvals[] = {Window.getX(column*xdelta)+xdelta/2,Window.getX(column*xdelta)+xdelta,Window.getX(column*xdelta)+xdelta/2,Window.getX(column*xdelta)};
        int yvals[] = {Window.getY(row*ydelta),Window.getY(row*ydelta)+ydelta/2,Window.getY(row*ydelta)+ydelta,Window.getY(row*ydelta)+ydelta/2};
        g.fillPolygon(xvals,yvals,xvals.length);       
    } 
    
}
