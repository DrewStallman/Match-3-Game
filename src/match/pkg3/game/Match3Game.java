package match.pkg3.game;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Match3Game extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    
    Image BackgroundImage;
    Image HealthBar1;
    Image HealthBar2;
    Image HealthBar1P2;
    Image HealthBar2P2;
    public static void main(String[] args) {
        Match3Game frame = new Match3Game();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public Match3Game() {
        
        addMouseListener(new MouseAdapter() {
            int test = 0;
            public void mousePressed(MouseEvent e) {
                
                if (e.BUTTON1 == e.getButton() ) {
                    Board.SwitchPieces(e.getX(),e.getY());
                    
                }

                if (e.BUTTON3 == e.getButton()) {
                    //Board.RemovePiece(e.getX(),e.getY());
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
                   g.drawImage(HealthBar1,1066, 30, Window.xsize/3, Window.ysize/13,this);
        g.drawImage(HealthBar2,0, 30, Window.xsize/3, Window.ysize/13,this);
        g.drawImage(HealthBar1P2,1066, 30, Window.xsize/3, Window.ysize/13,this);
        g.drawImage(HealthBar2P2,0, 30, Window.xsize/3, Window.ysize/13,this); 

              
        Board.Draw(g);
        

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
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            BackgroundImage = Toolkit.getDefaultToolkit().getImage("./CaveBackground.PNG");
            HealthBar1 = Toolkit.getDefaultToolkit().getImage("./Healthbar1.PNG");
            HealthBar2 = Toolkit.getDefaultToolkit().getImage("./Healthbar2.PNG");
            HealthBar1P2 = Toolkit.getDefaultToolkit().getImage("./Healthbar1P2.PNG");
            HealthBar2P2 = Toolkit.getDefaultToolkit().getImage("./Healthbar2P2.PNG");
            reset();

        }

        
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

}

 

 

 