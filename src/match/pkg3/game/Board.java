package match.pkg3.game;

import java.awt.*;

public class Board {
    static int row2;
    static int column2;
    static int row;
    static int column;
    private static int numPiecesAdded = 0;
    private final static int NUM_ROWS = 6;
    private final static int NUM_COLUMNS = 12;      
    private static Piece board[][] = new Piece[NUM_ROWS][NUM_COLUMNS];
    private static Highlight highlight;
    
    public static void RemovePiece(int xpixel,int ypixel) {
        if (Player.GetPlayer1().isWinner() || Player.GetPlayer2().isWinner())
            return;

        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        int xpixelOffset = xpixel - Window.getX(0);
        int ypixelOffset = ypixel - Window.getY(0);
        if (xpixelOffset < 0  ||  xpixelOffset > Window.getWidth2())
            return;
        if (ypixelOffset < 0  ||  ypixelOffset > Window.getHeight2())
            return;
        int row = ypixelOffset/ydelta;
        int column = xpixelOffset/xdelta;  
        
//if left clicked on a piece.      
        if (board[row][column] != null)
        {            
//keep looping when not at the top && there is a piece.            
            while (row > 0 && board[row][column] != null)
            {
//have the current spot point to the piece above it.                
                board[row][column] = board[row-1][column];
//move up a row.                
                row--;
            }
//clear the spot if we are at the top.            
            board[0][column] = null;
        }
    }
    
    public static void SwapPiece(int xpixel,int ypixel) {
        
        if (Player.GetPlayer1().isWinner() || Player.GetPlayer2().isWinner())
            return;
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        int xpixelOffset = xpixel - Window.getX(0);
        int ypixelOffset = ypixel - Window.getY(0);
        if (xpixelOffset < 0  ||  xpixelOffset > Window.getWidth2())
            return;
        if (ypixelOffset < 0  ||  ypixelOffset > Window.getHeight2())
            return;
         row = ypixelOffset/ydelta;
         column = xpixelOffset/xdelta;

//        Player currentPlayer = Player.GetCurrentTurn();
       
//        boolean isWin = CheckMatch();
//        if (isWin)
//            currentPlayer.setWinner();
//        else {
            Player.SwitchTurn();
//            numPiecesAdded++;
//            if (numPiecesAdded == NUM_ROWS * NUM_COLUMNS)
//            {
//                Player.GetPlayer1().setWinner();
//                Player.GetPlayer2().setWinner();
//            }        
//        }


    }
    public static void SwapPiece2(int xpixel,int ypixel) {
        
        if (Player.GetPlayer1().isWinner() || Player.GetPlayer2().isWinner())
            return;
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        int xpixelOffset = xpixel - Window.getX(0);
        int ypixelOffset = ypixel - Window.getY(0);
        if (xpixelOffset < 0  ||  xpixelOffset > Window.getWidth2())
            return;
        if (ypixelOffset < 0  ||  ypixelOffset > Window.getHeight2())
            return;
         row2 = ypixelOffset/ydelta;
         column2 = xpixelOffset/xdelta;
         board[row2][column2] = board[row][column];
    }

    private static boolean CheckMatch()
    {
        final int numConnected = 3;
        int numInARow = 0;      
        int wrow = 0;
        int wcolumn = 0;
        Color currentColor = null;
//horizontal win        
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
//the current location is null                
                if (board[zrow][zcol] == null) { 
                    numInARow = 0;
                    currentColor = null;
                }
//the current location matches the current color                
                else if (board[zrow][zcol].getColor() == currentColor) { 
                    numInARow++;
                    if (numInARow == numConnected)  //if we have a win
                    {                        
                        highlight.setHighlight(wrow, wcolumn, numInARow, Highlight.Direction.Right);                          
                        return true;
                    }
                }
                else {  //the current location has a different color
                    numInARow = 1;
                    wrow = zrow;
                    wcolumn = zcol;
                    currentColor = board[zrow][zcol].getColor();
                }
            }
            numInARow = 0;    //starting a new row.
            currentColor = null;
        }
//vertical win
        numInARow = 0;        
        currentColor = null;
        for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
        {
            for (int zrow=0;zrow<NUM_ROWS;zrow++)
            {
//the current location is null                
                if (board[zrow][zcol] == null) { 
                    numInARow = 0;
                    currentColor = null;
                }
//the current location matches the current color                
                else if (board[zrow][zcol].getColor() == currentColor) { 
                    numInARow++;
                    if (numInARow == numConnected)  //if we have a win
                    {                                                 
                        highlight.setHighlight(wrow, wcolumn, numInARow, Highlight.Direction.Down);                                                  
                        return true;
                    }
                }
                else {  //the current location has a different color
                    numInARow = 1;
                    wrow = zrow;
                    wcolumn = zcol;
                    currentColor = board[zrow][zcol].getColor();
                }
            }
            numInARow = 0;    //starting a new column.
            currentColor = null;
        }
        
        
        return false;
    }
    
    
    public static void Reset() {
        numPiecesAdded = 0;
                
//clear the board.
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
            {
                int num = (int)(Math.random() * 3) + 1;
                if(num == 1)
               
                board[zrow][zcol] = new RedCrystal();
                else if(num == 2)
                board[zrow][zcol] = new GreenCrystal();
                else if(num == 3)
                 board[zrow][zcol] = new BlueCrystal();
            } 


        

 //       highlight = new Highlight();
        
    //    highlight = new Highlight(4, 1);

        
        
//row, col, num, direction
//        highlight = new Highlight(2, 3, 4, Highlight.Direction.Right);
        
                             //row, col, num, direction
//        highlight.setHighlight(2, 3, 4, Highlight.Direction.Right);        
    }
    
    public static void Draw(Graphics2D g) {
//draw grid
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
<<<<<<< HEAD
   
=======
        //highlight.draw(g,xdelta,ydelta);
        
>>>>>>> 08643cf4ea7ff471ed2ae67466e2c49c380571a7
        
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
        
        if (Player.GetCurrentTurn().isWinner() && Player.GetOtherTurn().isWinner())
        {
            g.setColor(Color.blue);
            StringCentered(g,250,554,"We Have World Peace","Arial",30);
           
        }
        else if (Player.GetCurrentTurn().isWinner())
        {
            g.setColor(Player.GetCurrentTurn().getColor());
            if (Player.GetCurrentTurn().getColor() == Color.red)
                StringCentered(g,250,554,"Player 1 is the Winner","Arial",30);
            else
                StringCentered(g,250,554,"Player 2 is the Winner","Arial",30);
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

 