/* Alvaro & Chinmayi
4/7/2015
Lab 7 Prelab
 */


public class CharacterIterator extends AbstractIterator<Character> {

    String str;
    
    public CharacterIterator(String str) {
	this.str = str;

    }

    
    public Character next() {
	//pre: hasNext()
	//post: returns current valye, and then increments iterator

    }
    
    
    public boolean hasNext() {
	//post: true iff the iterator has more elements to visit
	
    }
    
    public void reset() {
	//pre: iterator may be initialized or even amid-traversal
	//post: reset iterator to the beginning of the structure 

    }

    
    public Character get() { ... }
    //pre: there are more elements to be considered; hasNext()
    //post: returns current value; ie. value next() will return 

}
