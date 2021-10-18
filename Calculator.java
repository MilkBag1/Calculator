/**
 * Program Name: Calculator.java
 * Purpose: 	TO handle the logic of a calculator object
 * @author		Matthew Taylor
 * @version		1.0
 * @since		Apr 6, 2021
 */

import java.util.ArrayList;

public class Calculator {
	
	//declare variables
	private String operand;
	private String operator;
	
	private boolean decimalPressed;
	//flag for the toggle plus minus
	private boolean flag;
	
	private ArrayList<String> list;
	
	/**
	 * Constructs a new Calculator object
	 */
	public Calculator() {
		
		this.list = new ArrayList<String>();
		
		this.operand = "";
		this.operator = "";
		this.decimalPressed = false;
		this.flag = false;
	}

	/**
	 * gets the operand of this object 
	 * @return the operand
	 */
	public String getOperand() {
		return this.operand;
	}

	/**
	 * gets the operator of this object 
	 * @return the operator
	 */
	public String getOperator() {
		return this.operator;
	}

	/**
	 * gets the decimalPressed of this object 
	 * @return the decimalPressed
	 */
	public boolean isDecimalPressed() {
		return this.decimalPressed;
	}
	
	/**
	 * gets the flag of this object 
	 * @return the operand
	 */
	public boolean getFlag() {
		return this.flag;
	}
	
	/**
	 * Sets the operand of this object
	 * @param operand the operand to set
	 */
	public void setOperand(String operand) {
		this.operand = operand;
	}

	/**
	 * Sets the operator of this object
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	/**
	 * Sets the flag of this object
	 * @param flag the flag to set
	 */
	public void setOperator(Boolean flag) {
		this.flag = flag;
	}

	/**
	 * Sets the decimalPressed of this object
	 * @param decimalPressed the decimalPressed to set
	 */
	public void setDecimalPressed(boolean decimalPressed) {
		this.decimalPressed = decimalPressed;
	}
	
	/**
	 * Resets all variables to their default values
	 */
	public void clear() {
		this.operand = "";
		this.operator = "";
		this.decimalPressed = false;
		this.flag = false;
		this.list.clear();
	}
	
	/**
	 * Removes the last character from an operand and returns the updated string
	 * @param value 
	 * @return value after removal of last character
	 * @throws EmptyOperandException
	 */
	public String backspace(String value) throws EmptyOperandException {
		
		if(value.equals("")) {
			throw new EmptyOperandException();
		} else {
			value = value.substring(0, value.length() - 1);
		}

		return value;
	}
	
	/**
	 * Converts value into a percent
	 * @param value
	 * @return value in percent format
	 * @throws EmptyOperandException
	 */
	public String findPercentage(String value) throws EmptyOperandException {
		
		double percent = 0.0;
		
		if(value.equals("")) {
			throw new EmptyOperandException();
		} else {
			//parse string and convert it into decimal by dividing by 100
			percent = Double.parseDouble(value) / 100.00;
			value = Double.toString(percent);
		}

		return value;
	}
	
	/**
	 * Will either add or delete a minus sign from the current operand
	 * Will not add a sign if operand exceeds 20 characters
	 * @param flag
	 * @return The modified operand
	 * @throws EmptyOperandException
	 * @throws LongOperandException
	 */
	public String togglePlusMinus(Boolean flag) throws EmptyOperandException, LongOperandException {
		
		if(operand.length() < 20) {
			if(this.operand.equals("")) {
				throw new EmptyOperandException();
			}
			
			if(flag) {
				this.operand = this.operand.substring(1);
				this.flag = false;
			} else {
				this.operand = "-" + this.operand;
				this.flag = true;
			}
		} else {
			throw new LongOperandException();
		}
		
		return this.operand;
	}
	
	/**
	 * Squares a value
	 * @param value to be squared
 	* @return value squared
 	* @throws EmptyOperandException
 	*/
	public String findSquared(String value) throws EmptyOperandException {
	
		double square = 0.0;
		
		if(value.equals("")) {
			throw new EmptyOperandException();
		} else {
			square = Double.parseDouble(value);
			square*=square;
			value = Double.toString(square);
		}

		return value;
	}

	/**
	 * Return the square root of a value
	 * @param value to be square rooted
	 * @return square root of value
	 * @throws EmptyOperandException
	 */
	public String findSquareRoot(String value) throws EmptyOperandException {
	
		double squareRoot = 0.0;
	
		if(value.equals("")) {
			throw new EmptyOperandException();
		} else {
			squareRoot = Double.parseDouble(value);
			squareRoot = Math.sqrt(squareRoot);
			value = Double.toString(squareRoot);
		}
		return value;
	}
	
	/**
	 * Concatenates value on the end of the operand
	 * @param value to be added to operand
	 */
	public void buildOperand(String value) throws LongOperandException{

		if(this.operand.length() < 20) {
			this.operand+=value;
				
		} else {
				throw new LongOperandException();
		}
	}
	
	/**
	 * Adds both the operand and the operator to the list one after the other. Then clears 
	 * the operand, operator, and and flags
	 * @param value
	 * @throws EmptyOperandException
	 */
	public void buildExpression(String value) throws EmptyOperandException {
		
		if(this.operand.equals("")) {
			throw new EmptyOperandException();
		} else {
			//add operand to list followed by the operator
			this.list.add(this.operand);
			this.operator = value;
			this.list.add(this.operator);

			//clear the operand and operator
			this.operand = "";
			this.operator = "";
			this.flag = false;
			
		}
	}
	
	/**
	 * Converts String value into an integer
	 * @param value
	 * @return value converted into an integer
	 */
	public int round(String value) {
		int rounded = 0;
		
		double toRound = Double.parseDouble(value);
		
		rounded = (int)(toRound + 0.5) * 1 / 1;
		
		return rounded;
	}
	
	/**
	 * Reverses value and returns the new string
	 * @param value
	 * @return value reversed
	 */
	public String bigOlFlipper(String value) {
		String flipped = "";
		
		for(int i = 0; i < value.length(); i++) {
			flipped+=value.charAt(value.length() - 1 - i);
		}
		
		return flipped;
	}
	
	/** 
	 * Converts operand to hexadecimal and returns String
	 * @return String hex
	 * @throws LongOperandException 
	 */
	public String convertHex() throws LongOperandException {
		String hex = "";
		int convert = 0;
		
		convert = round(this.operand);
		
		while(convert > 0) {
			
			switch(convert%16) {
			case 0:
			case 1:
			case 2: 
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9: 
				hex+=convert%16;
				break;
			case 10: 
				hex+="A";
				break;
			case 11: 
				hex+="B";
				break;
			case 12: 
				hex+="C";
				break;
			case 13: 
				hex+="D";
				break;
			case 14: 
				hex+="E";
				break;
			case 15: 
				hex+="F";
				break;
			}
			
			convert/=16;
		}
		
		hex = bigOlFlipper(hex);
		
		if(hex.length() > 20) {
			throw(new LongOperandException());
		}
		
		return hex;
	}
		
	/** Converts operand to octal and returns String
	 * 
	 * @return String oct
	 * @throws LongOperandException 
	 */
	public String convertOct() throws LongOperandException {
		String oct = "";
		int convert = 0;
		
		convert = round(this.operand);
		
		while(convert > 0) {
			oct+=convert%8;
			convert/=8;
		}
		
		oct = bigOlFlipper(oct);
		
		if(oct.length() > 20) {
			throw(new LongOperandException());
		}
		
		return oct;
	}
		
	/**	Converts operand to binary and returns String
	 * 
	 * @return String bin
	 * @throws LongOperandException 
	 */
	public String convertBin() throws LongOperandException {
		String bin = "";
		int convert = 0;
		
		convert = round(this.operand);
		
		while(convert > 0) {
			bin+=convert%2;
			convert/=2;
		}
		
		bin = bigOlFlipper(bin);
		
		if(bin.length() > 20) {
			throw(new LongOperandException());
		}
		
		return bin;
	}
	
	/**
	 * Will evaluate the expression stored in the list.
	 * Follows BEDMAS operations order
	 * clears all variables once completed
	 * @return answer in a double format
	 * @throws EmptyOperandException
	 * @throws ArithmeticException
	 */
	public double calculate() throws EmptyOperandException, ArithmeticException{
		double answer = 0.0;
		String temp = "";
		
			if(this.operand == "") {
				throw new EmptyOperandException();
			} else {
				this.list.add(this.operand);
			}

			//multiply/divide
			for(int m = 0; m < this.list.size(); m++) {
					
				if(this.list.get(m).equals("x")) {
						//parse the data into a string
					temp = Double.toString(
							//parse the data into a double to perform the operation
						Double.parseDouble(this.list.get(m - 1)) * 
						Double.parseDouble(this.list.get(m + 1))
						);
					//set current index to temp value
					this.list.set(m, temp);
					
					//remove elements before and after
					this.list.remove(m-1);
					this.list.remove(m);
					
					//adjust the counter for a loss of two list items
					m-=2;
						
			} else if(this.list.get(m).equals("/")) {
						
				if(Double.parseDouble(this.list.get(m+1)) == 0) {
					throw new ArithmeticException();
				}
				//parse the data into a string
				temp = Double.toString(
						//parse the data into a double to perform the operation
						Double.parseDouble(this.list.get(m-1)) / 
						Double.parseDouble(this.list.get(m+1))
						);
					//set current index to temp value
					this.list.set(m, temp);
					
					//remove elements before and after
					this.list.remove(m-1);
					this.list.remove(m);
					
					//adjust the counter for a loss of two list items
					m-=2;
				}
			}
				
			//add and subtract
			for(int a = 0; a < this.list.size(); a++){
					
				if(this.list.get(a).equals("+")) {
					//parse the data into a string
					temp = Double.toString(
							//parse the data into a double to perform the operation
						Double.parseDouble(this.list.get(a-1)) + 
						Double.parseDouble(this.list.get(a+1))
						);
					//set current index to temp value
					this.list.set(a, temp);
					
					//remove elements before and after
					this.list.remove(a - 1);
					this.list.remove(a);
					
					//adjust the counter for a loss of two list items
					a-=2;
					
				} else if(this.list.get(a).equals("-")) {
					//parse the data into a string
					temp = Double.toString(
							//parse the data into a double to perform the operation
							Double.parseDouble(this.list.get(a-1)) - 
							Double.parseDouble(this.list.get(a+1))
							);
						
					//set current index to temp value
					this.list.set(a, temp);
					
					//remove elements before and after
					this.list.remove(a - 1);
					this.list.remove(a);
					
					//adjust the counter for a loss of two list items
					a-=2;
				}
			}
				
			answer = Double.parseDouble(list.get(0));
			this.clear();
		return answer;
	}
	
}
//End of class




