// MeiLu McDermott and Alvaro Aleman (c) 2015
// CS 136

// Answers to questions:
// Self Check Problem 3.2 - Add(v) method appends a value to the end of the vector. Add(i,v) method inserts a value into a vector such that v will be found in index i. 
// Self Check Problem 3.3 - The Add method inserts new values into the vector, logically increasing its size while no existing values are changed. The set(i,v) method replaces the value at location i with v.
// Self Check Problem 3.4 - The remove method removes the first occurrence of a value v, or a value found at location i, both decreasing the logical size of the vector. 
// Problem 3.6 - Having a special-purpose vector like BitVector allows for data structures that can branch out and hold true/false conditions to branch out further, creating a tree.

import structure5.*;
import java.util.Random;
import java.util.Scanner;

public class WordGen {


    protected static int k;              // the k-value
    protected static int output;         // the number of desired characters

    //most of these instance variables were used in our initial implementation. They are not required for the current one. 
    // protected Scanner dataIn;            // reads input
    // protected String localtext;          // the saved input text (used in
    //protected String randomText;         // the string of text that is generated


    public static void main( String args[]){
	k = 2;
	output = 10;
	Scanner dataIn = new Scanner(System.in);
	StringBuffer textBuffer = new StringBuffer();
	  while (dataIn.hasNextLine()) {
	      String line = dataIn.nextLine();
	      textBuffer.append(line);
	      textBuffer.append("\n");
	  }

	String text = textBuffer.toString();
	Table tabble = new Table();
	tabble.createTable(text, k);
	for(int i = 0; i < output - k; i++){
	    System.out.print(tabble.returnString(text.substring(i, i+k)));
	}
    }




    //We tried to implement the lab a different way initially (below). This way seemed to innefficient though as it required a lot of back and forth
    //talking between the classes. We rewrote the main class, allowing for a smaller WordGen class. The tables are being created correctly but there
    //still seems to be minor errors. By changing it up we chose to not extend the vector class, forcing us to write a few methods in the other classes
    //to make up for it. 
   
    /*
    public static void main( String args[] ){

	// creates a Scanner to read input
        Scanner dataIn = new Scanner(System.in);
        //System.out.print("What k-level? ");
        //int k = dataIn.nextInt();
	//System.out.print("How many characters of output? ");
	//int output = dataIn.nextInt();
	k = 2;
	output = 15;
	WordGen wordgen = new WordGen();

	//System.out.print("Enter text: ");
	// inputText is the full contents of the input
        StringBuffer textBuffer = new StringBuffer();
        while (dataIn.hasNextLine()) {
            String line = dataIn.nextLine();
            textBuffer.append(line);
            textBuffer.append("\n");
        }
	String text = textBuffer.toString();

        Table table = new Table();
        table.createTable( text, k );
	System.out.println(table.helpMe());
	System.out.println(wordgen.generateText( text, table ));
    }
    
    public String generateText( String text, Table table ){
	randomText = text.substring(0,k);  // starts with first two characters

	Random r = new Random();   // the random generator

	// Initialize outside for() loop, in order to conserve memory
	String stringSub = randomText.substring(0,k);
	System.out.println(stringSub);
	int tableIndex = 0;
	FrequencyList tempFre;
	int freSize;
	int randomNum;

	for(int i=0; i<output; i++){
	    Association<String,FrequencyList> testAss = new Association<String,FrequencyList>(stringSub,null);
	    System.out.println(testAss.getKey()+"key");

	    // check if the string contains the last two values of the current random string
	    if( table.contains( testAss ) ) {
		tableIndex = table.indexOf( testAss );
		
	    }   // otherwise, pick a random character and find a kLetters value that includes that characters
	    else {
		tableIndex = r.nextInt(table.size());
	    }

	    tempFre = table.getAss(tableIndex).getValue();
	    freSize = tempFre.freqSize();
	    System.out.println("fuck this lab"+freSize);
	    randomNum = r.nextInt(freSize);

	    int freqDesired = 0;
	    int x = 0;
		
	    while( freqDesired < randomNum ){
		freqDesired += tempFre.get(x).getValue();
		System.out.println( randomNum+ " value at index" + tempFre.get(0).getValue());
		x++;
	    }

	    System.out.print(x);
	    System.out.println(tempFre.get(x)+"yitong");
	    randomText = randomText + tempFre.get(x).getKey();
	    stringSub = randomText.substring(randomText.length()-k);
        }
	
        return randomText;
	
    }
    */
}
