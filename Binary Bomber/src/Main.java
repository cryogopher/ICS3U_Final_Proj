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
    public static boolean isTransition = false;
    public static BufferedImage gs0;
    public static BufferedImage gs1;
    public static BufferedImage loss;

    //Global Coordinate Variables
    public static int backGroundX = 0;
    public static int backGroundY = 0;

    //modifier variable is how much to move the screen by
    public static int xmodifier = 0;
    public static int ymodifier = 0;
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
        //g.drawImage(gs1, foreGroundX, foreGroundY, null);

        //Drawing different game states
        if(gameState == 0){
            if(isTransition){
                backGroundX += xmodifier;
                g.drawImage(gs0, backGroundX, backGroundY, null);
                if(backGroundX == 0){
                    isTransition = false;
                }
            }
        }

        //right side of screen
        if(gameState == 1){
            if(isTransition){
                backGroundX += xmodifier;
                g.drawImage(gs0, backGroundX, backGroundY, null);
                if(backGroundX == -400){
                    isTransition = false;
                }
            }
        }

        //bottom part of screen
        if(gameState == 2){
            if(isTransition){
                backGroundY += ymodifier;
                g.drawImage(gs0, backGroundX, backGroundY, null);
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
        gs1 = ImageIO.read(new File("ForeGround.png"));
        loss = ImageIO.read(new File("Loss.png"));


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
    // changed to 1 and 2 for the moment (1 = lose, 2 = win)
    public void keyPressed(KeyEvent e){
        //while in menu
        //when a character is pressed, set isTransition to true
        //then change the modifier (this determines how fast a screen will scroll) (make it accelerate in future)
        if(gameState == 0){

            if(e.getKeyChar() == '1'){
                //goes to gameplay screen

                ymodifier = -25;
                gameState = 2;
                isTransition = true;
                System.out.println("any key to lose");

                repaint();
            }
            else if(e.getKeyChar() == '2'){
                //goes to credits
                xmodifier = -20;
                gameState = 1;
                isTransition = true;
                System.out.println("any key to go back to menu");

                repaint();
            }
        }

        //while in credits screen
        else if(gameState == 1){

            xmodifier = 20;
            gameState = 0;
            isTransition = true;
            System.out.println("1 gameplay, 2 for credits");
            repaint();

        }

        //while in gameplay
        else if(gameState == 2){
            //lose lol

            gameState = 3;
            backGroundX = 0;
            backGroundY = 0;
            System.out.println("any key to go back to menu");
            repaint();

        }

        else if(gameState == 3){

            backGroundX = 0;
            backGroundY = 0;
            gameState = 0;
            System.out.println("1 gameplay, 2 for credits");
            repaint();

        }


    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}


}
