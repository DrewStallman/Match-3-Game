package match.pkg3.game;
import java.awt.*;

public abstract class Piece {
    private int row;
    private int column;
    public static enum CrystalType
    {
        Blue,Green,Red;
    };
    private CrystalType type;
    Piece(CrystalType _type,int _row,int _col)
    {
        row = _row;
        column = _col;
        type = _type;
    }
    public CrystalType getType()
    {
        return type;
    }
    public int getRow()
    {
        return row;
    }
    public int getColumn()
    {
        return column;
    }

    public abstract void draw(Graphics2D g,int row,int column,int xdelta,int ydelta);
    
}

 
