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

    //<editor-fold desc="transition booleans">
    public static boolean isTransition = false;
    public static boolean menuStartPressed = false;
    public static boolean creditPressed = false;
    public static boolean CreditBackPressed = false;
    public static boolean LossScreenBack = false;
    //</editor-fold>

    //<editor-fold desc="Image Importation">
    public static BufferedImage gs0;
    public static BufferedImage gs1;
    public static BufferedImage loss;
    public static BufferedImage BombHit1;
    public static BufferedImage BombHit2;
    public static BufferedImage menuStartButtonReleased;
    public static BufferedImage menuStartButtonPressed;

    public static BufferedImage CreditButtonPressed;
    public static BufferedImage CreditButtonReleased;
    public static BufferedImage creditBackPressed;
    public static BufferedImage creditBackReleased;

    public static BufferedImage binaryDigit0Released;
    public static BufferedImage binaryDigit1Released;
    public static BufferedImage binaryDigit0Pressed;
    public static BufferedImage binaryDigit1Pressed;
    public static BufferedImage bomb;
    public static BufferedImage Lives;
    //counter for the spawner
    public static int spawner = 0;
    //random number the spawnerNum has to be before it spawns a bomb
    public static int spawnerNum = 0;


    //</editor-fold>

    //<editor-fold desc="Global Coordinate Variables">
    public static int backGroundX = 0;
    public static int backGroundY = 0;
    public static int foreGroundX = 0;
    public static int foreGroundY = 0;
    //</editor-fold>

    //<editor-fold desc="Modifier Variable">
    public static int xmodifier = 0;
    public static int ymodifier = 0;
    //</editor-fold>

    //<editor-fold desc="Gameplay Variables">
    public static int score = 0;
    public static int lives = 3;
    //</editor-fold>

    //<editor-fold desc="Arrays">
    public static int[] binary = {0, 0, 0, 0, 0, 0, 0, 0};
    //stores binary value
    public static int[][] bombs = new int[3][3];
    //{bomb value, bomb value, bomb value}
    //{   x value,    x value,   x value }
    //{   y value,    y value,   y value }
    //added option of having a fourth column, 0/1 that lets you
    //change the speed of the bomb (ie. 1 means an angry bomb which moves a lot quicker)
    public static boolean[] binaryDigitPressed = {false, false, false, false, false, false, false, false};
    //</editor-fold>

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

        //<editor-fold desc="First drawing and Button Pressed">
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
        g.drawImage(creditBackReleased, foreGroundX + 820, foreGroundY + 500, null);
        if(CreditBackPressed)
        {
            g.drawImage(creditBackPressed,foreGroundX + 820, foreGroundY + 500, null);
        }
        //</editor-fold>

        //<editor-fold desc="Gamestate 0 (Menu)">
        if(gameState == 0){
            if(isTransition){
                backGroundX += xmodifier;
                foreGroundX += xmodifier + 20;
                g.drawImage(gs0, backGroundX, backGroundY, null);
                g.drawImage(gs1, foreGroundX, foreGroundY, null);
                g.drawImage(menuStartButtonReleased, foreGroundX + 50, foreGroundY + 200, null);
                g.drawImage(CreditButtonReleased, foreGroundX+120, foreGroundY +340, null);
                g.drawImage(creditBackReleased, foreGroundX + 820, foreGroundY + 500, null);

                if(backGroundX == 0){
                    isTransition = false;
                }
            }
        }
        //</editor-fold>

        //<editor-fold desc="Game state 1 (Credits)">
        if(gameState == 1){
            if(isTransition){
                backGroundX += xmodifier;
                foreGroundX += xmodifier - 20;
                g.drawImage(gs0, backGroundX, backGroundY, null);
                g.drawImage(gs1, foreGroundX, foreGroundY, null);
                g.drawImage(menuStartButtonReleased, foreGroundX + 50, foreGroundY + 200, null);
                g.drawImage(CreditButtonReleased, foreGroundX+120, foreGroundY +340, null);
                g.drawImage(creditBackReleased, foreGroundX + 820, foreGroundY + 500, null);
                if(backGroundX == -400){
                    isTransition = false;
                }
            }
        }
        //</editor-fold>

        //<editor-fold desc="Game state 2 (gameplay)">
        if(gameState == 2){
            score = 0;
            // Game state 2 gameplay
            if(!isTransition) {

                if(lives <= 0){
                    g.drawString("LOSELOSELOSELLOSELOE", 0, 300);
                    gameState = 3;
                    lives = 3;
                    bombs = new int[3][3];
                }
                else if(lives == 3) {
                    g.drawImage(Lives, 20, 20, null);
                    g.drawImage(Lives, 70, 20, null);
                    g.drawImage(Lives, 120, 20, null);
                }
                else if(lives == 2)
                {
                    g.drawImage(BombHit1 , 0, -5, null);
                    g.drawImage(Lives, 20, 20, null);
                    g.drawImage(Lives, 70, 20, null);

                }
                else if(lives == 1)
                {
                    g.drawImage(BombHit2, 0, -5, null);
                    g.drawImage(Lives, 20, 20, null);
                }



                g.setFont(new Font("Comic Sans MS", 1, 30));
                g.setColor(new Color(0, 255, 32));

                // drawing and key pressed and key released
                for (int i = 0, x = 25; i < 8; i++, x += 40) {
                    if (x == 185) {
                        x += 50;
                    }
                    if (binary[i] == 0 && !binaryDigitPressed[i]) {
                        g.drawImage(binaryDigit0Released, x, 549, null);
                    } else if (binary[i] == 1 && !binaryDigitPressed[i]) {
                        g.drawImage(binaryDigit1Released, x, 549, null);
                    } else if (binary[i] == 0 && binaryDigitPressed[i]) {
                        g.drawImage(binaryDigit0Pressed, x, 549, null);
                    } else if (binary[i] == 1 && binaryDigitPressed[i]) {
                        g.drawImage(binaryDigit1Pressed, x, 549, null);
                    }
                }

                // if integer matches with bomb
                for (int i = 0; i < bombs[0].length; i++) {
                    if (binaryValue(binary) == bombs[0][i]) {
                        score += 1;
                        bombs = bombCalculator(bombs, binaryValue(binary));
                        binary[0] = 0; //unironically the fastest way to reset
                        binary[1] = 0;
                        binary[2] = 0;
                        binary[3] = 0;
                        binary[4] = 0;
                        binary[5] = 0;
                        binary[6] = 0;
                        binary[7] = 0;
                    }
                }

                //<editor-fold desc="Show the integer value of the binary">
                //TD make it so that the array shrinks after a number is correctly inputted
                //and that it generates a new number randomly
                //conversion of array into decimal representation
                g.setFont(new Font("PseudoText", 1, 15));
                g.drawString(String.valueOf(binaryValue(binary)), 189, 569);

                //</editor-fold>

                //<editor-fold desc="Drawing bombs">
                //random bomb number display
                g.setColor(new Color(0, 255, 32));
                g.setFont(new Font("Comic Sans MS", 1, 13));
                for (int i = 0, y = 300; i < bombs[0].length; i++, y -= 50) {
                    bombs[2][i] += 1; //this moves the bomb down
                    if(bombs[2][i] > 460){
                        lives -= 1;
                        System.out.println(lives);
                        //if a bomb reaches the bottom, destroy it
                        bombs = bombCalculator(bombs, bombs[0][i]);
                        break;
                    }
                    g.drawImage(bomb, bombs[1][i], bombs[2][i], null);
                    g.drawString(String.valueOf(bombs[0][i]), bombs[1][i]+8, bombs[2][i]+50);
                }
                //</editor-fold>

                //<editor-fold desc="random bomb spawner">
                if(spawner == 0){
                    spawnerNum = (int) Math.floor(Math.random() * 160) + 120;
                }
                spawner += 1;
                if(spawnerNum == spawner){
                    spawner = 0;
                    bombs = bombCalculator(bombs);
                //</editor-fold>
                }
            }
            //<editor-fold desc="Game state 2 transition">
            else{
                backGroundY += ymodifier;
                foreGroundY += ymodifier - 9;
                g.drawImage(gs0, backGroundX, backGroundY, null);
                g.drawImage(gs1, foreGroundX, foreGroundY, null);
                g.drawImage(menuStartButtonReleased, foreGroundX + 50, foreGroundY + 200, null);
                g.drawImage(CreditButtonReleased, foreGroundX+120, foreGroundY +340, null);
                g.drawImage(creditBackReleased, foreGroundX + 820, foreGroundY + 500, null);
                if(backGroundY == -600){
                    isTransition = false;
                }
            }
            //</editor-fold>
        }
        //</editor-fold>

        //<editor-fold desc="Game state 3 (Loss Screen)">
        if(gameState == 3){
            g.drawImage(loss, 0, 0, null);
            g.drawImage(creditBackReleased, 145, 200, null);
            if(LossScreenBack)
            {
                g.drawImage(creditBackPressed,145, 200, null);
            }
        }
        //</editor-fold>

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
        gs1 = ImageIO.read(new File("Foreground.png"));
        loss = ImageIO.read(new File("GameOver.png"));
        BombHit1 = ImageIO.read(new File("BombHit1.png"));
        BombHit2 = ImageIO.read(new File("BombHit2.png"));
        menuStartButtonReleased = ImageIO.read(new File("StartButtonReleased.png"));
        menuStartButtonPressed = ImageIO.read(new File("StartButtonPressed.png"));
        CreditButtonPressed = ImageIO.read(new File("CreditButtonPressed.png"));
        CreditButtonReleased = ImageIO.read(new File("CreditButtonReleased.png"));
        creditBackPressed = ImageIO.read(new File("CreditBackPressed.png"));
        creditBackReleased = ImageIO.read(new File("CreditBackReleased.png"));
        binaryDigit0Released =  ImageIO.read(new File("0Released.png"));
        binaryDigit1Released =  ImageIO.read(new File("1Released.png"));
        binaryDigit0Pressed =  ImageIO.read(new File("0Pressed.png"));
        binaryDigit1Pressed =  ImageIO.read(new File("1Pressed.png"));
        bomb = ImageIO.read(new File("bomb.png"));
        Lives = ImageIO.read(new File("Lives.png"));

        JFrame frame = new JFrame("Binary Bomber");
        JPanel panel = new Main();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();

        // set bomb values (dont need this later)
        bombs[0][0] = (int) Math.floor(Math.random() * 255) + 1;

        //setting random x values
        bombs[1][0] = (int) Math.floor(Math.random() * 300) + 10;


        //setting y values
        bombs[2][0] = -100;

    }

    // Keybinding
    public void keyPressed(KeyEvent e){
        //<editor-fold desc="lose condition">
        //while in gameplay
        if(e.getKeyChar() == '-' && gameState == 2 && !isTransition) {
            //lose lol

            gameState = 3;
            backGroundX = 0;
            backGroundY = 0;
            System.out.println("any key to go back to menu");
            repaint();
        }
        //</editor-fold>

        // is binary digit pressed
        for(int i = 0; i < binary.length; i++) {
            if (e.getKeyChar() == '8') {
                binaryDigitPressed[7] = true;
            }

            if (e.getKeyChar() == '7') {
                binaryDigitPressed[6] = true;
            }

            if (e.getKeyChar() == '6') {
                binaryDigitPressed[5] = true;
            }

            if (e.getKeyChar() == '5') {
                binaryDigitPressed[4] = true;
            }

            if (e.getKeyChar() == '4') {
                binaryDigitPressed[3] = true;
            }

            if (e.getKeyChar() == '3') {
                binaryDigitPressed[2] = true;
            }
            if (e.getKeyChar() == '2') {
                binaryDigitPressed[1] = true;
            }

            if (e.getKeyChar() == '1') {
                binaryDigitPressed[0] = true;
            }
        }

    }
    public void keyReleased(KeyEvent e){

        //<editor-fold desc="Reset the bombs with 'q'">
        if (e.getKeyChar() == 'q') { //need to make an array that adds length to "bombs"
            bombs = new int[3][1];
            bombs[0][0] = (int) Math.floor(Math.random() * 255) + 1;

            //setting random x values
            bombs[1][0] = (int) Math.floor(Math.random() * 300) + 10;


            //setting y values
            bombs[2][0] = -100;

        }
        //</editor-fold>

        // Change from one to zero when pressed
        for(int i = 0; i < binary.length; i++)
        {
            if(binary[i] == 0 && binaryDigitPressed[i])
            {
                binary[i] = 1;
                binaryDigitPressed[i] = false;
            }
            else if(binary[i] == 1 && binaryDigitPressed[i])
            {
                binary[i] = 0;
                binaryDigitPressed[i] = false;
            }
        }
    }
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
            if(e.getX() >= 120 && e.getX() <= 280 && e.getY() >= 340 && e.getY() <= 390 && !isTransition)
            {
                creditPressed = true;
            }
        }
        if(gameState == 1)
        {
            if(e.getX() >= 20 && e.getX() <= 130 && e.getY() >= 500 && e.getY() <= 550 && !isTransition)
            {
                CreditBackPressed = true;
            }
        }
        if(gameState == 3)
        {
            if(e.getX() >= 145 && e.getX() <= 255 && e.getY() >= 200 && e.getY() <= 250 && !isTransition)
            {
                LossScreenBack = true;
            }
        }
    }
    public void mouseReleased(MouseEvent e){
        if(menuStartPressed)
        {
            ymodifier = -25;
            gameState = 2;
            isTransition = true;
            menuStartPressed = false;
        }
        if(creditPressed)
        {
            xmodifier = -20;
            gameState = 1;
            isTransition = true;
            creditPressed = false;
        }
        if(CreditBackPressed)
        {
            xmodifier = 20;
            gameState = 0;
            isTransition = true;
            CreditBackPressed = false;
        }
        if(LossScreenBack)
        {
            backGroundX = 0;
            backGroundY = 0;
            foreGroundX = 0;
            foreGroundY = 0;
            gameState = 0;
            LossScreenBack = false;

        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}

    // determine the integer value of the binary combination
    public static int binaryValue(int[] x) {
        int binaryValue = 0;
        for (int i = 7, j = 0; i >= 0; i--, j++) {
            binaryValue += binary[j] * (int) (Math.pow(2, i));
        }
        return binaryValue;
    }

    // shrink the array of the bombs after one has been eliminated
    public static int[][] bombCalculator(int[][] x, int y) {
        int[][] placeholder = new int[3][x[0].length - 1];
        for (int i = 0, j = 0; i < x[0].length; i++) {
            if (y != x[0][i]) {
                placeholder[0][j] = x[0][i];
                placeholder[1][j] = x[1][i];
                placeholder[2][j] = x[2][i];
                j++;
            }
        }
        return placeholder;
    }
    public static int[][] bombCalculator(int[][] x) {
        int[][] placeholder = new int[3][x[0].length + 1];
        for (int i = 0; i < x[0].length; i++) {
            placeholder[0][i] = x[0][i];
            placeholder[1][i] = x[1][i];
            placeholder[2][i] = x[2][i];
        }
        placeholder[0][x[0].length] = (int) Math.floor(Math.random() * 255) + 1;
        placeholder[1][x[0].length] = (int) Math.floor(Math.random() * 300) + 10;
        placeholder[2][x[0].length] = -100;

        return placeholder;
    }

}

// Change clouds bc they look fugly DONE
// Change city background bc the lighting makes no sense DONE
// Change bottom binary part to look better DONE
// Add text background so you can actually see it DONE
// maybe change the font bc it also looks fugly DONE
// CURRENT GOAL:
// CREATE ANIMATIONS FOR BINARY KEY PRESSED