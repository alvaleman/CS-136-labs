/* Alvaro Aleman (c) 2015
 *Comp sci 136
 *Lab 4- Sort phonebook data in different ways by implementing comparators
 *This class reads in the phonebook data, creates the student objects, and sorts the objects in different ways using comparators in order to answers particular questions
 */
import structure5.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    
    
    public static void main( String args[]){

	//vector that will hold Student objects
	MyVector<Student>  phonebookData = new MyVector<Student>();
	try{
	    
	    //Scan in data file, create student objects based on data, and insert objects into phonebookData array
	    Scanner S = new Scanner(new File("pbook.txt"));
	  while(S.hasNextLine()){
	      String name = S.nextLine();
	      
	      String address=  S.nextLine();
	      
	      String campusPhone =  S.next();
	      
	      int suBox = Integer.parseInt(S.next());
	      
	      String homePhone= S.next();
        
	      S.nextLine();  //blank space after particular student
	      S.nextLine(); //dashes after particular student
	      
	      Student student = new Student(name, address, campusPhone, suBox, homePhone);
	      student.setVowelCount();
	      phonebookData.add(student);
	  }
     }catch (FileNotFoundException e){
	    System.out.println("o noes!");
	}
	
	
	//sorts phonebook based on alphabetical order 
	phonebookData.sort(Student.StudentNameComparator);
	
	Student first = phonebookData.get(0);
	System.out.println("A) Student that appears first in the printed phone book if the book is arranged in alphabetical order: " + first.getName()+ "\n");
	

	
	//sorts phonebook based on SU box
	phonebookData.sort(Student.StudentSuBoxComparator);
	
	Student largest = phonebookData.get(0);
	System.out.println("B) Largest SU Box number belongs to " + largest.getName() + " with #" + largest.getSuBox()+ "\n"); 
	int lastEntry = phonebookData.size()-1;
	Student smallest = phonebookData.get(lastEntry);
	System.out.println("smallest SU Box number belongs to " + smallest.getName() + " with #" + smallest.getSuBox()+"\n"); 
	
	
	
	//sorts phonebook based on vowel count
	phonebookData.sort(Student.StudentVowelNumberComparator);
	
	Student mostVowels = phonebookData.get(0);
	System.out.println( "C) The person with the most vowels in his or her name is " + mostVowels.getName() + " with " + mostVowels.getVowelCount()+"\n");
	Student leastVowels = phonebookData.get(lastEntry);
	System.out.println( "The person with the least vowels in his or her name is " + leastVowels.getName() + " with " + leastVowels.getVowelCount()+"\n");
	
	
	
	//sorts phonebook  based on HomePhone number (Used to determine area code frequency)
	phonebookData.sort(Student.StudentHomePhoneComparator);
	
	
	//Vector that will hold AreaFrequency objects
	MyVector<AreaFrequency>  areaCodeFreq = new MyVector<AreaFrequency>();
	
	//Determines frequency for each area code
	int index=0;
     while(index< lastEntry){
	 Integer count = 0;
	 String areaCode1 = "";
     String areaCode2 = "";
     
     while(areaCode1.equals(areaCode2)){
	 areaCode1 = phonebookData.get(index).getHomePhone().substring(0,3);
	 areaCode2 = phonebookData.get(index+1).getHomePhone().substring(0,3);
	 count++;
	 index++;
	 
     }
     
     //created association if area code is valid and adds to AreaFrequency vector
     if(!areaCode1.equals(000)){
	 
	 AreaFrequency newEntry =  new AreaFrequency(areaCode1, (count+1)); 
	 areaCodeFreq.add(newEntry);
     }
     }
     
     
     //Sorts area code association vector based on frequency of area codes
     areaCodeFreq.sort(AreaFrequency.AreaCodeFreqComparator);
     System.out.println("E) Top ten most common area codes for student home phone number");
     
     //print out the top ten most common area codes
     for(int i=0; i<10; i++){
	 int areaCount = areaCodeFreq.get(i).getValue();
	 String commonArea = areaCodeFreq.get(i).getKey();
	 System.out.println((i+1) + ". " + commonArea + " with " + areaCount + " people with this area code.");
	 
     }
    }
}


