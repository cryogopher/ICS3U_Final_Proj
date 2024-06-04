import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.nio.Buffer;
import java.security.Key;

//store binary numbers as an array, or just keep the decimal representation in an array
//and then convert the array "binary" into decimal and save that representation into
//another variable
//Math.floor(Math.random) // for random number generation
public class Binary_Checker extends JPanel implements KeyListener, Runnable {
    public static BufferedImage bomb;
    public static int[] binary = {0, 0, 0, 0, 0, 0, 0, 0};
    //stores binary value
    public static int[][] bombs = new int[3][1];
    //{bomb value, bomb value, bomb value}
    //{   x value,    x value,   x value }
    //{   y value,    y value,   y value }
    //added option of having a fourth column, 0/1 that lets you
    //change the speed of the bomb (ie. 1 means an angry bomb which moves a lot quicker)

    //make a frame counter for bomb animation later
    public static int lives = 3;

    //counter for the spawner
    public static int spawner = 0;
    //random number the spawnerNum has to be before it spawns a bomb
    public static int spawnerNum = 0;
    public Binary_Checker() {
        setPreferredSize(new Dimension(400, 600));
        this.setFocusable(true);
        addKeyListener(this);
        // adding Thread (Timer)
        Thread thread = new Thread(this);
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        bomb = ImageIO.read(new File("bomb.png"));

        JFrame frame = new JFrame("Binary Check");
        Binary_Checker panel = new Binary_Checker();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
        //setting bomb value
        bombs[0][0] = (int) Math.floor(Math.random() * 255) + 1;


        //setting random x values
        bombs[1][0] = (int) Math.floor(Math.random() * 300) + 10;

        //setting y values
        bombs[2][0] = 0;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(lives <= 0){
            g.drawString("LOSELOSELOSELLOSELOE", 0, 300);

        }

        g.setFont(new Font("Comic Sans MS", 1, 50));
        g.drawString("Press q to reset", 0, 40);

        for (int i = 0, x = 10; i < 8; i++, x += 50) {
            g.drawString(String.valueOf(binary[i]), x, 500);
        }
        //optimize later so it's not a bunch of for loops
        //this turns text green if it is the same

        for (int i = 0; i < bombs[0].length; i++) {
            if (binaryValue(binary) == bombs[0][i]) {
                g.setColor(new Color(0, 255, 32));
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


        //TD make it so that the array shrinks after a number is correctly inputted
        //and that it generates a new number randomly
        //conversion of array into decimal representation
        g.drawString(String.valueOf(binaryValue(binary)), 300, 400);
        g.setColor(new Color(0, 0, 0));

        g.setFont(new Font("Comic Sans MS", 1, 30));

        //random bomb number display
        for (int i = 0; i < bombs[0].length; i++) {
            bombs[2][i] += 1; //this moves the bomb down
            //calculate hit detection and lives here
            if(bombs[2][i] > 500){
                lives -= 1;
                //if a bomb reaches the bottom, destroy it
                bombs = bombCalculator(bombs, bombs[0][i]);
                break;
            }

            g.drawImage(bomb, bombs[1][i], bombs[2][i], null);
            g.drawString(String.valueOf(bombs[0][i]), bombs[1][i], bombs[2][i]);
        }

        System.out.println(lives);

        if(spawner == 0){
            spawnerNum = (int) Math.floor(Math.random() * 160) + 120;
        }
        spawner += 1;
        if(spawnerNum == spawner){
            spawner = 0;
            bombs = bombCalculator(bombs);
        }

    }

    //frame rate stuff that doesn't matter
    public void run() {

        while (true) {
            //1) Set up frame rate
            try {
                Thread.sleep(20); //20ms per frame -> 50 frames per second
            } catch (Exception e) {
            }

            //2) Redraw the screen
            repaint();

            //test doesnt work rn
        }
    }

    public void keyPressed(KeyEvent e) {
        //might not want to use keyPressed, and use Type instead.

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'q') { //need to make an array that adds length to "bombs"
            bombs = new int[3][1];
            lives = 3;
            bombs[0][0] = (int) Math.floor(Math.random() * 255) + 1;

            bombs[1][0] = (int) Math.floor(Math.random() * 300) + 10;

        }

        if (e.getKeyChar() == '8') {
            if (binary[7] == 0) {
                binary[7] = 1;
            } else {
                binary[7] = 0;
            }
        }

        if (e.getKeyChar() == '7') {
            if (binary[6] == 0) {
                binary[6] = 1;
            } else {
                binary[6] = 0;
            }
        }

        if (e.getKeyChar() == '6') {
            if (binary[5] == 0) {
                binary[5] = 1;
            } else {
                binary[5] = 0;
            }
        }

        if (e.getKeyChar() == '5') {
            if (binary[4] == 0) {
                binary[4] = 1;
            } else {
                binary[4] = 0;
            }
        }

        if (e.getKeyChar() == '4') {
            if (binary[3] == 0) {
                binary[3] = 1;
            } else {
                binary[3] = 0;
            }
        }

        if (e.getKeyChar() == '3') {
            if (binary[2] == 0) {
                binary[2] = 1;
            } else {
                binary[2] = 0;
            }
        }

        if (e.getKeyChar() == '2') {
            if (binary[1] == 0) {
                binary[1] = 1;
            } else {
                binary[1] = 0;
            }
        }

        if (e.getKeyChar() == '1') {
            if (binary[0] == 0) {
                binary[0] = 1;
            } else {
                binary[0] = 0;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }


    //calculates the decimal value given a binary array

    public static int binaryValue(int[] x) {
        int binaryValue = 0;
        for (int i = 7, j = 0; i >= 0; i--, j++) {
            binaryValue += binary[j] * (int) (Math.pow(2, i));
        }
        return binaryValue;
    }

    //Calculates the amount of bombs at any given time
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
        return placeholder;
    }



}
