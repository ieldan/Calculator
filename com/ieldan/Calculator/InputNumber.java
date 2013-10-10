package com.ieldan.Calculator;

import java.lang.StringBuilder;
import java.util.Stack;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *  Represents numerical input.
 */
public class InputNumber {
    /**
     *  Holds the string representation of the tokens in the InputToken queue.
     */
    private StringBuilder display = new StringBuilder();
    /**
     *  Queue holding all the tokens.
     */
    private Stack<InputToken> tokens = new Stack<InputToken>();
    /**
     *  Flag whose purpose is to signal if the decimal 
     *  separator is already present.
     */
    private boolean hasDot = false;
    
    /**
     *  Adds input.
     *  
     *  @param input InputToken representing input.
     *  @return Returns true if successful, false otherwise.
     */
    public boolean push(InputToken input) {
        if (input == InputToken.DECIMAL_SEPARATOR) {
            if (hasDot)
                return false;
            hasDot = true;   
        }
        
        tokens.push(input);
        appendTextToken(input);
        return true;
    }
    
    /**
     *  Checks if there is no input.
     *  
     *  @return Returns true if input is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tokens.empty();
    }
    
    /**
     *  Removes and returns the last input token.
     *  
     *  @return The last token. If no token available, returns null
     */
    public InputToken pop() {
        if (!isEmpty()) {
            InputToken popped = tokens.pop();
            
            if (popped == InputToken.DECIMAL_SEPARATOR) {
                hasDot = false;   
            }
            
            removeTextToken(popped);
            
            return popped;
        }
        return null;
    }
    
    /**
     *  Returns the current input value.
     *  
     *  @return The current input value.
     */
    public double getNumber() {
        try {
            NumberFormat format = NumberFormat.getInstance();
            Number number = format.parse(display.toString());
            return number.doubleValue();
        } catch (ParseException | NumberFormatException ex) {
            assert false : ex.getMessage();
            return 0.0;
        }
    }
    
    /**
     *  Returns the textual representation of the current input.
     *  
     *  @return The textual representation of the current input.
     */
    public String toString() {
        return display.toString();
    }
    
    /**
     *  Add the representation of a token to the text display.
     *  
     *  @param type The kind of token to add.
     */
    private void appendTextToken(InputToken type) {
        display.append(type.toString());   
    }
    
    /**
     *  Remove token representation from the text display.
     *  
     *  @param type The kind of token to remove.
     */
    private void removeTextToken(InputToken type) {
        int tokenSize = type.toString().length();
        display.delete(display.length() - tokenSize - 1, tokenSize + 1);
    }
    
    /**
     *  Character representing a locale sensitive decimal separator.
     */
    private static char decimalSeparator = ((DecimalFormat)(DecimalFormat.getInstance()))
                                             .getDecimalFormatSymbols()
                                             .getDecimalSeparator();
    
    /**
     *  Legal tokens for class InputNumber
     */
    public enum InputToken {
        DIGIT_0('0'), DIGIT_1('1'), DIGIT_2('2'), DIGIT_3('3'), DIGIT_4('4'), DIGIT_5('4'),
        DIGIT_6('6'), DIGIT_7('7'), DIGIT_8('8'), DIGIT_9('9'), 
        DECIMAL_SEPARATOR(InputNumber.decimalSeparator);
        
        /**
         *  String representation of the InputToken
         */
        final String rep;
        
        InputToken(String rep) {
            this.rep = rep;
        }
        InputToken(char rep) {
            this.rep = Character.toString(rep);
        }
        
        /**
         *  Returns a textual representation of InputToken.
         *
         *  @return String representing the InputToken
         */
        public String toString() {
            return rep;
        }
        
        /**
         *  Parse a number into an InputToken.
         *  
         *  @param digit Numeric representation of an InputToken
         *  @return InputToken
         *  @throws IndexOutOfBoundsException If digit is invalid
         */
        public static InputToken parse(int digit) {
            switch (digit) {
            case 0:
                return InputToken.DIGIT_0;
            case 1:
                return InputToken.DIGIT_1;
            case 2:
                return InputToken.DIGIT_2;
            case 3:
                return InputToken.DIGIT_3;
            case 4:
                return InputToken.DIGIT_4;
            case 5:
                return InputToken.DIGIT_5;
            case 6:
                return InputToken.DIGIT_6;
            case 7:
                return InputToken.DIGIT_7;
            case 8:
                return InputToken.DIGIT_8;
            case 9:
                return InputToken.DIGIT_9;
            default:
                throw new IndexOutOfBoundsException();
            }
        }
        
        /**
         *  Parse a string into an InputToken.
         *  
         *  @param rep Textual representation of an InputToken
         *  @return InputToken
         *  @throws IllegalArgumentException If rep is an invalid representation
         */
        public static InputToken parse(String rep) {
            if (rep.charAt(0) == InputNumber.decimalSeparator)
                return InputToken.DECIMAL_SEPARATOR;
            
            throw new IllegalArgumentException();
        } 
    }
}