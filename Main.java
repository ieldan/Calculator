import com.ieldan.Calculator.SwingCalculatorGUI;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Class that deals with the startup of this application.
 */
public class Main {
    /**
     * Starting point of this application.
     */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override 
            public void run() {
                SwingCalculatorGUI.makeWindow().setVisible(true);
            }
        });
    }
}