package demoudp;

import java.awt.Container;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainFrame extends JFrame
{
    public MainFrame()
    {
        super("MainWindow");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        mainPanel = new MainPanel();
        
        Container contentPane = getContentPane();
        contentPane.add(mainPanel);
        
        setBounds(100, 100, 400, 100);
        setVisible(true);
    }

    private MainPanel mainPanel;
}
  
