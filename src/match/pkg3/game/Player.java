package match.pkg3.game;
import java.awt.Color;
public class Player 
{
    private static Player currentTurn;
    private static Player players[] = new Player[2];
    private int greenPoints;
    private int redPoints;
    private int bluePoints;
    private boolean winner;
    public static void Reset()
    {
        players[0] = new Player();
        players[1] = new Player();
        currentTurn = players[0];
    }
    public static Player GetCurrentTurn() {
        return (currentTurn);
    }
    public static Player GetPlayer1()
    {
        return players[0];
    }
    public static Player GetPlayer2()
    {
        return players[1];
    }
    public static void SwitchTurn() {
        if (currentTurn == players[0])
            currentTurn = players[1];
        else
            currentTurn = players[0];
    }
    
    Player()
    {
        winner = false;
        greenPoints = 0;
        redPoints = 0;
        bluePoints = 0;
    }
    public boolean isWinner() 
    {
        return (winner);
    }
    public void setWinner() 
    {
        winner = true;
    }
    public int getPoints(Color color)
    {
        if (color == Color.BLUE)
        {
            return bluePoints;
        }
        else if (color == Color.RED)
        {
            return redPoints;
        }
        else if (color == Color.GREEN)
        {
            return greenPoints;
        }
        return 0;
    }
    public void changePoints(Color color,int value)
    {
        if (color == Color.BLUE)
        {
            bluePoints += value;
        }
        else if (color == Color.RED)
        {
            redPoints += value;
        }
        else if (color == Color.GREEN)
        {
            greenPoints += value;
        }
    }
}

 