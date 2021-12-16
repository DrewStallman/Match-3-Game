package match.pkg3.game;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Match3Game extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    boolean circleClicked;
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
    Circle leftCircle1;
    Circle leftCircle2;
    Circle leftCircle3;
    
    Circle rightCircle1;
    Circle rightCircle2;
    Circle rightCircle3;
    
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
    static ArrayList<Circle> circles = new ArrayList<>();
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
                    if (Time <= 100)
                        return;
                    Board.SwitchPieces(e.getX(),e.getY());
                    IndicatorClick(e.getX(),e.getY());
                }
                
                if (e.BUTTON3 == e.getButton()) {
                    //Board.RemovePiece(e.getX(),e.getY());
                    //Board.CheckMatch();
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
        leftCircle1.draw(g);
        leftCircle2.draw(g);
        leftCircle3.draw(g);
        rightCircle1.draw(g);
        rightCircle2.draw(g);
        rightCircle3.draw(g);
        Board.Draw(g);
        
        if (Time < 100)
        {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT);
            g.setColor(Color.BLACK);
            g.setFont (new Font ("Arial",Font.TYPE1_FONT, 40));
            g.drawString("Hello, this is a match 3 game, match 3 of the same pieces for one point of that ",50,100);
            g.drawString("piece type. The points for each crystal type max out at 10, click on the point ",50,140);
            g.drawString("indicators to use that attack. The highlighted buttons on left or right of the ",50,180);
            g.drawString("screen are the current player's attack buttons. Take your time and use your brain. ",50,220);
            g.drawString("This is not on a timer and only one piece or attack can be moved/used per turn.",50,260);
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
        leftCircle1 = new Circle(BlueCrystal,Circle,50, 430);
        leftCircle2 = new Circle(RedCrystal,Circle,50, 580);
        leftCircle3 = new Circle(GreenCrystal,Circle,50, 730);
        rightCircle1 = new Circle(BlueCrystal,Circle,Window.xsize-200, 430);
        rightCircle2 = new Circle(RedCrystal,Circle,Window.xsize-200, 580);
        rightCircle3 = new Circle(GreenCrystal,Circle,Window.xsize-200, 730);
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
    public void IndicatorClick(int xpos, int ypos)
    {
        if (Player.GetCurrentTurn() == Player.GetPlayer1())
        {
            leftCircle1.circle = Toolkit.getDefaultToolkit().getImage("./CircleHighlighted.PNG");
            leftCircle2.circle = Toolkit.getDefaultToolkit().getImage("./CircleHighlighted.PNG");
            leftCircle3.circle = Toolkit.getDefaultToolkit().getImage("./CircleHighlighted.PNG");
            rightCircle1.circle = Toolkit.getDefaultToolkit().getImage("./Circle.PNG");
            rightCircle2.circle = Toolkit.getDefaultToolkit().getImage("./Circle.PNG");
            rightCircle3.circle = Toolkit.getDefaultToolkit().getImage("./Circle.PNG");
        }
        else 
        {
            rightCircle1.circle = Toolkit.getDefaultToolkit().getImage("./CircleHighlighted.PNG");
            rightCircle2.circle = Toolkit.getDefaultToolkit().getImage("./CircleHighlighted.PNG");
            rightCircle3.circle = Toolkit.getDefaultToolkit().getImage("./CircleHighlighted.PNG");
            leftCircle1.circle = Toolkit.getDefaultToolkit().getImage("./Circle.PNG");
            leftCircle2.circle = Toolkit.getDefaultToolkit().getImage("./Circle.PNG");
            leftCircle3.circle = Toolkit.getDefaultToolkit().getImage("./Circle.PNG");
        }
        if (Player.GetCurrentTurn() == Player.GetPlayer1())
        {
            if (xpos >= leftCircle1.xpos-150 && xpos <= leftCircle1.xpos+150 && ypos >= leftCircle1.ypos-150 && ypos <= leftCircle1.ypos+150)
            {
                System.out.println("Click");
            }
            else if (xpos >= leftCircle2.xpos-150 && xpos <= leftCircle2.xpos+150 && ypos >= leftCircle2.ypos-150 && ypos <= leftCircle2.ypos+150)
            {
                System.out.println("Click");
            }
            else if (xpos >= leftCircle3.xpos-150 && xpos <= leftCircle3.xpos+150 && ypos >= leftCircle3.ypos-150 && ypos <= leftCircle3.ypos+150)
            {
                System.out.println("Click");
            }
        }
        
        if (Player.GetCurrentTurn() == Player.GetPlayer2())
        {
            if (xpos >= rightCircle1.xpos-150 && xpos <= rightCircle1.xpos+150 && ypos >= rightCircle1.ypos-150 && ypos <= rightCircle1.ypos+150)
            {
                System.out.println("Click");
            }
            else if (xpos >= rightCircle2.xpos-150 && xpos <= rightCircle2.xpos+150 && ypos >= rightCircle2.ypos-150 && ypos <= rightCircle2.ypos+150)
            {
                System.out.println("Click");
            }
            else if (xpos >= rightCircle3.xpos-150 && xpos <= rightCircle3.xpos+150 && ypos >= rightCircle3.ypos-150 && ypos <= rightCircle3.ypos+150)
            {
                System.out.println("Click");
            }
        }
        
    }
}