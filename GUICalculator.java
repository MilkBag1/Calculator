/**
 * Program Name: GUICalculator.java
 * Purpose: 	 Create an application that simulates a cell phonecalculator
 * @author		Matthew Taylor
 * @version      1.0
 * @since        Apr. 7, 2021
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUICalculator extends JFrame
{
	//declare variables/objects
	private JMenuBar mnuBar;
    private JMenu mnuFile;
    private JMenu mnuConvert;
    private JMenu mnuHelp;
    
    private JMenuItem mnuExit;
    private JMenuItem mnuHow2;
    private JMenuItem mnuAbout;
    
    private ButtonGroup mnuGroup;
    private JRadioButton mnuDec;
    private JRadioButton mnuOct;
    private JRadioButton mnuBin;
    private JRadioButton mnuHex;
    
	private JPanel pButtons;
	private JTextArea calcScreen;
	
	private Font font;
	private Font bFont;
	
	private JButton numButtons[];
	
	private JButton cmdClear;
	private JButton cmdBack;
	private JButton cmdPercent;
	private JButton cmdPlusMinus;
	private JButton cmdSquared;
	private JButton cmdSqrt;
	private JButton cmdBlank1;
	private JButton cmdDivide;
	private JButton cmdMultiply;
	private JButton cmdSubtract;
	private JButton cmdAdd;
	private JButton cmdBlank2;
	private JButton cmdDecimal;
	private JButton cmdEquals;
	
	public GUICalculator() {
		
		//call super
		super("Calculator");

		//attach a calculator object to handle button and logic operations
		Calculator calc = new Calculator();
		
		//set layout for frame
		setLayout(new BorderLayout());
		
		//set font for screen
		font = new Font("SansSerif", Font.PLAIN, 22);
		bFont = new Font("SansSerid", Font.BOLD, 16);
		
		//create and add screen
		calcScreen = new JTextArea("0.0", 1, 20);
		calcScreen.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		calcScreen.setFont(font);
		calcScreen.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		//calcScreen.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		calcScreen.setAlignmentX(RIGHT_ALIGNMENT);
		calcScreen.setEditable(false);
		add(calcScreen,BorderLayout.NORTH);
		
		//create and add button panel
		buildButtonPanel(calc);
		add(pButtons,BorderLayout.CENTER);
		buildMenuBar(calc);
		
		//suggested settings
		setSize(300, 365); 
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true); 
	}
	
	/**
	 *
	 * @param calc
	 */
	private void buildButtonPanel(Calculator calc) {
		
		//create and set layout of pButtons
		pButtons = new JPanel();
		pButtons.setLayout(new GridLayout(6,4,2,2));
		
		//create numButtons array
		numButtons = new JButton[10];
		
		
		cmdClear = new JButton("C");
		cmdClear.setFont(bFont);
		cmdClear.setBackground(Color.lightGray);
		cmdClear.addActionListener(new ActionListener () {
			
			public void actionPerformed(ActionEvent e) {
				//reset all calculator values
				calc.clear();
				
				//reset screen text to "0.0"
				calcScreen.setText("0.0");
			}
		});
		pButtons.add(cmdClear);
		
		
		cmdBack = new JButton("Del");
		cmdBack.setFont(bFont);
		cmdBack.setBackground(Color.lightGray);
		cmdBack.addActionListener(new ActionListener () {
			
			public void actionPerformed(ActionEvent e) {
				try {
					calc.setOperand(calc.backspace(calc.getOperand()));
					calcScreen.setText(calc.getOperand());
				}
				catch(EmptyOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
				
				if(!calc.getOperand().contains(".")) {
					calc.setDecimalPressed(false);
				}
			}
		});
		pButtons.add(cmdBack);
		
		
		cmdPercent = new JButton("%");
		cmdPercent.setFont(bFont);
		cmdPercent.setBackground(Color.lightGray);
		cmdPercent.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e){
				try {
					calc.setOperand(calc.findPercentage(calc.getOperand()));
					calcScreen.setText(calc.getOperand());
				}
				catch(EmptyOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
		});
		pButtons.add(cmdPercent);
		
		cmdPlusMinus = new JButton("+/-");
		cmdPlusMinus.setFont(bFont);
		cmdPlusMinus.setBackground(Color.lightGray);
		cmdPlusMinus.addActionListener(new ActionListener () {
			
			public void actionPerformed(ActionEvent e) {
				try {
					calc.setOperand(calc.togglePlusMinus(calc.getFlag()));	
					calcScreen.setText(calc.getOperand());
				}
					
				catch (EmptyOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
				
				catch (LongOperandException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
		});
		pButtons.add(cmdPlusMinus);
		
		cmdSquared = new JButton("X^2");
		cmdSquared.setFont(bFont);
		cmdSquared.setBackground(Color.lightGray);
		cmdSquared.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e){
				try {
					calc.setOperand(calc.findSquared(calc.getOperand()));
					calcScreen.setText(calc.getOperand());
				}
				catch(EmptyOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
		});
		pButtons.add(cmdSquared);
		
		cmdSqrt = new JButton("Sqrt");
		cmdSqrt.setFont(bFont);
		cmdSqrt.setBackground(Color.lightGray);
		cmdSqrt.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
					calc.setOperand(calc.findSquareRoot(calc.getOperand()));
					calcScreen.setText(calc.getOperand());
				}
				catch(EmptyOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
		});
		pButtons.add(cmdSqrt);
		
		cmdBlank1 = new JButton();
		cmdBlank1.setBackground(Color.lightGray);
		pButtons.add(cmdBlank1);
		
		cmdDivide = new JButton("/");
		cmdDivide.setFont(bFont);
		cmdDivide.setBackground(Color.lightGray);
		cmdDivide.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
					calc.buildExpression("/");
					calcScreen.setText(calc.getOperand());
				}
				catch(EmptyOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
		});
		pButtons.add(cmdDivide);
		
		//create the numButtons
		for (int i = 0; i < numButtons.length; i++) {
			numButtons[i] = new JButton("" + i);
			numButtons[i].setBackground(Color.WHITE);
			numButtons[i].addActionListener(new ActionListener () {
				public void actionPerformed(ActionEvent e) {
					
					//loop through number button to find source, and add it to the screen
					for(int i = 0; i < numButtons.length; i++) {
							
						//add value of button to the text area
						if(e.getSource() == numButtons[i]) {
							try {
								calc.buildOperand(String.valueOf(i));
								calcScreen.setText(calc.getOperand());
							}
							catch(LongOperandException a){
								JOptionPane.showMessageDialog(null, a.getMessage());
							}
						}
					}
				}
			});
		}
		
		//add remaining buttons
		pButtons.add(numButtons[7]);
		pButtons.add(numButtons[8]);
		pButtons.add(numButtons[9]);
		
		cmdMultiply = new JButton("x");
		cmdMultiply.setFont(bFont);
		cmdMultiply.setBackground(Color.lightGray);
		cmdMultiply.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
					calc.buildExpression("x");
					calcScreen.setText(calc.getOperand());
				}
				catch(EmptyOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
		});
		pButtons.add(cmdMultiply);
		
		pButtons.add(numButtons[4]);
		pButtons.add(numButtons[5]);
		pButtons.add(numButtons[6]);
		
		cmdSubtract = new JButton("-");
		cmdSubtract.setFont(bFont);
		cmdSubtract.setBackground(Color.lightGray);
		cmdSubtract.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
					calc.buildExpression("-");
					calcScreen.setText(calc.getOperand());
				}
				catch(EmptyOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
		});
		pButtons.add(cmdSubtract);
		
		pButtons.add(numButtons[1]);
		pButtons.add(numButtons[2]);
		pButtons.add(numButtons[3]);
		
		cmdAdd = new JButton("+");
		cmdAdd.setFont(bFont);
		cmdAdd.setBackground(Color.lightGray);
		cmdAdd.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
					calc.buildExpression("+");
					calcScreen.setText(calc.getOperand());
				}
				catch(EmptyOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
		});          
		pButtons.add(cmdAdd);
		
		cmdBlank2 = new JButton();
		cmdBlank2.setBackground(Color.lightGray);
		pButtons.add(cmdBlank2);
		
		pButtons.add(numButtons[0]);
		
		cmdDecimal = new JButton(".");
		cmdDecimal.setFont(bFont);
		cmdDecimal.setBackground(Color.lightGray);
		cmdDecimal.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if(calc.isDecimalPressed()) {
					//nothing can happen, maybe an error message
				} else {
					try {
						calc.buildOperand(".");
						calcScreen.setText(calc.getOperand());
						calc.setDecimalPressed(true);
					}
					catch(LongOperandException a){
						JOptionPane.showMessageDialog(null, a.getMessage());
					}
				}
			}
		});
		
		pButtons.add(cmdDecimal);
		
		cmdEquals = new JButton("=");
		cmdEquals.setFont(bFont);
		cmdEquals.setBackground(Color.cyan);
		cmdEquals.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				double answer = 0.0;
				
				try {
					answer = calc.calculate();
					
					calcScreen.setText("" + answer);
				}
				catch(EmptyOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
				catch(ArithmeticException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}		
			}
		});
		pButtons.add(cmdEquals);
	}
	
	private void buildMenuBar(Calculator calc) {
		
		mnuBar = new JMenuBar();
		
		buildFileMenu();
		buildConvertMenu(calc);
		buildHelpMenu();
		
		mnuBar.add(mnuFile);
		mnuBar.add(mnuConvert);
		mnuBar.add(mnuHelp);
		setJMenuBar(mnuBar);
	}
	
	private void buildFileMenu() {
		
		mnuFile = new JMenu("File");
		mnuExit = new JMenuItem("Exit");
		mnuExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
		);
		
		
		mnuFile.add(mnuExit);
		
	}
	
	private void buildHelpMenu() {
		
		mnuHelp = new JMenu("Help");
		mnuHow2 = new JMenuItem("How To Use");
		mnuAbout = new JMenuItem("About");
		
		mnuHow2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Please enter in your calculations. You can use the Convert menu option"
						+ " to change between values.",
						"How to Use This Program", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		mnuAbout.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "This calculator was created by co-authors Matt and Maximus"
						+ " to prove their massive big brain java skills",
						"About This Program", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		mnuHelp.add(mnuHow2);
		mnuHelp.add(mnuAbout);
	}

	private void buildConvertMenu(Calculator calc) {
		
		mnuConvert = new JMenu("Convert");
		mnuHex = new JRadioButton("Hex", false);
		mnuHex.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				//hex conversion
				try {
				calcScreen.setText(calc.convertHex());
				}
				catch(LongOperandException a){
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
			});
		
		mnuDec = new JRadioButton("Dec", true);
		mnuDec.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				calcScreen.setText(calc.getOperand());
			}
			});
		
		mnuOct = new JRadioButton("Oct", false);
		mnuOct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
				calcScreen.setText(calc.convertOct());
				}
				catch(LongOperandException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
			});
		
		mnuBin = new JRadioButton("Bin", false);
		mnuBin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
				calcScreen.setText(calc.convertBin());
				}
				catch(LongOperandException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
			});
		
		mnuGroup = new ButtonGroup();
		mnuGroup.add(mnuHex);
		mnuGroup.add(mnuDec);
		mnuGroup.add(mnuOct);
		mnuGroup.add(mnuBin);
		
		mnuConvert.add(mnuHex);
		mnuConvert.add(mnuDec);
		mnuConvert.add(mnuOct);
		mnuConvert.add(mnuBin);
	}
	
	public static void main(String[] args)
	{
		GUICalculator frame = new GUICalculator();

	}
	//End of main method
}
//End of class