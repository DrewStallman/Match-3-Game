package match.pkg3.game;
import java.awt.*;

public abstract class Piece {
    public static enum CrystalType
    {
        Blue,Green,Red;
    };
    private CrystalType type;
    Piece(CrystalType _type)
    {
        type = _type;
    }
    public CrystalType getType()
    {
        return type;
    }

    public abstract void draw(Graphics2D g,int row,int column,int xdelta,int ydelta);
    
}

 
