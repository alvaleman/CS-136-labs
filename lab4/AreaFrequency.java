/* Alvaro Aleman (c) 2015
 *Comp sci 136
 *Lab 4- Sort phonebook data in different ways by implementing comparators
 *This class creates associations between the areaCodes and the number of occurrences for each area code
 */

import structure5.*;
import java.util.Comparator;


public class AreaFrequency implements Comparable<AreaFrequency>{
    
    protected Association<String,Integer> AreaFrequency; //Association of areaCode and frequency of code
    protected String areaCode; //areaCode for association
    protected Integer count; //Frequency for particular code
    
    
    //Creates association between the area code and frequency
    public AreaFrequency(String areaCode, Integer count){
	super();
	this.areaCode = areaCode;
	this.count = count;
        AreaFrequency = new Association(areaCode, count);
    }
    
    
    //Compares frequency values in order to sort vector
    public int compareTo(AreaFrequency compareFreq) {
	
	int compareQuantity = compareFreq.getValue(); 
	
	//ascending order
	return this.getValue() - compareQuantity;
	
	//descending order
	//return compareQuantity - this.getValue();
	
    }
    
    //returns frequency of particular area code for an association
    public int getValue(){
	return count;
    }

    //returns particular areaCode for association
    public String getKey(){
	return areaCode;
    }
    
    //comparator used to sort by area code frequency
    public static Comparator<AreaFrequency> AreaCodeFreqComparator 
	= new Comparator<AreaFrequency>() {
		
		public int compare( AreaFrequency assoc1, AreaFrequency  assoc2) {
		    
		    //ascending order
		    //return assoc1.compareTo(assoc2);
		    
		    //descending order
		    return assoc2.compareTo(assoc1);
		}
		
	    };
    
    
}

