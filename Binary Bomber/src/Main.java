import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.nio.Buffer;
import java.security.Key;
public class Main extends JPanel implements KeyListener, Runnable{
    // Game States
    // 0 - Start Menu
    // 1 - High score/Credits
    // 2 - Gameplay Screen
    // 3 - Loss
    public static int gameState = 0;
    public static BufferedImage gs0;
    public static BufferedImage gs1;

    // Move States
    // Move state will be global integer variable
    // -1 - no movestate
    // 0 - transition from menu screen to credits screen
    // 1 - transition from credits screen to menu screen
    // 2 - transition from menu screen to game screen
    // 3 - transition from loss screen to menu screen
    // (transition from game screen to loss screen will be done during gameplay phase)
    public static int moveState = -1;

    //Global Coordinate Variables
    public static int backGroundX = 0;
    public static int backGroundY = 0;

    public static int foreGroundX = 0;
    public static int foreGroundY = 0;


    //panel generating
    public Main(){
        setPreferredSize(new Dimension(400, 600));
        this.setFocusable(true);
        addKeyListener(this);
        // adding Thread (Timer)
        Thread thread = new Thread(this);
        thread.start();
    }

    //Paint
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(gs0, backGroundX, backGroundY, null);
        g.drawImage(gs1, foreGroundX, foreGroundY, null);

        // Based off the movestate, we create animations
        if(moveState == 0)
        {
            System.out.println("menu to credits");
            //create anims for menu to credits
            moveState = -1;

        }
        else if(moveState == 1)
        {
            System.out.println("credits to menu");
            //create anims for credits to menu
            moveState = -1;
        }
        else if(moveState == 2)
        {
            System.out.println("menu to game");
            //create anims for menu to game
            moveState = -1;
        }
        else if(moveState == 3)
        {
            System.out.println("loss to menu");
            //create anims for loss to menu
            moveState = -1;

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

        gs0 = ImageIO.read(new File("Background.png"));
        gs1 = ImageIO.read(new File("ForeGround.png"));
        JFrame frame = new JFrame("Binary Bomber");
        JPanel panel = new Main();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
        

    }

    //Set keybinds for now for navigation between states
    // c - menu to credits
    // b - credits to menu
    // s - menu to game
    // t - game to loss(no anim between them necessary rn)
    // l - loss to menu
    public void keyPressed(KeyEvent e){
        // menu to credits and menu to game detect
        if(gameState == 0 && moveState == -1)
        {
            if(e.getKeyChar() == 'c')
            {
                moveState = 0;
                gameState = 1;
            }
            else if(e.getKeyChar() == 's')
            {
                moveState = 2;
                gameState = 2;
            }
        }
        // credits to menu detect
        else if(gameState == 1 && moveState == -1)
        {
            if(e.getKeyChar() == 'b')
            {
                moveState = 1;
                gameState = 0;
            }
        }
        // game to loss access
        else if(gameState == 2)
        {
            if(e.getKeyChar() == 't')
            {
                gameState = 3;
                System.out.println("You Lose");
            }
        }

        // loss to menu detect
        else if(gameState == 3 && moveState == -1)
        {
            if(e.getKeyChar() == 'l')
            {
                moveState = 3;
                gameState = 0;
            }
        }

    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}


}
