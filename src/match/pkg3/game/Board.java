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
                    while (Row > 0 && board[Row][zcol] == null)
                    {
                        board[Row][zcol] = board[Row-1][zcol];
                        Row--;
                    }
                    board[0][zcol] = RanPiece(zrow,zcol);
                }
            }
        }
    }
    
        Piece returnPiece = null;
        
        int num = (int)(Math.random() * 3) + 1;
        if(num == 1)
            board[zrow][zcol] = new RedCrystal(Piece.CrystalType.Red,zrow,zcol);
        else if(num == 2)
            board[zrow][zcol] = new GreenCrystal(Piece.CrystalType.Green,zrow,zcol);
        else if(num == 3)
            board[zrow][zcol] = new BlueCrystal(Piece.CrystalType.Blue,zrow,zcol);
        
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
                switch (board[zrow][zcol].getType()) 
                {
                    case Blue:
                        int wcol = zcol;
                        blueCount++;
                        for (int i=wcol;i<NUM_COLUMNS-1;i++)
                        {
                            if (wcol+1 < NUM_COLUMNS && board[zrow][wcol+1].getType() == Piece.CrystalType.Blue)
                            {
                                wcol++;
                                blueCount++;
                            }
                            else
                            {
                                break;
                            }
                        }
                        if (blueCount > 2)
                        {
                            wcol = zcol;
                            
                            for (int j=wcol;j<(blueCount+wcol);j++)
                            {
                                Board.pieces.add(board[zrow][j]);
                            }
                            blueCount = 0;
                            break;
                        }
                        else 
                        {
                            blueCount = 0;
                            break;
                        }
                    case Red:
                        int mcol = zcol;
                        redCount++;
                        for (int i=mcol;i<NUM_COLUMNS-1;i++)
                        {
                            if (mcol+1 < NUM_COLUMNS && board[zrow][mcol+1].getType() == Piece.CrystalType.Red)
                            {
                                mcol++;
                                redCount++;
                            }
                            else
                            {
                                break;
                            }
                        }
                        if (redCount > 2)
                        {
                            mcol = zcol;
                            
                            for (int j=mcol;j<(redCount+mcol);j++)
                            {
                                Board.pieces.add(board[zrow][j]);
                            }
                            redCount = 0;
                            break;
                        }
                        else 
                        {
                            redCount = 0;
                            break;
                        }
                    case Green:
                        int pcol = zcol;
                        greenCount++;
                        for (int i=pcol;i<NUM_COLUMNS-1;i++)
                        {
                            if (pcol+1 < NUM_COLUMNS && board[zrow][pcol+1].getType() == Piece.CrystalType.Green)
                            {
                                pcol++;
                                greenCount++;
                            }
                            else
                            {
                                break;
                            }
                        }
                        if (greenCount > 2)
                        {
                            pcol = zcol;
                            
                            for (int j=pcol;j<(greenCount+pcol);j++)
                            {
                                Board.pieces.add(board[zrow][j]);
                            }
                            greenCount = 0;
                            break;
                        }
                        else 
                        {
                            greenCount = 0;
                            break;
                        }
                    default:
                        break;
                }
            }
            blueCount = 0;
            greenCount = 0;
            redCount = 0;
        }
        
//vertical match
        for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
        {
            for (int zrow=0;zrow<NUM_ROWS;zrow++)        
            {
                switch (board[zrow][zcol].getType()) 
                {
                    case Blue:
                        int wrow = zrow;
                        blueCount++;
                        for (int i=wrow;i<NUM_ROWS-1;i++)
                        {
                            if (wrow+1 < NUM_ROWS && board[wrow+1][zcol].getType() == Piece.CrystalType.Blue)
                            {
                                wrow++;
                                blueCount++;
                            }
                            else
                            {
                                break;
                            }
                        }
                        if (blueCount > 2)
                        {
                            wrow = zrow;
                            
                            for (int j=wrow;j<(blueCount+wrow);j++)
                            {
                                Board.pieces.add(board[j][zcol]);
                            }
                            blueCount = 0;
                            break;
                        }
                        else 
                        {
                            blueCount = 0;
                            break;
                        }
                    case Red:
                        int mrow = zrow;
                        redCount++;
                        for (int i=mrow;i<NUM_ROWS-1;i++)
                        {
                            if (mrow+1 < NUM_ROWS && board[mrow+1][zcol].getType() == Piece.CrystalType.Red)
                            {
                                mrow++;
                                redCount++;
                            }
                            else
                            {
                                break;
                            }
                        }
                        if (redCount > 2)
                        {
                            mrow = zrow;
                            
                            for (int j=mrow;j<(redCount+mrow);j++)
                            {
                                Board.pieces.add(board[j][zcol]);
                            }
                            redCount = 0;
                            break;
                        }
                        else 
                        {
                            redCount = 0;
                            break;
                        }
                    case Green:
                        int prow = zrow;
                        greenCount++;
                        for (int i=prow;i<NUM_ROWS-1;i++)
                        {
                            if (prow+1 < NUM_ROWS && board[prow][zcol].getType() == Piece.CrystalType.Green)
                            {
                                prow++;
                                greenCount++;
                            }
                            else
                            {
                                break;
                            }
                        }
                        if (greenCount > 2)
                        {
                            prow = zrow;
                            
                            for (int j=prow;j<(greenCount+prow);j++)
                            {
                                Board.pieces.add(board[j][zcol]);
                            }
                            greenCount = 0;
                            break;
                        }
                        else 
                        {
                            greenCount = 0;
                            break;
                        }
                    default:
                        break;
                }
            }
            blueCount = 0;
            greenCount = 0;
            redCount = 0;
        }
        
        for (int i=0;i<Board.pieces.size();i++)
        {
            board[Board.pieces.get(i).getRow()][Board.pieces.get(i).getColumn()] = null;
        }
        Board.pieces.removeAll(pieces);
        RemovePieces();
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

 