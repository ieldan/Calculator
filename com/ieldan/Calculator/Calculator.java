package com.ieldan.Calculator;

import java.text.DecimalFormat;
import com.ieldan.Calculator.Calculation;
import com.ieldan.Calculator.InputNumber;
import com.ieldan.Calculator.InputNumber.InputToken;

/**
 *  Implements the logic of a very simple calculator.
 */
public class Calculator {
    /**
     *  Object that performs calculations.
     */
    private Calculation calc;
    
    /**
     *  Object that keeps track of numerical input.
     */
    private InputNumber inputNumber;
    
    /**
     *  Keeps track of the next operation to be performed.
     */
    private Operation nextOperation;
    
    /**
     *  Keeps track of the operation that is going to be performed.
     */
    private Operation currOperation;
    
    /**
     *  Creates a Calculator object to work like a virtual calculator.
     */
    public Calculator() {
        reset();
    }
    
    /**
     *  Receive operation input.
     *
     *  @param type Operation to perform
     */
    public void operation(Operation type) {
        nextOperation = type;
        
        if (currOperation != null) {
            switch(currOperation) {
            case ADD:
                calc.add(inputNumber.getNumber());
                break;
            case SUB:
                calc.sub(inputNumber.getNumber());
                break;
            case MUL:
                calc.mul(inputNumber.getNumber());
                break;
            case DIV:
                calc.div(inputNumber.getNumber());
                break;
            case ANS:   
                /* To overcome the fact that this switch statement is fired for
                   binary operations, the ANS operation results in its second operand. */
                calc = new Calculation(inputNumber.getNumber());
                break;
            }
            currOperation = null;
        }
        
        inputNumber = new InputNumber();
    }
    
    /**
     *  Receive numerical input.
     *
     *  @param input Input
     */
    public void input(InputToken input) {
        if (inputNumber.push(input))
            currOperation = nextOperation;
    }
    
    /**
     *  Resets the calculator's state.
     */
    public void reset() {
        calc = new Calculation(0);
        currOperation = null;
        nextOperation = Operation.ADD;
        inputNumber = new InputNumber();
    }
    
    /**
     *  Returns the current input as a String object.
     *
     *  @return Current input
     */
    public String getInput() {
        return inputNumber.toString();
    }
    
    /**
     *  Returns the last calculation as a String object.
     *
     *  @return Last calculation
     */
    public String getCalculationResult() {
        return new DecimalFormat("0.#########").format(calc.getAnswer());
    }
    
    /**
     *  Enum that represents all valid operations.
     */
    public enum Operation {
        ADD("+"), SUB("-"), MUL("x"), DIV("/"), ANS("ans");
        
        /**
         *  String representation of Operation.
         */
        final String rep;

        Operation(String rep) {
            this.rep = rep;
        }
        
        /**
         *  Returns the representation of an Operation enum value.
         */
        public String toString() {
            return rep;
        }
        
        /**
         *  Parse a String object to an Operation type.
         *
         *  @param rep Textual representation of an operation.
         */
        public static Operation parse(String rep) {
            switch(rep) {
            case "+":
                return Operation.ADD;
            case "-":
                return Operation.SUB;
            case "x":
                return Operation.MUL;
            case "/":
                return Operation.DIV;
            case "ans":
                return Operation.ANS;
            default:
                throw new UnsupportedOperationException("Operation type '"
                                                        + rep 
                                                        + "' is unknown.");
            }
        }
    }
}