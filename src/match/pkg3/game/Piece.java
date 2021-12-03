package match.pkg3.game;
import java.awt.*;

public abstract class Piece {
    private Color color;
    Piece()
    {
        
    }
    public Color getColor()
    {
        return (color);
    }

    public abstract void draw(Graphics2D g,int row,int column,int xdelta,int ydelta);
    
}

 
