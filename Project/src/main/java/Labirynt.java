
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Labirynt {
    int[][]lab=new int[13][23];
    int row=0;
    int col=0;
    int rownumber=13;
    int colnumber=23;
    int blockwidth=40;
    int blockheight=40;
    Image enigma, block, grass;

    public void paint(Graphics graphics) throws IOException{
        int [][]maze=getMaze();
        block = ImageIO.read(new File("Resources/blok.png"));
        enigma = ImageIO.read(new File("Resources/zagadka.png"));
        grass = ImageIO.read(new File("Resources/trawa.png"));
        
        for(row=0;row<rownumber;row++){
            for(col=0;col<colnumber;col++){
                if(maze[row][col]==1){
                    if(Game.getLevel()==1) {
                        graphics.setColor(Color.darkGray);
                    }
                    if(Game.getLevel()==2){graphics.setColor(Color.green);}
                    if(Game.getLevel()>=3){graphics.setColor(Color.blue);}

                    graphics.drawImage(block, col*40, row*40, blockwidth, blockheight, null);
                } else if(maze[row][col]==2) {
                    graphics.drawImage(enigma, col*40, row*40, blockwidth, blockheight, null);
                } else {
                    graphics.drawImage(grass, col*40, row*40, blockwidth, blockheight, null);
                }
            }
        }
        graphics.drawString("Start", 5, 62);
        graphics.drawString("End", 850, 462);
    }
    
    public int [][] getMaze(){
        if(Game.getLevel()==1){

            //Level 1
            int maze[][]=
            {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            { 1,0,0,1,0,0,2,0,1,0,0,0,1,0,1,0,0,0,0,0,0,1,1},
            { 1,1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,1,1},
            { 1,1,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,1,1},
            { 1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,0,1,1,0,1,0,1,1},
            { 1,1,0,0,1,0,1,0,1,1,1,0,1,0,1,0,0,0,0,1,0,1,1},
            { 1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1,0,1,1,1,1},
            { 1,1,0,1,0,0,0,0,1,0,0,0,1,0,0,1,1,1,0,1,0,1,1},
            { 1,1,0,1,0,1,1,1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1},
            { 1,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,2,1,0,1,1},
            { 1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1},
            { 1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1},
            { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
            
            lab=maze;
        }
        
        if(Game.getLevel()==2){
            
            //Level 2
            int maze[][]=
            {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            { 1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
            { 1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1},
            { 1,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1,1},
            { 1,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1},
            { 1,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,1},
            { 1,1,1,0,1,0,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1},
            { 1,1,0,0,1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1,1},
            { 1,1,0,1,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,1},
            { 1,1,0,1,0,0,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0,1,1},
            { 1,1,0,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1},
            { 1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,1},
            { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
            
            lab=maze;
        }
        
        if(Game.getLevel()==3){
            
            //Level 3
            int maze [][]=
            {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            { 1,0,0,0,1,0,0,0,0,0,0,1,1,1,0,0,1,1,3,0,0,1,1},
            { 1,1,1,0,1,0,1,0,1,1,1,0,0,0,1,0,0,0,1,1,0,1,1},
            { 1,1,0,0,0,0,1,0,0,1,0,0,1,0,1,0,1,0,0,1,0,1,1},
            { 1,1,0,1,1,1,1,0,1,0,0,1,1,0,1,0,0,1,0,1,0,1,1},
            { 1,1,0,0,0,0,1,0,0,0,1,1,1,0,1,0,1,0,0,1,0,1,1},
            { 1,1,1,0,1,0,1,1,1,1,0,0,0,0,1,0,0,1,0,1,0,1,1},
            { 1,1,0,0,0,1,0,0,0,1,0,1,1,1,0,0,1,0,0,1,0,1,1},
            { 1,1,0,1,1,0,0,1,0,1,0,0,0,0,0,1,0,0,1,1,0,1,1},
            { 1,1,0,1,0,0,1,1,0,1,0,1,1,1,1,0,0,1,0,0,0,1,1},
            { 1,1,0,0,0,1,1,1,0,1,0,0,0,0,0,1,0,0,0,1,0,1,1},
            { 1,1,0,1,0,0,0,1,0,0,0,1,1,1,1,0,0,1,0,1,0,0,1},
            { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
            
            lab=maze;
        }
        return lab;
    }
}
