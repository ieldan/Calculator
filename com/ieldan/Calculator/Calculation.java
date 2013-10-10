package com.ieldan.Calculator;

/**
 *  Does arithmetic operations on a number.
 */
public class Calculation {
    /**
     *  Variable storing the number to be used to perform calculations on.
     */
    private double result;
    
    /**
     *  Does arithmetic operations on a number.
     *
     *  @param startOperand 
     */
    public Calculation(double startOperand) {
        result = startOperand;
    }
    
    /**
     *  Performs the addition operation.
     *
     *  @param n Number to add with
     */
    public void add(double n) {
        result += n;
    }
    
    /**
     *  Performs the subtraction operation.
     *
     *  @param n Number to subtract with
     */
    public void sub(double n) {
        result -= n;
    }
    
    /**
     *  Performs the multiplication operation.
     *
     *  @param n Number to multiply by
     */
    public void mul(double n) {
        result *= n;
    }
    
    /**
     *  Performs the division operation.
     *
     *  @param n Number to divide by
     */
    public void div(double n) {
        result /= n;
    }
    
    /**
     *  Returns the result of all calculations.
     *
     *  @return Result of all calculations
     */
    public double getAnswer() {
        return result;
    }
}