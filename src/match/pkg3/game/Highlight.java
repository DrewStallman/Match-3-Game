package match.pkg3.game;
import java.awt.*;

public class Highlight {
    public enum Direction {Right,Down};
    private int row;
    private int column;
    private Direction direction;
    private boolean isHighlight;
    private int numBoxes;
    
    public Highlight()
    {
//        isHighlight = false;
    }
    
    public Highlight(int _row,int _column)
    {
        row = _row;
        column = _column;
        isHighlight = true;
    }
     
    
    
    
    
    public Highlight(int _row,int _column,int _numBoxes,Direction _direction)
    {
        row = _row;
        column = _column;
        numBoxes = _numBoxes;
        direction = _direction;
//        isHighlight = true;
    }
    
    
    public void setHighlight(int _row,int _column,int _numBoxes,Direction _direction) {
        row = _row;
        column = _column;
        numBoxes = _numBoxes;
        direction = _direction;
//        isHighlight = true;        
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
//        if (!isHighlight)
//            return;
        g.setColor(Color.yellow);
        int zcolumn = column;
        int zrow = row;
        for (int i=0;i<numBoxes;i++) {
            g.fillRect(Window.getX(zcolumn*xdelta), Window.getY(zrow*ydelta), xdelta, ydelta);  
            if (direction == Direction.Down)
                zrow++;
            else if (direction == Direction.Right)
                zcolumn++;
        }
    }

}