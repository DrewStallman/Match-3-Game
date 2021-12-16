package match.pkg3.game;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Match3Game extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    int health1 = 100;
    int health2 = 100;
    int damage1 = 0;
    int damage2 = 0;
    int damageAdd1 = 0;
    int damageAdd2 = 0;
    int playerTurn;
    int Time;
    Image image;
    Graphics2D g;
    
    Image BackgroundImage;
    Image HealthBar1;
    Image HealthBar2;
    Image HealthBar1P2;
    Image HealthBar2P2;
    Image MiddlePiece;
    Image PlayerBar;
    Image BoardOutline;
    Image Circle;
    Image CircleHighlighted;
    Image RedCrystal;
    Image BlueCrystal;
    Image GreenCrystal;
    static Match3Game frame;
    public static void main(String[] args) {
        frame = new Match3Game();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public Match3Game() {
        
        addMouseListener(new MouseAdapter() {
            int test = 0;
            public void mousePressed(MouseEvent e) {
                
                if (e.BUTTON1 == e.getButton() ) {
                    if (Time <= 50)
                        return;
                    Board.SwitchPieces(e.getX(),e.getY(),frame);
                    CrystalsHighlighted(e.getX(),e.getY());
                }
                
                if (e.BUTTON3 == e.getButton()) {
                    //Board.RemovePiece(e.getX(),e.getY());
                    Board.CheckMatch();
                }
                repaint();
            }
        });
            

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {

        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {

        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_DOWN == e.getKeyCode()) {
                } else if (e.VK_LEFT == e.getKeyCode()) {
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                } else if (e.VK_ESCAPE == e.getKeyCode()) {
                    reset();
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
        g.drawImage(BackgroundImage,0, 0, Window.xsize, Window.ysize,this);
//        g.setColor(Color.red);
//        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.black);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }
        
        
        if(Player.GetCurrentTurn() == Player.GetPlayer1())
            playerTurn = 1;
        else
            playerTurn = 2;
        
        HealthBar();
        TurnIndicator(); 
        Crystals();
        Board.Draw(g,frame);
        
        if (Time < 50)
        {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT);
            g.setColor(Color.BLACK);
            g.setFont (new Font ("Arial",Font.TYPE1_FONT, 40));
            g.drawString("Hello, this is a match 3 game, match 3 of the same pieces for one point of that ",50,100);
            g.drawString("piece type. The points for each crystal type max out at 10, click on the point ",50,140);
            g.drawString("indicators to use that attack. ",50,180);
        }
        
        gOld.drawImage(image, 0, 0, null);
    }

////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = .1;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
    
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        Board.Reset();
        Player.Reset();
        Time = 0;
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            BackgroundImage = Toolkit.getDefaultToolkit().getImage("./Cave.PNG");
            HealthBar1 = Toolkit.getDefaultToolkit().getImage("./Healthbar1.PNG");
            HealthBar2 = Toolkit.getDefaultToolkit().getImage("./Healthbar2.PNG");
            HealthBar1P2 = Toolkit.getDefaultToolkit().getImage("./Healthbar1P2.PNG");
            HealthBar2P2 = Toolkit.getDefaultToolkit().getImage("./Healthbar2P2.PNG");
            MiddlePiece = Toolkit.getDefaultToolkit().getImage("./MiddlePiece.PNG");
            PlayerBar = Toolkit.getDefaultToolkit().getImage("./PlayerTurn.PNG");
            BoardOutline = Toolkit.getDefaultToolkit().getImage("./BoardOutline.PNG");
            Circle = Toolkit.getDefaultToolkit().getImage("./Circle.PNG");
            CircleHighlighted = Toolkit.getDefaultToolkit().getImage("./CircleHighlighted.PNG");
            RedCrystal = Toolkit.getDefaultToolkit().getImage("./Red Crystal.PNG");
            BlueCrystal  = Toolkit.getDefaultToolkit().getImage("./Blue Crystal.PNG");
            GreenCrystal  = Toolkit.getDefaultToolkit().getImage("./Green Crystal.PNG");
            reset();
        }
        
        
        Time++;
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
    
    public void TurnIndicator()
    {   
        g.drawImage(PlayerBar,685, 170, 240, -140,this);
        g.setFont (new Font ("Arial",Font.TYPE1_FONT, 55));
        g.drawString("Player", 720, 77);
        g.setFont (new Font ("Arial",Font.TYPE1_FONT, 75));
        g.drawString("" + playerTurn, 780, 161);
        g.drawImage(BoardOutline,282, 400, 1039, 500,this);
    }
    public void Damage()
    {
        health1 -= damage1;
        health2 -= damage2;
        damageAdd1 = damage1*5;
        damageAdd2 = damage2*5;
    }
    public void HealthBar()
    {
        g.drawImage(HealthBar1,1066, 30, Window.xsize/3, Window.ysize/13,this);
        g.drawImage(HealthBar2,0, 30, Window.xsize/3, Window.ysize/13,this);
        g.setColor(Color.green);
        g.fillRect(0, 0, 511 - damageAdd1, Window.ysize/9-5);
        g.setColor(Color.green);
        g.fillRect(1088 + damageAdd2, 0, 510, Window.ysize/9-5);
        g.drawImage(MiddlePiece,387, 20, 825, 80,this);
        g.setColor(Color.BLACK);
        g.setFont (new Font ("Arial",Font.TYPE1_FONT, 50));
        g.drawString(health1 + "%", 100, 80);
        g.setFont (new Font ("Arial",Font.TYPE1_FONT, 50));
        g.drawString(health2 + "%", 1350, 80);
        g.drawImage(HealthBar1P2,1066, 30, Window.xsize/3, Window.ysize/13,this);
        g.drawImage(HealthBar2P2,0, 30, Window.xsize/3, Window.ysize/13,this);
    }
    
    public void CrystalsHighlighted(int xpos, int ypos)
    {
        //do not change numbers
        if (xpos > 130 - 65 &&
        xpos < 130 + 65 &&
        ypos > 650 - 65 &&
        ypos < 650 + 65 && Player.GetPlayer1().getPoints(Color.BLUE) >= 3)
                        g.drawImage(CircleHighlighted,50, 580, 150, 150,this);//blue1
                    else if (xpos > Window.xsize-145 - 65 &&
        xpos < Window.xsize-145 + 65 &&
        ypos > 650 - 65 &&
        ypos < 650 + 65 && Player.GetPlayer2().getPoints(Color.BLUE) >= 3)
                       g.drawImage(CircleHighlighted,Window.xsize-200, 580, 150, 150,this);//blue2 
                    else if (xpos > 130 - 65 &&
        xpos < 130 + 65 &&
        ypos > 800 - 65 &&
        ypos < 800 + 65 && Player.GetPlayer1().getPoints(Color.RED) >= 3)
                        g.drawImage(CircleHighlighted,50, 730, 150, 150,this);//red1
                    else if (xpos > Window.xsize-145 - 65 &&
        xpos < Window.xsize-145 + 65 &&
        ypos > 800 - 65 &&
        ypos < 800 + 65 && Player.GetPlayer2().getPoints(Color.RED) >= 3)
                       g.drawImage(CircleHighlighted,Window.xsize-200, 730, 150, 150,this);//red2
                    else if (xpos > 130 - 65 &&
        xpos < 130 + 65 &&
        ypos > 500 - 65 &&
        ypos < 500 + 65 && Player.GetPlayer1().getPoints(Color.GREEN) >= 3)
                        g.drawImage(CircleHighlighted,50, 430, 150, 150,this);//green1
                    else if (xpos > Window.xsize-145 - 65 &&
        xpos < Window.xsize-145 + 65 &&
        ypos > 500 - 65 &&
        ypos < 500 + 65 && Player.GetPlayer2().getPoints(Color.GREEN) >= 3)
                      g.drawImage(CircleHighlighted,Window.xsize-200, 430, 150, 150,this);//green2
    }
    
    public void Crystals()
    {
                    
        g.drawImage(Circle,50, 430, 150, 150,this);
        g.drawImage(Circle,50, 580, 150, 150,this);
        g.drawImage(Circle,50, 730, 150, 150,this);
        g.drawImage(Circle,Window.xsize-200, 430, 150, 150,this);
        g.drawImage(Circle,Window.xsize-200, 580, 150, 150,this);      
        g.drawImage(Circle,Window.xsize-200, 730, 150, 150,this);
        g.drawImage(RedCrystal,Window.xsize-165, 740, 75, 75,this);
        g.drawImage(BlueCrystal,Window.xsize-165, 590, 75, 75,this);
        g.drawImage(GreenCrystal,Window.xsize-165, 440, 75, 75,this);
        g.drawImage(RedCrystal,85, 740, 75, 75,this);
        g.drawImage(BlueCrystal,85, 590, 75, 75,this);
        g.drawImage(GreenCrystal,85, 440, 75, 75,this);        
        g.setColor(Color.black);
        g.setFont (new Font ("Arial",Font.TYPE1_FONT, 30));
        g.drawString(""+Player.GetPlayer2().getPoints(Color.BLUE), 104, 705);
        g.drawString(""+Player.GetPlayer1().getPoints(Color.BLUE), Window.xsize-145, 705);
        g.drawString(""+Player.GetPlayer2().getPoints(Color.RED), 104, 855);
        g.drawString(""+Player.GetPlayer1().getPoints(Color.RED), Window.xsize-145, 855);
        g.drawString(""+Player.GetPlayer2().getPoints(Color.GREEN), 104, 555);
        g.drawString(""+Player.GetPlayer1().getPoints(Color.GREEN), Window.xsize-145, 555);
    }
}