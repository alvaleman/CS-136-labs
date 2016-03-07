// MeiLu McDermott and Alvaro Aleman (c) 2015
// CS 136

// javac -cp bailey.jar FrequencyList.java Table.java WordGen.java
// (bailey.jar must be in same file, or can use a filepath)
//This class creates a list of frequencies of every character after each k-letter pair in the input text. 

import java.util.Random;
import structure5.*;

public class FrequencyList{

    protected Vector<Association<String,Integer>> freqsList;

    public FrequencyList(){
        freqsList = new Vector<Association<String,Integer>>();
    }

    //Returns the total frequencies in a frequency list 
    public int freqSize(){
	int totalFreq = 0;
	for(int i=0; i<freqsList.size(); i++) {
	    totalFreq += freqsList.get(i).getValue();
	}
	return totalFreq;
    }

    public void add(Association<String,Integer> ass){
	freqsList.add(ass);
    }
    
    public boolean contains(Association<String,Integer> ass){
	return freqsList.contains(ass);
    }

    //determines the output letter for randomly generated text 
    public String returnLetter(){
	Random r = new Random();
	int i = r.nextInt(freqSize());
	int d = 0;
	int s = 0;
	while(  i >= s){
	    s += freqsList.get(d).getValue();
	}
	return freqsList.get(d).getKey();
    }
    
    public int size(){
	return freqsList.size();
    }

    public int indexOf(Association<String, Integer> ass){
	return freqsList.indexOf(ass);

    }


    
    public Association<String, Integer> get (int index){
	return freqsList.get(index);
    }
    
}
