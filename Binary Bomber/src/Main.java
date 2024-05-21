import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.nio.Buffer;
import java.security.Key;
public class Main extends JPanel implements KeyListener{
    public static int gameState = 0;
    // Game States
    // 0 - Start
    // 1 - High score/Credits
    // 2 - Gameplay Screen
    // 3 - Loss

    //Image variables
    //Image "gs0" responsible for all images
    public static BufferedImage gs0;




    //panel generating
    public Main(){
        setPreferredSize(new Dimension(400, 600));
        this.setFocusable(true);
        addKeyListener(this);
    }

    public static void main(String[] args) throws IOException{

        //image importation (needs to come before drawing panel)
        gs0 = ImageIO.read(new File("Background.png"));


        JFrame frame = new JFrame("Binary Bomber");
        JPanel panel = new Main();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();



    }

    public void paintComponent(Graphics g){
        if(gameState == 0){
            g.drawImage(gs0, 0, 0, null);
        }
        if(gameState == 1){
            g.drawImage(gs0, -400, 0, null);
        }
        //-400x for right side

        if(gameState == 2){
            g.drawImage(gs0, 0, 0, null);
        }
        //test what y value is needed to show city

    }

    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}


}
