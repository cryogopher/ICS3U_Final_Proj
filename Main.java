import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;

public class Main extends JPanel implements MouseListener, KeyListener, Runnable{

    // Game States
    // The following are just examples of a game state legend
    // 0 <- Home Screen
    // 1 <- Credit/Tutorial Screen
    // 2 <- Game Screen
    // 3 <- Game Over Screen
    public static int gameState = 0;

    // Buffered Images
    public static BufferedImage sky;
    public static BufferedImage cityBack;
    public static BufferedImage cityFore;
    public static BufferedImage bottom;
    public static BufferedImage bomb;
    public static BufferedImage rocket;

    // Mouse/Keyboard Events
    public static int mouseX;
    public static int mouseY;


    // Game Stats
    // Player stats
    public static int score;
    public static int hp;
    public static int xp;
    public static int[] playerPos;

    // Enemy stats
    public static int[] hps;
    public static int[] damages;
    public static int[] directions;
    public static int[] enemyPos;

    // General stats
    public static int map;
    public static int row;
    public static int column;
    public static int leftBound;
    public static int rightBound;
    public static int frameCounter;

    // Timer
    public void run() {
        while(true) {
            repaint();
            try {
                Thread.sleep(20); //50FPS
            }
            catch(Exception e) {
                System.out.println("Timer Error");
            }
        }
    }

    // Setting Game/Window/Image/Timer Data
    public Main(){
        // Image Importation
        try{
        }
        catch (Exception e){
            System.out.println("Image Error");
        }

        setPreferredSize(new Dimension(400,600));
        this.setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        Thread thread = new Thread(this);
        thread.start();
    }

    // Creating Game Window
    public static void main(String[] args){
        JFrame frame = new JFrame("Game Name");
        Main panel = new Main();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    // Draw Screen
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(gameState == 0){

        }
        else if(gameState == 1){

        }
        else if(gameState == 2){

        }
        else if(gameState == 3){

        }

    }

    // Mouse and Keyboard Methods
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
