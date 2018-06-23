import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        MainWindow mw = new MainWindow();
        mw.Game();
    }
}
///