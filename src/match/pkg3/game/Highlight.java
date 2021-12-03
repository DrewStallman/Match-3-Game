package match.pkg3.game;
import java.awt.*;

public class Highlight {
    private int row;
    private int column;
    private boolean isHighlight;
    private int numBoxes;
    
    public Highlight()
    {
        isHighlight = false;
    }
    
    public Highlight(int _row,int _column)
    {
        row = _row;
        column = _column;
        isHighlight = true;
    }
     
    
    
    
    
    public Highlight(int _row,int _column,int _numBoxes)
    {
        row = _row;
        column = _column;
        numBoxes = _numBoxes;
        isHighlight = true;
    }
    
    
    public void setHighlight(int _row,int _column,int _numBoxes)
    {
        row = _row;
        column = _column;
        numBoxes = _numBoxes;
        isHighlight = true;        
    }
    /*
    public void draw(Graphics2D g,int xdelta,int ydelta) {
        if (!isHighlight)
            return;
        g.setColor(Color.yellow);
        g.fillRect(Window.getX(column*xdelta), Window.getY(row*ydelta), xdelta, ydelta);  
    }    
    */
    
 
    public void draw(Graphics2D g,int xdelta,int ydelta) {
        if (!isHighlight)
            return;
        g.setColor(Color.yellow);
        int zcolumn = column;
        int zrow = row;
        g.fillRect(Window.getX(zcolumn*xdelta), Window.getY(zrow*ydelta), xdelta, ydelta);
    }

}