import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.nio.Buffer;
import java.security.Key;
public class Main extends JPanel implements KeyListener, Runnable, MouseListener{
    // Game States
    // 0 - Start Menu
    // 1 - High score/Credits
    // 2 - Gameplay Screen
    // 3 - Loss
    public static int gameState = 0;
    public static boolean isTransition = false;
    public static boolean menuStartPressed = false;
    public static boolean creditPressed = false;
    public static boolean CreditBackPressed = false;
    public static BufferedImage gs0;
    public static BufferedImage gs1;
    public static BufferedImage loss;
    public static BufferedImage menuStartButtonReleased;
    public static BufferedImage menuStartButtonPressed;

    public static BufferedImage CreditButtonPressed;
    public static BufferedImage CreditButtonReleased;
    public static BufferedImage creditBackPressed;
    public static BufferedImage creditBackReleased;

    //Global Coordinate Variables
    public static int backGroundX = 0;
    public static int backGroundY = 0;
    public static int foreGroundX = 0;
    public static int foreGroundY = 0;

    //modifier variable is how much to move the screen by
    public static int xmodifier = 0;
    public static int ymodifier = 0;

    //panel generating
    public Main(){
        setPreferredSize(new Dimension(400, 600));
        this.setFocusable(true);
        addKeyListener(this);
        // adding Thread (Timer)
        Thread thread = new Thread(this);
        thread.start();
        addMouseListener(this);
    }

    //Paint
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(gs0, backGroundX, backGroundY, null);
        g.drawImage(gs1, foreGroundX, foreGroundY, null);
        g.drawImage(menuStartButtonReleased, foreGroundX + 50, foreGroundY + 200, null);
        if(menuStartPressed)
        {
            g.drawImage(menuStartButtonPressed, foreGroundX + 50, foreGroundY + 200, null);
        }
        g.drawImage(CreditButtonReleased, foreGroundX+120, foreGroundY +340, null);
        if(creditPressed)
        {
            g.drawImage(CreditButtonPressed, foreGroundX+120, foreGroundY +340, null);
        }
        g.drawImage(creditBackReleased, foreGroundX + 520, foreGroundY + 500, null);
        if(CreditBackPressed)
        {
            g.drawImage(creditBackPressed,foreGroundX + 520, foreGroundY + 500, null);
        }
        //g.drawImage(gs1, foreGroundX, foreGroundY, null);

        //Drawing different game states
        if(gameState == 0){
            if(isTransition){
                backGroundX += xmodifier;
                foreGroundX += xmodifier + 20;
                g.drawImage(gs0, backGroundX, backGroundY, null);
                g.drawImage(gs1, foreGroundX, foreGroundY, null);
                g.drawImage(menuStartButtonReleased, foreGroundX + 50, foreGroundY + 200, null);
                g.drawImage(CreditButtonReleased, foreGroundX+120, foreGroundY +340, null);
                g.drawImage(creditBackReleased, foreGroundX + 620, foreGroundY + 500, null);

                if(backGroundX == 0){
                    isTransition = false;
                }
            }
        }

        //right side of screen
        if(gameState == 1){
            if(isTransition){
                backGroundX += xmodifier;
                foreGroundX += xmodifier - 20;
                g.drawImage(gs0, backGroundX, backGroundY, null);
                g.drawImage(gs1, foreGroundX, foreGroundY, null);
                g.drawImage(menuStartButtonReleased, foreGroundX + 50, foreGroundY + 200, null);
                g.drawImage(CreditButtonReleased, foreGroundX+120, foreGroundY +340, null);
                g.drawImage(creditBackReleased, foreGroundX + 620, foreGroundY + 500, null);

                if(backGroundX == -400){
                    isTransition = false;
                }
            }
        }

        //bottom part of screen
        if(gameState == 2){
            if(isTransition){
                backGroundY += ymodifier;
                foreGroundY += ymodifier - 9;
                g.drawImage(gs0, backGroundX, backGroundY, null);
                g.drawImage(gs1, foreGroundX, foreGroundY, null);
                g.drawImage(menuStartButtonReleased, foreGroundX + 50, foreGroundY + 200, null);
                g.drawImage(CreditButtonReleased, foreGroundX+120, foreGroundY +340, null);
                g.drawImage(creditBackReleased, foreGroundX + 620, foreGroundY + 500, null);
                if(backGroundY == -600){
                    isTransition = false;
                }
            }
        }

        if(gameState == 3){
            g.drawImage(loss, 0, 0, null);
        }


    }

    //Framerate
    public void run()
    {
        while(true){
            // 1) set up frame rate
            try{
                Thread.sleep(20);
            }
            catch(Exception e){}

            repaint();
        }
    }

    public static void main(String[] args) throws IOException{
        System.out.println("1 gameplay, 2 for credits");

        gs0 = ImageIO.read(new File("Background.png"));
        gs1 = ImageIO.read(new File("Foreground.png"));
        loss = ImageIO.read(new File("GameOver.png"));
        menuStartButtonReleased = ImageIO.read(new File("StartButtonReleased.png"));
        menuStartButtonPressed = ImageIO.read(new File("StartButtonPressed.png"));
        CreditButtonPressed = ImageIO.read(new File("CreditButtonPressed.png"));
        CreditButtonReleased = ImageIO.read(new File("CreditButtonReleased.png"));
        creditBackPressed = ImageIO.read(new File("CreditBackPressed.png"));
        creditBackReleased = ImageIO.read(new File("CreditBackReleased.png"));

        JFrame frame = new JFrame("Binary Bomber");
        JPanel panel = new Main();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();


    }

    // Keybinding
    // changed to 1 and 2 for the moment (1 = lose, 2 = win)
    public void keyPressed(KeyEvent e){
        //while in menu
        //when a character is pressed, set isTransition to true
        //then change the modifier (this determines how fast a screen will scroll) (make it accelerate in future)
        if(gameState == 0){

            if(e.getKeyChar() == '1' && !isTransition){
                //goes to gameplay screen

                ymodifier = -25;
                gameState = 2;
                isTransition = true;
                System.out.println("any key to lose");

                repaint();
            }
            else if(e.getKeyChar() == '2' && !isTransition){
                //goes to credits
                xmodifier = -20;
                gameState = 1;
                isTransition = true;
                System.out.println("any key to go back to menu");

                repaint();
            }
        }

        //while in credits screen
        else if(gameState == 1 && !isTransition){

            xmodifier = 20;
            gameState = 0;
            isTransition = true;
            System.out.println("1 gameplay, 2 for credits");
            repaint();

        }

        //while in gameplay
        else if(gameState == 2 && !isTransition){
            //lose lol

            gameState = 3;
            backGroundX = 0;
            backGroundY = 0;
            System.out.println("any key to go back to menu");
            repaint();

        }

        // after loss
        else if(gameState == 3 && !isTransition){

            backGroundX = 0;
            backGroundY = 0;
            foreGroundX = 0;
            foreGroundY = 0;
            gameState = 0;

            System.out.println("1 gameplay, 2 for credits");
            repaint();

        }


    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}

    // Mousebinding
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){
        if(gameState == 0)
        {
            if(e.getX() >= 50 && e.getX() <= 350 && e.getY() >= 200 && e.getY() <= 330 && !isTransition)
            {
                menuStartPressed = true;
            }
        }
        if(gameState == 0)
        {
            if(e.getX() >= 120 && e.getX() <= 280 && e.getY() >= 340 && e.getY() <= 390 && !isTransition)
            {
                creditPressed = true;
            }
        }
        if(gameState == 1)
        {
            if(e.getX() >= 620 && e.getX() <= 730 && e.getY() >= 500 && e.getY() <= 550 && !isTransition)
            {
                CreditBackPressed = true;
            }
        }

    }
    public void mouseReleased(MouseEvent e){
        if(menuStartPressed)
        {
            ymodifier = -25;
            gameState = 2;
            isTransition = true;
            System.out.println("any key to lose");
            menuStartPressed = false;
        }
        if(creditPressed)
        {
            xmodifier = -20;
            gameState = 1;
            isTransition = true;
            System.out.println("any key to go back to menu");
            creditPressed = false;
        }
        if(CreditBackPressed)
        {
            xmodifier = 20;
            gameState = 0;
            isTransition = true;
            System.out.println("1 gameplay, 2 for credits");
            CreditBackPressed = false;
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}


}

// Change clouds bc they look fugly
// Change city background bc the lighting makes no sense
// Change bottom binary part to look better
// Add text background so you can actually see it
// maybe change the font bc it also looks fugly