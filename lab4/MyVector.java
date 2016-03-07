/* Alvaro Aleman (c) 2015
 *Comp sci 136
 *Lab 4- Sort phonebook data in different ways by implementing comparators
 *This class is an extension of the vector class in which we implement an insertionSort method
 * that will be used to sort the vector based on the camparator parameter that is passed
 */ 
import structure5.*;
import java.util.Comparator;

public class MyVector<Student> extends Vector<Student> {
    
    public MyVector(){
	super();
    }
    
    //Taken from Data Structures textbook
    //pre: c is a valid comparator
    //post: sort this vector in order determined by
    public void sort(Comparator<Student> c){
	int numSorted = 0;
	int index; //general index
	while(numSorted < size()){
	    //take the first unsorted value
	    Student temp = get(numSorted);
	    //...and insert it among the sorted:
	    for (index = numSorted; index >0; index--){
		if((c.compare(temp, get(index-1)))<0){
		    set(index, get(index-1));
		}else{
		    break;
		}
	    }
	    //reinsert value
	    set(index,temp);
	    numSorted++;
	}
    }
    
    
}
