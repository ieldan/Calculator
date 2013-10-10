import com.ieldan.Calculator.SwingCalculatorGUI;
import javax.swing.*;
import java.awt.*;

/**
 * Class used as the main entry point for this application when run as an 
 * applet.
 */
public class MainApplet extends JApplet {
    @Override
    public void init() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override 
            public void run() { 
                setFocusable(false);

                JComponent contentPane = (JComponent)getContentPane();
                contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
                SwingCalculatorGUI.makeWindow().setVisible(true);
                
                JPanel panel = new JPanel(new GridBagLayout());
                panel.add(new JLabel("The Calculator window has been instantiated.", JLabel.CENTER));
                panel.add(new JLabel("Please look for it.", JLabel.CENTER));

                contentPane.add(panel);
            }
        });
    }
}