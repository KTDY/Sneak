import javax.swing.*;
import java.awt.*;

class Snake {
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
    private int BombX;
    private int BombY;
    private int BombX2;
    private int BombY2;

    Snake(int dots){
        this.dots = dots;
        for (int i = 0; i < this.dots; i++) {
            x[i] = 32 - (i * DOT_SIZE);
            y[i] = 0;
        }
        BombX = 32;
        BombY = 32;
        BombX2 = 320;
        BombY2 = 32;
        loadImages();
    }



    private void loadImages(){
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

    int getBombX(){
        return BombX;
    }
    int getBombY(){
        return BombY;
    }
    void setBombY(int num){
        BombY = num;
    }
    void setBombX(int num){
        BombX = num;
    }

    int getBombX2(){
        return BombX2;
    }
    int getBombY2(){
        return BombY2;
    }
    void setBombY2(int num){
        BombY2 = num;
    }
    void setBombX2(int num){
        BombX2 = num;
    }

    int getDots(){
        return dots;
    }

    int getX(int pos){
        return x[pos];
    }

    int getY(int pos){
        return y[pos];
    }
    void addDots(){
        dots++;
    }
    void decDots(){
        dots--;
    }
    Image getHeadTop(){
        return headTop;
    }
    Image getHeadLeft(){
        return headLeft;
    }
    Image getHeadRight(){
        return headRight;
    }
    Image getHeadDown(){
        return headDown;
    }
    Image getBomb(){
        return Bomb;
    }
    void setX(int pos, int num){
        x[pos] = num;
    }
    void setY(int pos, int num){
        y[pos] = num;
    }
    int getDOT_SIZE(){
        return DOT_SIZE;
    }

    Image getTail(){
        return tail;
    }

}
