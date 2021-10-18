/**
 * Program Name: ArithmeticException.java
 * Purpose: 	
 * @author		Matt Taylor
 * @version		1.0
 * @since		Apr 13, 2021
 */

public class ArithmeticException extends Exception{
	
	public ArithmeticException() {
		super("ArithmeticException: You cannot divide by zero");
		//super("ArithmeticException: Division by zero is illegal");
		//@Janice, take your pick
	}
}
//End of class