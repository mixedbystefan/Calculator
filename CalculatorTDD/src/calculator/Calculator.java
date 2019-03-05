package calculator;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.spi.DirStateFactory.Result;
import javax.print.attribute.ResolutionSyntax;
import javax.swing.table.DefaultTableCellRenderer;

public class Calculator {
	
	// main metod med konsol-gränssnitt
	public static void main(String[] args) 
	
	{	
		Scanner scanner =new Scanner(System.in);
		String input = scanner.nextLine();
		Calculator calculator = new Calculator();
		String output = calculator.calculateExpression(input);
		System.out.println(output);
		scanner.close();
	}
	

	
	// Huvudmetod so tar in det som matas in i miniräknaren som en sträng
	// anropar en metod beroende på input och returnerar en summa
	
	public String calculateExpression(String expression) {
	
        
		double result = 0.0;
		double mem = 0.0;
	
		// Delar upp input i en lista
		String temp[] = expression.split("(?<=[\\(\\)\\+\\-*\\/\\^A-Za-z])|(?=[\\(\\)\\+\\-*\\/\\^A-Za-z])");
		
		
		if (temp.length >1) 
		{
			
			
		for (int i=0; i<temp.length; i++)
		{ 	
			
			
			
			if (temp[i].equalsIgnoreCase(("*"))|| temp[i].equalsIgnoreCase(("/")))
			{
				
				double d1 = Double.parseDouble(temp[i-1]);
				double d2 = Double.parseDouble(temp[i+1]);
				if (temp[i].equalsIgnoreCase(("*"))) 
				{
					if (mem==0.0) 
						{
						result = multiply(d1, d2); mem=result;
						temp[i-1]="";
						temp[i]="";
						temp[i+1]=Double.toString(result);
						
						
						}
					else 
						{
						result = multiply(mem, d2); mem=result;
						temp[i-1]="";
						temp[i]="";
						temp[i+1]=Double.toString(result);
						}
				}
				if (temp[i].equalsIgnoreCase(("/"))) 
				{
					
					if (mem==0.0) 
						{
						result = divide(d1, d2); mem=result;
						temp[i-1]="";
						temp[i]="";
						temp[i+1]=Double.toString(result);
						}
					else 
					{
						result = divide(mem, d2); mem=result;
						temp[i-1]="";
						temp[i]="";
						temp[i+1]=Double.toString(result);
					}
				}
				
			
			}
			
			
			
			
			
		}
		
		for (int i=0; i<temp.length; i++)
		{ 
		
		if (temp[i].equalsIgnoreCase(("+"))|| temp[i].equalsIgnoreCase(("-")))
		{
			
			double d1 = Double.parseDouble(temp[i-1]);
			double d2 = Double.parseDouble(temp[i+1]);
			
			if (temp[i].equalsIgnoreCase(("+"))) 
			{
				if (mem==0.0) {
					result = add(d1, d2); mem=result;
					}
				else 
					{
					result = add(mem, d2); mem=result;
					}
			}
		
			if (temp[i].equalsIgnoreCase(("-"))) 
			{
				
				if (mem==0.0) 
					{
					result = subtract(d1, d2); mem=result;
					}
				else 
				{
					result = subtract(mem, d2); mem=result;
				}
			}
			
		
		}
		}
		
		}
		
		
		
		
		
		/*split = expression.split("[-]");
		if (split.length >1) 
		{ 
			double d1=0;
			for (int i=1; i<split.length; i++)
			{
				
				if (i==1) {d1 = Double.parseDouble(split[i-1]);}
				double d2 = Double.parseDouble(split[i]);
				result = subtract(d1, d2);
				d1 = result;
			}
			
			
			
			 
		}
		
		split = expression.split("[+]");
		if (split.length >1) 
		{ 
			
			for (int i=1; i<split.length; i++)
			{
				
				double d1 = Double.parseDouble(split[i-1]);
				double d2 = Double.parseDouble(split[i+1]);
				result = add(d1, d2);
				
			}
			
			
			
			 
		}*/
		
		String out = Double.toString(result);
		System.out.println("detta är final-strängen ut : " + expression);
		return out;
	
	}
	
	
	
	

	//add
	public double add(double d1, double d2) 
	{
		return d1 + d2;
	}
	//subtract
	public double subtract(double d1, double d2) 
	{
		return d1 - d2;
	}
	//multiply
	public double multiply(double d1, double d2) 
	{
		return d1 * d2;
	}
	
	//divide
		public double divide(double d1, double d2) 
		{
			return d1 / d2;
		}
	
	// modulus
	// exponent
	// logaritm
	// root

	

}
