package calculator;

import java.awt.List;
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
	StringBuffer sBuffer;
	Calculator check;
	
	
	// main metod med konsol-gränssnitt
	public static void main(String[] args) 
	
	{	
		Scanner scanner =new Scanner(System.in);
		String input = scanner.nextLine();
		Calculator calculator = new Calculator();
		String output = calculator.replaceDoubleMinus(input);
		//String output = calculator.calculateExpression(input);
		System.out.println(output);
		scanner.close();
		
	}
	
	public String replaceDoubleMinus(String s) {
		
		return s;
	}
	
	public String checkParanteses(String s){             //metod som hanterar paranteser separat
        check = new Calculator();
        while(s.contains(Character.toString('('))||s.contains(Character.toString(')'))){
            for(int o=0; o<s.length();o++){
                try{                                                        //i there is not sign
                    if((s.charAt(o)==')' || Character.isDigit(s.charAt(o))) //between separate brackets
                            && s.charAt(o+1)=='('){                         //or number and bracket,
                        s=s.substring(0,o+1)+"*"+(s.substring(o+1));        //behandlas som multiplikation
                    }                                                      
                }catch (Exception ignored){}                                //ignore out of range ex
                if(s.charAt(o)==')'){                                  //söker avslutande parantes
                    for(int i=o; i>=0;i--){
                        if(s.charAt(i)=='('){                          //söker öppnande parantes
                            String in = s.substring(i+1,o);
                            in = check.calculateExpressionBrackets(in);
                            s=s.substring(0,i)+in+s.substring(o+1);
                            i=o=0;
                        }
                    }
                }
            }
            if(s.contains(Character.toString('('))||s.contains(Character.toString(')'))||
                    s.contains(Character.toString('('))||s.contains(Character.toString(')'))){
                System.out.println("Fel: parantes fel placerad");
                return "Fel: parantes fel placerad";
            }
        }
        s=check.calculateExpression(s);
        return s;
    }
	

	
	// Huvudmetod so tar in det som matas in i miniräknaren som en sträng
	// anropar en metod beroende på input och returnerar en summa
	
	public String calculateExpression(String expression) {
	
		
		double result = 0.0;
		double mem = 0.0;
		double mem_2 = 0.0;
		int index = 0;
		ArrayList<String> upDatedList = new ArrayList<String>();
	
		// Delar upp input i en lista
		String temp[] = expression.split("(?<=[\\(\\)\\+\\-*\\/\\^A-Za-z])|(?=[\\(\\)\\+\\-*\\/\\^A-Za-z])");
		
		
		
		if (temp.length >1) 
		{
			
			
		for (int i=0; i<temp.length; i++)
		{ 	
			
			if (temp[i].equalsIgnoreCase(("+"))|| temp[i].equalsIgnoreCase(("-")))
			{mem=0.0;}
			
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
			
			
			
			// temp ska tömmas på tomma index
			
			
			
		}
		
		StringBuffer sBuffer = new StringBuffer();
	    
	    
		for (int i=0; i<temp.length; i++)
		{
			if (!temp[i].equalsIgnoreCase("")) 
			{upDatedList.add(temp[i]);} 
			
			sBuffer.append(temp[i]);
		
		}
		String updatedExpression = sBuffer.toString();
		String _temp[] = updatedExpression.split("(?<=[\\(\\)\\+\\-*\\/\\^A-Za-z])|(?=[\\(\\)\\+\\-*\\/\\^A-Za-z])");
		temp = _temp;
		
		
		
		
		
		for (int i=0; i<temp.length; i++)
		{ 
		
		if (temp[i].equalsIgnoreCase(("+"))|| temp[i].equalsIgnoreCase(("-")))
		{
			
			double d1 = Double.parseDouble(temp[i-1]);
			double d2 = Double.parseDouble(temp[i+1]);
			
			if (temp[i].equalsIgnoreCase(("+"))) 
			{
				if (mem_2==0.0) {
					result = add(d1, d2); mem_2=result;
					}
				else 
					{
					result = add(mem_2, d2); mem_2=result;
					}
			}
		
			if (temp[i].equalsIgnoreCase(("-"))) 
			{
				
				if (mem_2==0.0) 
					{
					result = subtract(d1, d2); mem_2=result;
					}
				else 
				{
					result = subtract(mem_2, d2); mem_2=result;
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
	
	
public String calculateExpressionBrackets(String expression) {
	
		
		double result = 0.0;
		double mem = 0.0;
		double mem_2 = 0.0;
		int index = 0;
		ArrayList<String> upDatedList = new ArrayList<String>();
	
		// Delar upp input i en lista
		String temp[] = expression.split("(?<=[\\(\\)\\+\\-*\\/\\^A-Za-z])|(?=[\\(\\)\\+\\-*\\/\\^A-Za-z])");
		
		
		
		if (temp.length >1) 
		{
			
			
		for (int i=0; i<temp.length; i++)
		{ 	
			
			if (temp[i].equalsIgnoreCase(("+"))|| temp[i].equalsIgnoreCase(("-")))
			{mem=0.0;}
			
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
			
			
			
			// temp ska tömmas på tomma index
			
			
			
		}
		
		StringBuffer sBuffer = new StringBuffer();
	    
	    
		for (int i=0; i<temp.length; i++)
		{
			if (!temp[i].equalsIgnoreCase("")) 
			{upDatedList.add(temp[i]);} 
			
			sBuffer.append(temp[i]);
		
		}
		String updatedExpression = sBuffer.toString();
		String _temp[] = updatedExpression.split("(?<=[\\(\\)\\+\\-*\\/\\^A-Za-z])|(?=[\\(\\)\\+\\-*\\/\\^A-Za-z])");
		temp = _temp;
		
		
		
		
		
		for (int i=0; i<temp.length; i++)
		{ 
		
		if (temp[i].equalsIgnoreCase(("+"))|| temp[i].equalsIgnoreCase(("-")))
		{
			
			double d1 = Double.parseDouble(temp[i-1]);
			double d2 = Double.parseDouble(temp[i+1]);
			
			if (temp[i].equalsIgnoreCase(("+"))) 
			{
				if (mem_2==0.0) {
					result = add(d1, d2); mem_2=result;
					}
				else 
					{
					result = add(mem_2, d2); mem_2=result;
					}
			}
		
			if (temp[i].equalsIgnoreCase(("-"))) 
			{
				
				if (mem_2==0.0) 
					{
					result = subtract(d1, d2); mem_2=result;
					}
				else 
				{
					result = subtract(mem_2, d2); mem_2=result;
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
