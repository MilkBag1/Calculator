/**
 * Program Name: LongOperandException.java
 * Purpose: 	
 * @author		Matt Taylor
 * @version		1.0
 * @since		Apr 13, 2021
 */

public class LongOperandException extends Exception{
	
	public LongOperandException() {
		
		super("LongOperandException: Operand is too long for calculator. Exceeded 20 characters");
	}
}
//End of class