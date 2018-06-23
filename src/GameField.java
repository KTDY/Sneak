import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;


public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private int [] best = new int[5000];
    private Image myBackGround;
    private Image apple;
    private Image PoisonApple;
    private Timer timer;
    private Snake snake;
    private int applePX;
    private int applePY;
    private int appleX;
    private int appleY;
    private int ActionPoison;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private int eatApples = 0;
    private boolean downBomb = true;


    public GameField(){
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void initGame(){
        snake = new Snake(3);
        timer = new Timer(250, this);
        timer.start();
        createApple();
        createPoisonApple();
    }


    public void deleteApple(){
        applePX = 400;
        applePY = 400;
    }


    public void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }

    public void createPoisonApple(){
        applePX = new Random().nextInt(20)*DOT_SIZE;
        applePY = new Random().nextInt(20)*DOT_SIZE;
    }

    public void loadImages(){
        ImageIcon BackGround = new ImageIcon("Background.jpg");
        myBackGround = BackGround.getImage();
        ImageIcon iia = new ImageIcon("Apple.png");
        apple = iia.getImage();
        ImageIcon iipa = new ImageIcon("Apple.png");
        PoisonApple = iipa.getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    public void checkCollisions() {
        for (int i = snake.getDots(); i > 0 ; i--) {
            if(i > 4 && snake.getX(0) == snake.getX(i) && snake.getY(0) == snake.getY(i)){ //голова пришла в хвост
                inGame = false;
            }
            //проверка не коснулись ли тела
            for (int j = 1; j < snake.getDots(); j++){
                if(i > 4 && snake.getX(0) == snake.getX(j) && snake.getY(0) == snake.getY(j)){
                    inGame = false;
                }
            }
        }
        if((snake.getX(0)> SIZE) || (snake.getX(0)<0) || (snake.getY(0)> SIZE) || (snake.getY(0)<0)){
            inGame = false;
        }
        if (snake.getDots() == 0){
            inGame = false;
        }
        for (int i = snake.getDots(); i > 0; i--) {
            if((snake.getX(i)== snake.getBombX()) && snake.getBombY() == snake.getY(i)){
                snake.decDots();
                //break;
            }
        }
    }

    public void checkApple() {
        if(snake.getX(0) == appleX && snake.getY(0) == appleY){ //если голова пришла к яблоку
            snake.addDots();
            eatApples++;
            createApple();
            ActionPoison = new Random().nextInt(1000);
            deleteApple();
            if (ActionPoison < 200){
                createPoisonApple();
            }
        }
        if(snake.getX(0) == applePX && snake.getY(0) == applePY){ //если голова пришла к ядовитому яблоку
            snake.decDots();
            if(eatApples > 0) {
                eatApples--;
            }
            createPoisonApple();
            ActionPoison = new Random().nextInt(1000);
            if (ActionPoison < 400){
                createApple();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myBackGround,0,0,this); //выводим фон
        if (inGame){
            g.drawImage(apple, appleX, appleY, this);
            g.drawImage(PoisonApple, applePX, applePY, this);
            for (int i = 0; i < snake.getDots(); i++) {
                if(left){
                    if(i == 0){
                        g.drawImage(snake.getHeadLeft(), snake.getX(i),snake.getY(i),this);
                    }
                    else {
                        g.drawImage(snake.getTail(), snake.getX(i), snake.getY(i), this);
                    }
                }
                if(right) {
                    if (i == 0) {
                        g.drawImage(snake.getHeadRight(), snake.getX(i), snake.getY(i), this);
                    } else {
                        g.drawImage(snake.getTail(), snake.getX(i), snake.getY(i), this);
                    }
                }
                if(up) {
                    if (i == 0) {
                        g.drawImage(snake.getHeadTop(), snake.getX(i), snake.getY(i), this);
                    } else {
                        g.drawImage(snake.getTail(), snake.getX(i), snake.getY(i), this);
                    }
                }
                if(down) {
                    if (i == 0) {
                        g.drawImage(snake.getHeadDown(), snake.getX(i), snake.getY(i), this);
                    } else {
                        g.drawImage(snake.getTail(), snake.getX(i), snake.getY(i), this);
                    }
                }
                bestInFile();
                //g.drawImage(snake.getDot(), snake.getX(i),snake.getY(i), this);
            }
            g.drawImage(snake.getBomb(), snake.getBombX(), snake.getBombY(), this);
        }else{

            String str = "Game Over";
            String str2 = "Your score is: " + eatApples;
            g.setColor(Color.RED);
            g.drawString(str, 125, SIZE/2);
            g.drawString(str2,125,SIZE/2 + 15);
        }
    }

    private void move() {
        for (int i = snake.getDots(); i > 0 ; i--) {
            snake.setX(i,snake.getX(i-1));
            snake.setY(i,snake.getY(i-1));
        }
        if(left){
            snake.setX(0, snake.getX(0) - snake.getDOT_SIZE());
        }
        if(right){
            snake.setX(0, snake.getX(0) + snake.getDOT_SIZE());
        }
        if(up){
            snake.setY(0, snake.getY(0) - snake.getDOT_SIZE());
        }
        if(down){
            snake.setY(0, snake.getY(0) + snake.getDOT_SIZE());
        }
        if(downBomb){
            snake.setBombY(snake.getBombY() + snake.getDOT_SIZE());
            if(snake.getBombY() == 20*snake.getDOT_SIZE()){
                snake.setBombY(0);
            }
        }
    }


    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down){
                left = false;
                up = true;
                right = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                left = false;
                down = true;
                right = false;
            }
        }
    }
    public void bestInFile(){
        try(FileReader reader = new FileReader("Best.txt"))
        {
            int i = 0;
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){

                best[i] = c;
                i++;
            }
            for(int j = 0;j<best.length;j++) {
                if (best[j] < eatApples + 48){
                    best[j] = eatApples + 48;
                }
            }
        }

        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


        try(FileWriter writer = new FileWriter("Best.txt"))
        {
            // запись всей строки

            //String res = String.valueOf(best[0]);

            writer.write(best[0]);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
