import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class MainWindow extends JFrame {
    JPanel p;
    String name = "Gamer";
    Image font;
    int map = 0;
    public MainWindow(){
        setTitle("Змейка");
        setSize(352, 374);
        setLocation(500, 300);
        p = new JPanel();
        add(p);
        p.setLayout(new BorderLayout());
    }
    public void Game(int gameMap){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        map = gameMap;
        GameField g = new GameField(name, map);
        g.setBounds(0,0,400,400);
        add(g);
        setVisible(true);
    }

    public void Map(JComboBox comboBox){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(200, 200);
        setLocation(600, 400);
        comboBox.setAlignmentX(CENTER_ALIGNMENT);
        JButton gameMap = new JButton("Start game");

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String item = (String)box.getSelectedItem();
                if (item == "First map") {
                    map = 1;
                }
                if (item == "Second map") {
                    map = 2;
                }
                if (item == "Third map") {
                    map = 3;
                }
                if(item == "Default map"){
                    map = 0;
                }
            }
        };

        ActionListener actionGame = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainWindow mw = new MainWindow();
                mw.Game(map);
            }
        };

        gameMap.addActionListener(actionGame);
        comboBox.addActionListener(actionListener);
        gameMap.setBounds(50,100,100,50);

        add(gameMap);
        add(comboBox);
        setVisible(true);
    }

    public void Table(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        int pos = 100;
        try {
            FileReader reader = new FileReader("Best.txt");
            Scanner scan = new Scanner(reader);
            scan.useDelimiter(" |\n");
            JLabel best = new JLabel("This is a best gamer's: ");
            best.setBounds(100,60,150,40);
            p.add(best,BorderLayout.CENTER);
            while (scan.hasNext()) {
                String Name = scan.next();
                String valStr = scan.next();
                JLabel name = new JLabel(Name + "    " + valStr);
                name.setBounds(150,pos,40,40);
                pos +=20;
                p.add(name, BorderLayout.CENTER);

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Menu(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel lab1 = new JLabel(" ");
        JButton startClassicMode = new JButton("Start classic mode");
        JButton selectMap = new JButton("Select map");
        JButton highscoreTable = new JButton("Highscore table");
        JButton settings = new JButton("Settings");

        String[] items = {
                "Default map",
                "First map",
                "Second map",
                "Third map"
        };
        JComboBox comboBox = new JComboBox(items);


        ActionListener actionClassicMod = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainWindow mw = new MainWindow();
                mw.Game(0);
            }
        };

        ActionListener actionSelectMap = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainWindow mw = new MainWindow();
                mw.Map(comboBox);
            }
        };

        ActionListener actionTable = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainWindow mw = new MainWindow();
                mw.Table();
            }
        };


        highscoreTable.setBounds(100,200,150,50);
        selectMap.setBounds(125,150,100,50);
        startClassicMode.setBounds(100, 100,150,50);
        settings.setBounds(125, 250,100,50);

        startClassicMode.addActionListener(actionClassicMod);
        selectMap.addActionListener(actionSelectMap);
        highscoreTable.addActionListener(actionTable);


        p.add(startClassicMode, BorderLayout.CENTER);
        p.add(selectMap, BorderLayout.CENTER);
        p.add(highscoreTable, BorderLayout.CENTER);
        p.add(settings, BorderLayout.CENTER);
        p.add(lab1, BorderLayout.CENTER);

        try {
            BufferedImage myPicture = ImageIO.read(new File("MenuBackground.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            p.add(picLabel, BorderLayout.WEST);
        }catch (IOException ex) {
            // handle exception...
        }
        setVisible(true);
    }

    public static void main(String[] args){
        MainWindow mw = new MainWindow();
        mw.Menu();
    }
}
