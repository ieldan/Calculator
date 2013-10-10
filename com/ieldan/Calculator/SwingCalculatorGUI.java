package com.ieldan.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.ieldan.Calculator.Calculator;
import com.ieldan.Calculator.Calculator.Operation;
import com.ieldan.Calculator.InputNumber.InputToken;

/**
 *  Contains factory methods to create swing components that control
 *  a virtual calculator.
 */
public class SwingCalculatorGUI {
    /**
     *  Creates and returns a JFrame object fit to contain all required  
     *  components for the virtual calculator. 
     *  Each call generates an independent state.
     *  Note: should only be called from the event-dispatching thread.
     *
     *  @return JFrame object with visibility set to false
     */
    public static JFrame makeWindow() {
        return new SwingCalculatorGUI().createJFrame();
    }
    
    /**
     *  Creates and returns a JPanel object fit to display all GUI components 
     *  of the virtual calculator.
     *  Each call generates an indepentent state.
     *  Note: should only be called from the event-dispatching thread.
     *
     *  @return A JPanel object
     */
    private static JPanel makeComponents() {
        return new SwingCalculatorGUI().createUIComponents();
    }
    
    /**
     *  Textfield that provides visual feedback of ongoing arithmetic
     *  operations.
     */
    private JTextField display;
    
    /**
     *  Object that controls the logic of the calculator.
     */
    private Calculator calculator;
    
    /**
     *  Private constructor. Only to be used to instantiate an Object
     *  that keeps track of the state of the virtual calculator.
     */
    private SwingCalculatorGUI() { }
    
    /**
     *  Creates a JFrame fit to display all GUI components of the virtual 
     *  calculator.
     *
     *  @return JFrame window with visibility set to false
     */
    private JFrame createJFrame() {        
        JFrame calculatorWindow = new JFrame("Calculator");
        JComponent contentPane = (JComponent)calculatorWindow.getContentPane();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        calculatorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel components = createUIComponents();
        
        contentPane.add(components);
        calculatorWindow.pack();
        calculatorWindow.setLocationRelativeTo(null);
        calculatorWindow.setResizable(false);
        
        return calculatorWindow;
    }
    
    /**
     *  Sets up all the components required to control the calculator
     *  and returns them added to a JPanel object.
     *
     *  @return JPanel object containing all the necessary controls
     */
    private JPanel createUIComponents() {
        JComponent answerDisplay = createAnswerDisplay();
        JComponent constrolsPanel = createControlsPanel();
        constrolsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        JPanel group = new JPanel();
        group.setLayout(new BoxLayout(group, BoxLayout.PAGE_AXIS));
        group.add(answerDisplay);
        group.add(constrolsPanel);
        
        return group;
    }
    
    /**
     *  Restarts the calculator.
     */
    private void reset() {
        calculator = new Calculator();
        displayResult();
    }
    
    /**
     *  Displays the result of the previous calculation.
     */
    private void displayResult() {
        display.setText(calculator.getCalculationResult());
    }
    
    /**
     *  Displays the current input value.
     */
    private void displayInput() {
        String input = calculator.getInput();
        display.setText((input != "") ? input : "0");
    }
    
    /**
     *  Creates and returns the swing component responsible for displaying
     *  input and airthmetic results.
     *
     *  @return JComponent object
     */
    private JComponent createAnswerDisplay() {
        display = new JTextField();
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                                             Integer.MAX_VALUE));
        
        reset();
        
        return display;
    }
    
    /**
     *  Creates and returns the swing component to send input to the
     *  virtual calculator.
     *  
     *  @return JComponent object
     */
    private JComponent createControlsPanel() {
        JPanel constrolsPanel = new JPanel(new GridLayout(4, 4));
        
        for (int i = 1; i <= 3; i++) {
            constrolsPanel.add(createDigitButton(InputToken.parse(i)));
        }
        constrolsPanel.add(createOperationButton(Operation.ADD));
        
        for (int i = 4; i <= 6; i++) {
            constrolsPanel.add(createDigitButton(InputToken.parse(i)));
        }
        constrolsPanel.add(createOperationButton(Operation.SUB));
        
        for (int i = 7; i <= 9; i++) {
            constrolsPanel.add(createDigitButton(InputToken.parse(i)));
        }
        constrolsPanel.add(createOperationButton(Operation.MUL));
        
        constrolsPanel.add(createDigitButton(InputToken.DECIMAL_SEPARATOR));
        constrolsPanel.add(createDigitButton(InputToken.DIGIT_0));
        constrolsPanel.add(createOperationButton(Operation.ANS));
        constrolsPanel.add(createOperationButton(Operation.DIV)); 
        
        return constrolsPanel;  
    }
    
    /**
     *  Creates and returns a swing button for numerical input.
     *
     *  @param input Valid input
     *  @return JButton object
     */
    private JButton createDigitButton(InputToken input) {
        class InputActionListener implements ActionListener {
            private InputToken inputToken;
            
            public InputActionListener(InputToken input) {
                this.inputToken = input;
            }
            
            @Override
            public void actionPerformed(ActionEvent event) {
                calculator.input(inputToken);
                displayInput();
            }
        }
        
        JButton jButton = new JButton(input.toString());
        jButton.addActionListener(new InputActionListener(input));
        
        return jButton;
    }
    
    /**
     *  Creates and returns a swing button to pick an operation.
     *
     *  @param type Valid operation type
     *  @return JButton object
     */
    private JButton createOperationButton(Operation type) {   
        class OperationActionListener implements ActionListener {
            private Operation operation;
            
            public OperationActionListener(Operation t) {
                this.operation = t;
            }

            @Override
            public void actionPerformed(ActionEvent event) {
                calculator.operation(operation);
                displayResult();
            }
        }
        
        JButton jButton = new JButton(type.toString());
        jButton.addActionListener(new OperationActionListener(type));
        
        return jButton;
    }
}
