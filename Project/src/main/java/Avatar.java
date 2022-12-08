import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Avatar {
    Labirynt lab=new Labirynt();
    int x=40;
    int y=40;
    int width=40;
    int height=40;
    int movement=40;
    Image boy;

    public void paint(Graphics graphics) throws IOException{
        boy = ImageIO.read(new File("Resources/avatar1.jpg"));
        graphics.drawImage(boy, x,y,width, height, null);
    }

    public void keyPressed(KeyEvent event){
        int[][]maze=lab.getMaze();

        //Przesuń w lewo
        if(event.getKeyCode()==37){
            if(maze[y/40][(x/40)-1] !=1){
                x=x-movement;
            }
        }
        //Przesuń w prawo
        if(event.getKeyCode()==39){
            if(maze[y/40][(x/40)+1]!=1){
                x=x+movement;
            }
        }
        //Przesun w dół
        if(event.getKeyCode()==40){
            if(maze[(y/40)+1][x/40]!=1){
                y=y+movement;
            }
        }
        //Przesun w górę
        if(event.getKeyCode()==38){
            if(maze[(y/40)-1][x/40]!=1){
                y=y-movement;
            }
        }
        if(x==840 && y==440){
            Game.changeLevel();
            x=40;
            y=40;
        }
    }
}   
