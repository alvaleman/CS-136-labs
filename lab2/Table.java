// MeiLu McDermott and Alvaro Aleman (c) 2015
// CS 136

//This table class is responsible for constructing a vector of associations between each k-letter pairs of letters and
//their respective frequency lists.
import structure5.*;
import java.util.Random;

public class Table{
    
    protected Vector < Association <String, FrequencyList> > mainTable;

    public Table() {
    	mainTable = new Vector<Association <String, FrequencyList> >();
    }

 
    public void createTable( String text, int k ) {
	// go through table for each k-value of letters
	for (int i=0; i < text.length()-k-1; i++){
	    update( text.substring(i,i+k) , text.substring(i+k,i+k+1) );
	}
    }
    
    //kLetters is every substring sequence with length k. d is the character that directly follows s.
    public void update(String kLetters, String following){
	Association<String,FrequencyList> tempAss = new Association<String,FrequencyList>(kLetters,null);
	if( !mainTable.contains(tempAss) ){   // checks if kLetters is already created in the Table
	    FrequencyList tempFr = new FrequencyList();
	    tempFr.add( new Association(following,1) );
	    mainTable.add( new Association(kLetters, tempFr) );

	} else {  // checks if the following character is contained in the FrequencyList
	    int kIndex = mainTable.indexOf(tempAss);
	    FrequencyList tempFr = mainTable.get(kIndex).getValue();
	    Association<String,Integer> tempIntAss = new Association<String,Integer>(following,null);
	    if( tempFr.contains(tempIntAss) ) {
		int index = tempFr.indexOf(tempIntAss);
		int oldInt = tempFr.get(index).getValue();
		tempFr.get(index).setValue(new Integer(oldInt + 1));
	    } else {
		tempFr.add(new Association(following,1) );
	    }
	}
    }

    public String helpMe(){
	return mainTable.toString();
    }

    public Association<String,FrequencyList> getAss(int x){
	return mainTable.get(x);
    }
    
    public int size(){
	return mainTable.size();
    }

    public void add(Association<String,FrequencyList> ass){
	mainTable.add(ass);
    }

    
    //returns a random string based on relative probabilities of frequencies
    public String returnString(String kstring){
	Association temp;
	if(mainTable.contains(new Association( kstring, null))){
	    temp = getAss(mainTable.indexOf(new Association(kstring, null)));
	}
	else{
	    Random r = new Random();
	    temp = getAss(r.nextInt(mainTable.size()));
       }	
	FrequencyList list =(FrequencyList) temp.getValue();
	Random r = new Random();
	int i = r.nextInt(list.size());
	int d = 0;
	int s = 0;
	while(  i >= s){
	    s += list.get(d).getValue();
	    d++;
	}
	System.out.print(list.get(d) + "list");
	return list.get(d).getKey();
    }
    
    public boolean contains(Association<String,FrequencyList> ass){
	return mainTable.contains(ass);
    }
    
    public int indexOf(Association<String, FrequencyList> ass){
	return mainTable.indexOf(ass);
    }

    public Association<String, FrequencyList> get (int index){
	return mainTable.get(index);
    }
}
