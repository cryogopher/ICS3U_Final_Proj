import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.nio.Buffer;
import java.security.Key;
public class Main extends JPanel implements KeyListener{
    // Game States
    // 0 - Start
    // 1 - High score/Credits
    // 2 - Gameplay Screen
    // 3 - Loss


    //panel generating
    public Main(){
        setPreferredSize(new Dimension(400, 600));
        this.setFocusable(true);
        addKeyListener(this);
    }

    public static void main(String[] args) throws IOException{
        JFrame frame = new JFrame("Binary Bomber");
        JPanel panel = new Main();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
        

    }

    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}


}
