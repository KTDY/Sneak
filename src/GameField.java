import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private String name;
    private Map map;
    private String [] bestName = new String[5000];
    private String [] bestVal = new String[5000];
    private int topPersons = 0;
    private int topPersonsI = 10;
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
    private char Up,Down,Left,Right;
    private int gameMap = 0;


<<<<<<< HEAD
    public GameField(String name, int map, int complexity){
=======
    GameField(String name, int map){
>>>>>>> 08c54748154010240202bfd85d817863975c2406
        loadImages();
        initGame(name, map, complexity);
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

<<<<<<< HEAD
    public void initGame(String name, int map, int complexity){
=======
    private void initGame(String name, int map){
>>>>>>> 08c54748154010240202bfd85d817863975c2406
        this.name = name;
        gameMap = map;
        snake = new Snake(3);
        if(complexity == 0){
            timer = new Timer(250, this);
        }
        if(complexity == 1){
            timer = new Timer(150, this);
        }
        if(complexity == 2){
            timer = new Timer(30, this);
        }
        timer.start();
        createApple();
        createPoisonApple();
        bestInFile();
    }


    private void deleteApple(){
        applePX = 400;
        applePY = 400;
    }


    private void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }

    private void createPoisonApple(){
        applePX = new Random().nextInt(20)*DOT_SIZE;
        applePY = new Random().nextInt(20)*DOT_SIZE;
    }

    private void loadImages(){
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

    private void checkCollisions() {
        for (int i = snake.getDots(); i > 0 ; i--) {
            if(i > 4 && snake.getX(0) == snake.getX(i) && snake.getY(0) == snake.getY(i)){ //голова пришла в хвост
                inGame = false;
                checkOnBest(name, eatApples);
            }
            //проверка не коснулись ли тела
            for (int j = 1; j < snake.getDots(); j++){
                if(i > 4 && snake.getX(0) == snake.getX(j) && snake.getY(0) == snake.getY(j)){
                    inGame = false;
                    checkOnBest(name, eatApples);
                }
            }
        }
        if((snake.getX(0)> SIZE) || (snake.getX(0)<0) || (snake.getY(0)> SIZE) || (snake.getY(0)<0)){
            inGame = false;
            checkOnBest(name, eatApples);
        }
        if (snake.getDots() == 0){
            inGame = false;
            checkOnBest(name, eatApples);
        }
        for (int i = snake.getDots(); i > 0; i--) {
            if((snake.getX(i)== snake.getBombX()) && snake.getBombY() == snake.getY(i)){
                snake.decDots();
                //break;
            }
        }
        if(gameMap > 0) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < snake.getDots(); j++) {
                    if (snake.getX(j) == map.getMas(i) && snake.getY(j) == map.getPosY(i)) {
                        inGame = false;
                        checkOnBest(name, eatApples);
                    }
                    if (snake.getX(j) == map.getMas(i) && snake.getY(j) == map.getPosY2()) {
                        inGame = false;
                        checkOnBest(name, eatApples);
                    }
                }
            }
        }
        if(gameMap > 2) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < snake.getDots(); j++) {
                    if (snake.getX(j) == map.getPosX() && snake.getY(j) == map.getMasY(i)) {
                        inGame = false;
                        checkOnBest(name, eatApples);
                    }
                    if (snake.getX(j) == map.getPosX2() && snake.getY(j) == map.getMasY(i)) {
                        inGame = false;
                        checkOnBest(name, eatApples);
                    }
                }
            }
        }
    }

    private void checkApple() {
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
        map = new Map(gameMap);
        g.drawImage(myBackGround,0,0,this); //выводим фон
        if (inGame){
            if(gameMap > 0) {
                for (int i = 0; i < 10; i++) {
                    g.drawImage(snake.getBomb(), map.getMas(i), map.getPosY(i), this);
                    if (map.getMas(i) == appleX && map.getPosY(i) == appleY) {
                        createApple();
                    }
                    if (map.getMas(i) == applePX && map.getPosY(i) == applePY) {
                        createPoisonApple();
                    }
                }
                if(gameMap > 1) {
                    for (int i = 0; i < 10; i++) {
                        g.drawImage(snake.getBomb(), map.getMas(i), map.getPosY2(), this);
                        if (map.getMas(i) == appleX && map.getPosY2() == appleY) {
                            createApple();
                        }
                        if (map.getMas(i) == applePX && map.getPosY2() == applePY) {
                            createPoisonApple();
                        }
                    }
                    if(gameMap > 2){
                        for (int i = 0; i < 10; i++) {
                            g.drawImage(snake.getBomb(), map.getPosX(), map.getMasY(i), this);
                            if (map.getMasY(i) == appleX && map.getPosX() == appleY) {
                                createApple();
                            }
                            if (map.getMasY(i) == applePX && map.getPosX() == applePY) {
                                createPoisonApple();
                            }
                        }
                        for (int i = 0; i < 10; i++) {
                            g.drawImage(snake.getBomb(), map.getPosX2(), map.getMasY(i), this);
                            if (map.getMasY(i) == appleX && map.getPosX2() == appleY) {
                                createApple();
                            }
                            if (map.getMasY(i) == applePX && map.getPosX2() == applePY) {
                                createPoisonApple();
                            }
                        }
                    }
                }
            }

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
                //g.drawImage(snake.getDot(), snake.getX(i),snake.getY(i), this);
            }
            g.drawImage(snake.getBomb(), snake.getBombX(), snake.getBombY(), this);
            g.drawImage(snake.getBomb(), snake.getBombX2(), snake.getBombY2(), this);
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
            snake.setBombX(snake.getBombX() + snake.getDOT_SIZE());
            if(snake.getBombY() == 20*snake.getDOT_SIZE()){
                snake.setBombY(0);
                snake.setBombX(0);
            }
            snake.setBombY2(snake.getBombY2() + snake.getDOT_SIZE());
            snake.setBombX2(snake.getBombX2() - snake.getDOT_SIZE());
            if(snake.getBombY2() == 20*snake.getDOT_SIZE()){
                snake.setBombY2(0);
                snake.setBombX2(320);
            }
        }
    }


    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyChar();
            if(key == Left && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == Right && !left){
                right = true;
                up = false;
                down = false;
            }
            if(key == Up && !down){
                left = false;
                up = true;
                right = false;
            }
            if(key == Down && !up){
                left = false;
                down = true;
                right = false;
            }
        }
    }
    private void bestInFile(){
        int i = 0;
        try {
            FileReader reader = new FileReader("Best.txt");
            FileReader combination = new FileReader("Combination.txt");
            Scanner scan = new Scanner(reader);
            Scanner comb = new Scanner(combination);
            scan.useDelimiter(" |\n");
            comb.useDelimiter(" |\n");

            while (scan.hasNext()) { //устанавливаем имена и их рекоды в массивы
                String Name = scan.next();
                String valStr = scan.next();
                bestName[topPersons] = Name;
                bestVal[topPersons] = valStr;
                topPersons++;
            }

            while (comb.hasNext()) { //устанавливаем клавиши для управления
                String Name = comb.next();
                String valStr = comb.next();
                if (i == 3) {
                    Right = valStr.charAt(0);
                    i++;
                }
                if (i == 2) {
                    Left = valStr.charAt(0);
                    i++;
                }
                if (i == 1) {
                    Down = valStr.charAt(0);
                    i++;
                }
                if (i == 0) {
                    Up = valStr.charAt(0);
                    i++;
                }
            }

            combination.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void checkOnBest(String name, int num){
        boolean change = false;
        for(int i = 0; i < topPersons; i++){
            int val = Integer.parseInt(bestVal[i]);
            if(val <= num){
                /*
                bestVal[i] = String.valueOf(num);
                bestName[i] = name;
                change = true;
                System.out.println("We change");
                */
                sorted(num, i, name);
                change = true;
                System.out.println("We change");
                break;
            }
        }
        if(change){
            writeFile();
        }
    }

    private void sorted(int num, int index, String name) {
        String number = String.valueOf(num);
        boolean change = false;
        for(int i = index; i < topPersons; i++){
            bestName[i+1] = bestName[i];
            bestVal[i+1] = bestVal[i];

            if(!change) {
                bestVal[i] = number;
                bestName[i] = name;
                change = true;
                i++;
            }

        }
    }

    private void writeFile(){
        try {
            FileWriter writer = new FileWriter("Best.txt");
            for(int i = 0; i < topPersons; i++) {
                writer.write(bestName[i]);
                writer.write(" ");
                writer.write(bestVal[i]);
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

