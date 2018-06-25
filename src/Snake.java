import javax.swing.*;
import java.awt.*;

public class Snake {
    private Image headTop;
    private Image tail;
    private Image headLeft;
    private Image headRight;
    private Image headDown;
    private Image Bomb;
    private int dots;
    private final int ALL_DOTS = 400;
    private final int DOT_SIZE = 16;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private final int BombX;
    private int BombY;

    public Snake(int dots){
        this.dots = dots;
        for (int i = 0; i < this.dots; i++) {
            x[i] = 32 - (i * DOT_SIZE);
            y[i] = 0;
        }
        BombX = 32;
        BombY = 0;
        loadImages();
    }



    public void loadImages(){
        ImageIcon top = new ImageIcon("HeadTop.png");
        ImageIcon down = new ImageIcon("HeadDown.png");
        ImageIcon right = new ImageIcon("HeadRight.png");
        ImageIcon left = new ImageIcon("HeadLeft.png");
        ImageIcon T = new ImageIcon("Tail.png");
        ImageIcon bomb = new ImageIcon("editbomb.png");

        headDown = down.getImage();
        headLeft = left.getImage();
        headRight = right.getImage();
        headTop = top.getImage();
        tail = T.getImage();
        Bomb = bomb.getImage();

    }

    public int getBombX(){
        return BombX;
    }
    public int getBombY(){
        return BombY;
    }
    public void setBombY(int num){
        BombY = num;
    }

    public int getDots(){
        return dots;
    }

    public int getX(int pos){
        return x[pos];
    }

    public int getY(int pos){
        return y[pos];
    }
    public void addDots(){
        dots++;
    }
    public void decDots(){
        dots--;
    }
    public Image getHeadTop(){
        return headTop;
    }
    public Image getHeadLeft(){
        return headLeft;
    }
    public Image getHeadRight(){
        return headRight;
    }
    public Image getHeadDown(){
        return headDown;
    }
    public Image getBomb(){
        return Bomb;
    }
    public void setX(int pos, int num){
        x[pos] = num;
    }
    public void setY(int pos, int num){
        y[pos] = num;
    }
    public int getDOT_SIZE(){
        return DOT_SIZE;
    }

    public Image getTail(){
        return tail;
    }

}
