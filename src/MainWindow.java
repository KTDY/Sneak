import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class MainWindow extends JFrame {
    JPanel p;
    String name = "Gamer";
    public MainWindow(){
        setTitle("Змейка");
        setSize(352, 374);
        setLocation(500, 300);
        p = new JPanel();
        add(p);
        p.setLayout(new BorderLayout());
    }
    public void Game(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        GameField g = new GameField(name);
        g.setBounds(0,0,400,400);
        add(g);
        setVisible(true);
    }

    public void Menu(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel lab1 = new JLabel(" ");
        JButton button = new JButton("New Game");
        JButton about = new JButton("About");
        JButton t = new JButton("Test");

        ActionListener actionListener = new TestActionListener();
        button.addActionListener(actionListener);

        t.setBounds(125,200,85,50);
        about.setBounds(125,150,85,50);
        button.setBounds(125, 100,85,50);


        p.add(button, BorderLayout.CENTER);
        p.add(about, BorderLayout.CENTER);
        p.add(t, BorderLayout.CENTER);
        p.add(lab1, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args){
        MainWindow mw = new MainWindow();
        mw.Menu();
    }
}
