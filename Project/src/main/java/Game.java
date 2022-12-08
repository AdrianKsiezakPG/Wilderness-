import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel{
    Labirynt labirynt =new Labirynt();
    Avatar avatar=new Avatar();
    public static int level=1;

    public  Game(){
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                avatar.keyPressed(e);
            }
        
             @Override
            public void keyReleased(KeyEvent e) {
            
            }
        
    });
        setFocusable(true);
    }

    public void paint(Graphics graphics){
        try {
            labirynt.paint(graphics);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            avatar.paint(graphics);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int changeLevel(){
        return level++;
    }

    public static int getLevel(){
        return level;
    }

    public static void main(String[]args){
	   JOptionPane.showMessageDialog(null, "Welcome to the game Wilderness! Let's start!");
       JFrame myWindow=new JFrame("Wilderness");
       Game game =new Game();
       myWindow.add(game);
       myWindow.setSize(920,540);
       myWindow.setLocation(300,200);
       myWindow.setVisible(true);
       myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       while (true){
           try {
               Thread.sleep(10);
        } catch (InterruptedException ex) {
             Logger.getLogger(Game.class.getName()).log(Level.SEVERE, "GAME OVER");
        }
        myWindow.repaint();   

        if(getLevel()>3){
            JOptionPane.showMessageDialog(null, "YOU WIN");
            System.exit(0);
            }
        }
    }
}



