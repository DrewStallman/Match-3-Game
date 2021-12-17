package match.pkg3.game;

import java.awt.*;

public class Circle 
{
    public int xpos;
    public int ypos;
    public Image crystal;
    public Player player;
    public Image circle;
    Circle(Image _crystal,Image _circle,int _xpos,int _ypos)
    {
        crystal = _crystal;
        circle = _circle;
        xpos = _xpos;
        ypos = _ypos;
    }
    public void draw(Graphics g,Player player,Color color)
    {
        g.drawImage(circle,xpos,ypos,150,150,null);
        g.drawImage(crystal,xpos+40,ypos+15,75,75,null);
        g.setColor(Color.black);
        g.setFont (new Font ("Arial",Font.TYPE1_FONT, 30));
        g.drawString(""+player.getPoints(color), xpos+65, ypos+125);
    }
}
