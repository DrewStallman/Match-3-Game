package match.pkg3.game;

import java.awt.*;
import java.util.ArrayList;

public class Board 
{
    private static boolean PieceSwap;
    private static boolean GameStart;
    private static Piece TempSwap = null;
    private static int TempSwapRow = 0;
    private static int TempSwapColumn = 0;
    private static Highlight signal;
    private final static int NUM_ROWS = 6;
    private final static int NUM_COLUMNS = 12;      
    private static Piece board[][] = new Piece[NUM_ROWS][NUM_COLUMNS];
    static ArrayList<Piece> pieces = new ArrayList<>();
    
    public static void RemovePieces()
    {
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
                int Row = zrow;
                if (board[zrow][zcol] == null)
                {
                    board[zrow][zcol] = RanPiece(zrow,zcol);
                }
            }
        }
        CheckMatch();
    }
    
    public static Piece RanPiece(int zrow,int zcol)
    {
        Piece returnPiece = null;
        
        int num = (int)(Math.random() * 3) + 1;
        if(num == 1)
            returnPiece = new RedCrystal(Piece.CrystalType.Red,zrow,zcol);
        else if(num == 2)
            returnPiece = new GreenCrystal(Piece.CrystalType.Green,zrow,zcol);
        else if(num == 3)
            returnPiece = new BlueCrystal(Piece.CrystalType.Blue,zrow,zcol);
        
        return returnPiece;
    }
    
    public static void SwitchPieces(int xpixel,int ypixel) 
    {
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        int xpixelOffset = xpixel - Window.getX(0);
        int ypixelOffset = ypixel - Window.getY(0);
        if (xpixelOffset < 0  ||  xpixelOffset > Window.getWidth2())
            return;
        if (ypixelOffset < 0  ||  ypixelOffset > Window.getHeight2())
            return;
        int Row = ypixelOffset/ydelta;
        int Column = xpixelOffset/xdelta;
        
        if (!PieceSwap)
        {
            PieceSwap = true;
            TempSwap = board[Row][Column];
            TempSwapRow = Row;
            TempSwapColumn = Column;
            signal.setHighlight(Row, Column, 1);
            return;
        }
        else if (PieceSwap)
        {
            if ((Row==TempSwapRow+1 && Column==TempSwapColumn) || (Row==TempSwapRow-1 && Column==TempSwapColumn) || (Row==TempSwapRow && Column==TempSwapColumn-1) || (Row==TempSwapRow && Column==TempSwapColumn+1))
            {
                board[TempSwapRow][TempSwapColumn] = board[Row][Column];
                board[Row][Column] = TempSwap;
            }
        }
        signal = new Highlight();
        PieceSwap = false;
        CheckMatch();
    }

    public static void CheckMatch()
    {
        int blueCount = 0;
        int redCount = 0;
        int greenCount = 0;
//horizontal match
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
               if (zcol < NUM_COLUMNS && board[zrow][zcol].getType() == Piece.CrystalType.Blue)
               {
                   int wcol = zcol;
                   int wrow = zrow;
                   while (wcol < NUM_COLUMNS && board[wrow][wcol].getType() == Piece.CrystalType.Blue)
                   {
                       blueCount++;
                       wcol++;
                   }
                   if (blueCount >= 3)
                   {
                       while (zcol < NUM_COLUMNS && board[zrow][zcol].getType() == Piece.CrystalType.Blue)
                       {
                           board[zrow][zcol] = null;
                           RemovePieces();
                           zcol++;
                       }
                   }
                   blueCount = 0;
               }
               if (zcol < NUM_COLUMNS && board[zrow][zcol].getType() == Piece.CrystalType.Green)
               {
                   int wcol = zcol;
                   int wrow = zrow;
                   while (wcol < NUM_COLUMNS && board[wrow][wcol].getType() == Piece.CrystalType.Green)
                   {
                       greenCount++;
                       wcol++;
                   }
                   if (greenCount >= 3)
                   {
                       while (zcol < NUM_COLUMNS && board[zrow][zcol].getType() == Piece.CrystalType.Green)
                       {
                           board[zrow][zcol] = null;
                           RemovePieces();
                           zcol++;
                       }
                   }
                   greenCount = 0;
               }
               if (zcol < NUM_COLUMNS && board[zrow][zcol].getType() == Piece.CrystalType.Red)
               {
                   int wcol = zcol;
                   int wrow = zrow;
                   while (wcol < NUM_COLUMNS && board[wrow][wcol].getType() == Piece.CrystalType.Red)
                   {
                       redCount++;
                       wcol++;
                   }
                   if (redCount >= 3)
                   {
                       while (zcol < NUM_COLUMNS && board[zrow][zcol].getType() == Piece.CrystalType.Red)
                       {
                           board[zrow][zcol] = null;
                           RemovePieces();
                           zcol++;
                       }
                   }
                   redCount = 0;
               }

            }
            blueCount = 0;
            greenCount = 0;
            redCount = 0;
        }
        blueCount = 0;
        greenCount = 0;
        redCount = 0;
//vertical match
        for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
        {
            for (int zrow=0;zrow<NUM_ROWS;zrow++)        
            {
                if (zrow < NUM_ROWS && board[zrow][zcol].getType() == Piece.CrystalType.Blue)
                {
                    int wcol = zcol;
                    int wrow = zrow;
                    while (wrow < NUM_ROWS && board[wrow][wcol].getType() == Piece.CrystalType.Blue)
                    {
                        blueCount++;
                        wrow++;
                    }
                    if (blueCount >= 3)
                    {
                        while (zrow < NUM_ROWS && board[zrow][zcol].getType() == Piece.CrystalType.Blue)
                        {
                            board[zrow][zcol] = null;
                            RemovePieces();
                            zrow++;
                        }
                    }
                    blueCount = 0;
                }
                if (zrow < NUM_ROWS && board[zrow][zcol].getType() == Piece.CrystalType.Red)
                {
                    int wcol = zcol;
                    int wrow = zrow;
                    while (wrow < NUM_ROWS && board[wrow][wcol].getType() == Piece.CrystalType.Red)
                    {
                        redCount++;
                        wrow++;
                    }
                    if (redCount >= 3)
                    {
                        while (zrow < NUM_ROWS && board[zrow][zcol].getType() == Piece.CrystalType.Red)
                        {
                            board[zrow][zcol] = null;
                            RemovePieces();
                            zrow++;
                        }
                    }
                    redCount = 0;
                }
                if (zrow < NUM_ROWS && board[zrow][zcol].getType() == Piece.CrystalType.Green)
                {
                    int wcol = zcol;
                    int wrow = zrow;
                    while (wrow < NUM_ROWS && board[wrow][wcol].getType() == Piece.CrystalType.Green)
                    {
                        greenCount++;
                        wrow++;
                    }
                    if (greenCount >= 3)
                    {
                        while (zrow < NUM_ROWS && board[zrow][zcol].getType() == Piece.CrystalType.Green)
                        {
                            board[zrow][zcol] = null;
                            RemovePieces();
                            zrow++;
                        }
                    }
                    greenCount = 0;
                }
            }
            blueCount = 0;
            greenCount = 0;
            redCount = 0;
        }
        blueCount = 0;
        greenCount = 0;
        redCount = 0;
    }
    
    public static void Reset() {
        PieceSwap = false;
        signal = new Highlight();
        GameStart = true;
//clear the board.
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
            {
                int num = (int)(Math.random() * 3) + 1;
                if(num == 1)
                    board[zrow][zcol] = new RedCrystal(Piece.CrystalType.Red,zrow,zcol);
                else if(num == 2)
                    board[zrow][zcol] = new GreenCrystal(Piece.CrystalType.Green,zrow,zcol);
                else if(num == 3)
                    board[zrow][zcol] = new BlueCrystal(Piece.CrystalType.Blue,zrow,zcol);
            }
        }
        CheckMatch();
    }
    
    public static void Draw(Graphics2D g) {
//draw grid
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
        signal.draw(g,xdelta,ydelta);
        
        g.setColor(Color.black);
        for (int zi = 1;zi<NUM_ROWS;zi++)
        {
            g.drawLine(Window.getX(0),Window.getY(zi*ydelta),
                    Window.getX(Window.getWidth2()),Window.getY(zi*ydelta));
        }
        
        for (int zi = 1;zi<NUM_COLUMNS;zi++)
        {
            g.drawLine(Window.getX(zi*xdelta),Window.getY(0),
                    Window.getX(zi*xdelta),Window.getY(Window.getHeight2()));
        }
        
        
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
                if (board[zrow][zcol] != null)
                    board[zrow][zcol].draw(g, zrow, zcol,xdelta, ydelta);
            }
        }   
    }
    
    public static void StringCentered(Graphics2D g,int xpos,int ypos,String text,String font,int size)
    {
        g.setFont (new Font (font,Font.PLAIN, size)); 
        int width = g.getFontMetrics().stringWidth(text);
        int height = g.getFontMetrics().getHeight();
        xpos = xpos - width/2;
        ypos = ypos - height/4;
        xpos = Window.getX(xpos);
        ypos = Window.getYNormal(ypos);
        g.drawString(text, xpos, ypos);           
    }    
    
    
}

 