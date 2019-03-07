package calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
	StringBuffer sBuffer;
	StringBuffer sBuf;
	Calculator check;
	boolean memoryInUse=false;
	
	
	// main metod med konsol-gränssnitt
	public static void main(String[] args) 
	
	{	
		Scanner scanner =new Scanner(System.in);
		String input = scanner.nextLine();
		Calculator calculator = new Calculator();
		String output = calculator.checkParanteses(input);
		System.out.println(output);
		scanner.close();
		
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
                }catch (Exception ignored){}                                //ignorera of range ex
                if(s.charAt(o)==')'){                                  //söker avslutande parantes
                    for(int i=o; i>=0;i--){
                        if(s.charAt(i)=='('){                          //söker öppnande parantes
                            String in = s.substring(i+1,o);
                            in = check.calculateExpression(in);   // här är s rätt
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
	
		// Ersätter -- med +
		String twoMinusEqPlus = expression.replace("--", "+");
		
		// Om första char är ett minus så läggs en 0 till innan - 0-1..osv
				if (twoMinusEqPlus.substring(0, 1).equalsIgnoreCase("-")){
					String updatedInput= "0" + twoMinusEqPlus;
					twoMinusEqPlus = updatedInput;
				}
		
		// Delar upp input i en lista
		String inputList[] = twoMinusEqPlus.split("(?<=[\\(\\)\\+\\-*%√\\/\\^A-Za-z])|(?=[\\(\\)\\+\\-*%√\\/\\^A-Za-z])");
		
		
		
		if (inputList.length >1) 
		{
			
			for (int i=0; i<inputList.length; i++)
			{ 
			
				
			
				
				if (inputList[i].equalsIgnoreCase(("√"))) 
				{ 
					
					int d = Integer.parseInt(inputList[i+1]);
					result = root(d);
					inputList[i]="";
					inputList[i+1]=Double.toString(result);
					mem=0.0;
					
					
				
				}
				
				if (inputList[i].equalsIgnoreCase(("^"))) 
				{ 
					
					double d1 = Double.parseDouble(inputList[i-1]);
					double d2 = Double.parseDouble(inputList[i+1]);
					result = exponent(d1, d2);
					inputList[i-1]="";
					inputList[i]="";
					inputList[i+1]=Double.toString(result);
					mem=0.0;
					
				
				}
				
			
			}	
			
			/*sBuf = new StringBuffer();
			for (int i=0; i<inputList.length; i++)
			{
				
				
				sBuf.append(inputList[i]);
			
			}
			String _updatedExpression = sBuf.toString();
			String _temporary[] = _updatedExpression.split("(?<=[\\(\\)\\+\\-*%\\/\\^A-Za-z])|(?=[\\(\\)\\+\\-*%\\/\\^A-Za-z])");
			inputList = _temporary;*/
			
			inputList = updateList(inputList);
			
		for (int i=0; i<inputList.length; i++)
		{ 	
			// Tar bort + om detta inleder ekvationen
			if(inputList[0].equalsIgnoreCase("+")) {inputList[0]=""; continue;}
			if(inputList[inputList.length-1].equalsIgnoreCase("+")) {inputList[inputList.length-1]="";}
			
			if (inputList[i].equalsIgnoreCase(("+"))|| inputList[i].equalsIgnoreCase(("-")))
			{mem=0.0;}
			
			
			
			if (inputList[i].equalsIgnoreCase(("*"))|| inputList[i].equalsIgnoreCase(("/"))|| inputList[i].equalsIgnoreCase(("%")))
			{
				
				double d1 = Double.parseDouble(inputList[i-1]);
				//int _d1 = Integer.parseInt(temp[i-1]);
				double d2 = Double.parseDouble(inputList[i+1]);
				//int _d2 = Integer.parseInt(temp[i+1]);
				if (inputList[i].equalsIgnoreCase(("*"))) 
				{
					if (mem==0.0) 
						{
						result = multiply(d1, d2); mem=result;
						inputList[i-1]="";
						inputList[i]="";
						inputList[i+1]=Double.toString(result);
						
						
						}
					else 
						{
						result = multiply(mem, d2); mem=result;
						inputList[i-1]="";
						inputList[i]="";
						inputList[i+1]=Double.toString(result);
						}
				}
				if (inputList[i].equalsIgnoreCase(("/"))) 
				{
					
					if (mem==0.0) 
						{
						result = divide(d1, d2); mem=result;
						inputList[i-1]="";
						inputList[i]="";
						inputList[i+1]=Double.toString(result);
						}
					else 
					{
						result = divide(mem, d2); mem=result;
						inputList[i-1]="";
						inputList[i]="";
						inputList[i+1]=Double.toString(result);
					}
				}
				
				if (inputList[i].equalsIgnoreCase(("%"))) 
				{
					
					if (mem==0.0) 
						{
						System.out.println("Hej");
						int _d1 = (int)d1;
						int _d2 = (int)d2;
						int res = modulus(_d1, _d2);
						inputList[i]="";
						inputList[i-1]="";
						inputList[i+1]=Integer.toString(res);
						
						
						}
					else 
					{
						System.out.println("Svejs");
						int _d2 = (int)d2;
						int _mem= (int) mem;
						int res = modulus(_mem, _d2);
						inputList[i]="";
						inputList[i-1]="";
						inputList[i+1]=Integer.toString(res);
					}
				}
				
			
			}
			
			
			
			
			
			
			
		}
		
		inputList = updateList(inputList);
		
		
		
		
		
		for (int i=0; i<inputList.length; i++)
		{ 
			if(inputList[0].equalsIgnoreCase("+")) {inputList[0]=""; continue;}
			if(inputList[inputList.length-1].equalsIgnoreCase("+")) {inputList[inputList.length-1]="";}
		
		if (inputList[i].equalsIgnoreCase(("+"))|| inputList[i].equalsIgnoreCase(("-")))
		{
			double d1 = 0;
			if (i==0) {continue;}
			else {d1 = Double.parseDouble(inputList[i-1]);}
			double d2 = Double.parseDouble(inputList[i+1]);
			
			if (inputList[i].equalsIgnoreCase(("+"))) 
			{
				if (mem_2==0.0 && memoryInUse==false) {
					result = add(d1, d2); mem_2=result; memoryInUse=true;
					}
				else  
					{
					result = add(mem_2, d2); mem_2=result;
					}
			}
		
			if (inputList[i].equalsIgnoreCase(("-"))) 
			{
				if ((inputList[0].equalsIgnoreCase("-")))
					{
					
					
					}
				if (mem_2==0.0 && memoryInUse==false) 
					{
					result = subtract(d1, d2); mem_2=result; memoryInUse=true;
					}
				else 
				{
					result = subtract(mem_2, d2); mem_2=result;
				}
			}
			
		
		}
		}
		
		}
		
		
		
		String out = Double.toString(result);
		System.out.println("Detta är output från mainmetod : " + out);
		return out;
	
	}
	
	public String[] updateList(String[] listToUpdate) {
		StringBuffer sBuffer = new StringBuffer();
	    
	    
		for (int i=0; i<listToUpdate.length; i++)
		{
						
			sBuffer.append(listToUpdate[i]);
		
		}
		String updatedExpression = sBuffer.toString();
		String _temp[] = updatedExpression.split("(?<=[\\(\\)\\+\\-*%\\/\\^A-Za-z])|(?=[\\(\\)\\+\\-*%\\/\\^A-Za-z])");
		listToUpdate = _temp;
		return listToUpdate;
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
	
	// modulus %
		
	public int modulus(int d1, int d2) 
	{
		return Math.floorMod(d1, d2);
	}
		
	// root √
		
	public double root(double d1) 
	{
		return Math.sqrt(d1);
	}
	// exponent ^
	
	public double exponent(double d1, double d2 ) 
	{
		return Math.pow(d1, d2);
	}

	
	// logarithm log
	
	public double logarithm(double d1) 
	{
		return Math.log(d1);
	}
	
}