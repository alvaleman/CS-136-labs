/*Alvaro Aleman
 *Lab 6 
 *3/18/2015
 * This lab implements an interpreter forpost script language using a stackList
 */  

import structure5.*;

public class Interpreter{
    
    protected StackList<Token> s; //StackList used to keep track of post script tokens
    protected SymbolTable table; //Table used to keep track of symbols defined by the user or post scipt file
    
    
    //Creates an instance of the reader and interpreter
    public static void main(String args[]){
	Interpreter i = new Interpreter();
	Reader r = new Reader();
	i.interpret(r);
	
	}
    
    
    //Constructor creates the stacklist and symbol table that will keep track of the Tokens and Symbols
    public Interpreter(){
	
	s = new StackList<Token>();
	table = new SymbolTable();
	
	
    }

    //Goes through the post script file and assigns an action for each Token
    public void interpret(Reader r){
	
	Token t;
	while (r.hasNext()){
	    
	    t = (Token)r.next();
	    
	    //Quit command ends the process
	    if (t.isSymbol() && t.getSymbol().equals("quit")) break;
	    
	    //Numbers are automatically added to StackList 
	    if(t.isNumber() ){
		
		s.add(t);
	    }
	    
	    //Gives guidelines for each of the symbols and directs to appropriate method
	    if (t.isSymbol()){
		
		if(t.getSymbol().equals("add")){
		    
			addition();
			
		}else if(t.getSymbol().equals("sub")){
		    
		    subtraction();
		    
		}else if (t.getSymbol().equals("mul")) {
		    
		    multiplication();
		    
		}else if (t.getSymbol().equals("div")){
		    
		    division();
		    
		}else if(t.getSymbol().equals("dup")){
		    
		    duplicate();
		    
		}else if(t.getSymbol().equals("exch")){
			
		    exchange();
		    
		}else if(t.getSymbol().equals("eq")){
		    
		    equal();
		    
		}else if(t.getSymbol().equals("ne")){
		    
		    notEqual();
		    
		    
		}else if(t.getSymbol().equals("pop")){

		    Assert.condition(s.size()>0 , "Can not empty list with 0 tokens");
		    s.pop();
		    
		}else if(t.getSymbol().equals("ptable")){
		    
		    table.toString();
		    
		}else if(t.getSymbol().equals("pstack")){
		    
		    System.out.println(s.toString());
		    
		} else if(t.getSymbol().substring(0,1).equals("/")){
		    
		    s.add(t);
		    
		} else if (t.getSymbol().equals("def")){
		    
		    define();
		    
		    
		    //For user defined symbols	
		}else if (t.isSymbol() && table.contains(t.getSymbol())){
		    
		    Token token = table.get(t.getSymbol());
		    
		    s.add(token);
		    
		    
		}else
		    System.out.println(t.getSymbol() + " has not been defined yet");
		
	    }
	}
    }
    
    
    
    //Takes top two tokens of stack, adds them, and places result at the top of the stack 
    public void addition(){
	Assert.condition(s.size()>1 , "Not enough values for math operation");
	Token first = s.get();
	double num1 = first.getNumber();
	s.pop();
	Token second = s.get();
	double num2 = second.getNumber();
	s.pop();
	double result = num1 + num2;
	
	Token item = new Token(result);
	s.add(item);
    }
    
    
    //Takes top two tokens of stack, subtracts them, and places result at the top of the stack 
    public void subtraction(){
	Assert.condition(s.size()>1 , "Not enough values for math operation");
        Token first = s.get();
	double num1 = first.getNumber();
	s.pop();
	Token second = s.get();
	double num2 = second.getNumber();
	s.pop();
	double result = num2 - num1;
	Token item = new Token(result);
	s.add(item);
	
    }
    
    //Takes top two tokens of stack, multiplies them, and places result at the top of the stack 
    public void multiplication(){
	Assert.condition(s.size()>1 , "Not enough values for math operation");
        Token first = s.get();
	double num1 = first.getNumber();
	s.pop();
	Token second = s.get();
	double num2 = second.getNumber();
	s.pop();
	double result = num1*num2;
	Token item = new Token(result);
	s.add(item);
    }
    
    //Takes top two tokens of stack, divides them, and places result at the top of the stack 
    public void division(){
	Assert.condition(s.size()>1 , "Not enough values for math operation");
        Token first = s.get();
	double num1 = first.getNumber();
	s.pop();
	Token second = s.get();
	double num2 = second.getNumber();
	s.pop();
	double result = num2/num1;
	Token item = new Token(result);
	s.add(item);
    }
    
    //Creates a copy of the Token on the top of the stack 
    public void duplicate(){
	Assert.condition(s.size()>0 , "No value available to duplicate");
	Token first = s.get();
	s.add(first);
	
    }
    
    //Exchanges the position of the top two elements in the stack 
    public void exchange(){
	Assert.condition(s.size()>1 , "Not enough values to exchange");
	Token first = s.get();
	s.pop();
	Token second = s.get();
	s.pop();
	s.add(first);
	s.add(second);
    }
    
    //Returns boolean of top two tokens being equal 
    public void equal(){
	Assert.condition(s.size()>1 , "Not enough values to test");
	
	Token first = s.get();
	s.pop();
	Token second = s.get();
	s.pop();
        boolean equal = first.equals(second);
	Token item = new Token(equal);
	s.add(item);
    }
    
    //returns boolean of the top two tokens not being equal
    public void notEqual(){
	Assert.condition(s.size()>1 , "Not enough values for test");
	
	Token first = s.get();
	s.pop();
	Token second = s.get();
	s.pop();
        boolean equal = !first.equals(second);
	Token item = new Token(equal);
	s.add(item);
    }

    //Defines new symbol and value attached to symbol and adds it to SymbolTable
    public void define(){
	Assert.condition(s.size()>1 , "Not enough values to define a new symbol");
			
	Token value = s.get();
	s.pop();
	Token symbolTok = s.get();
	s.pop();
	String symbol  = symbolTok.getSymbol().substring(1);
	table.add(symbol, value);
	
    }

    
}








