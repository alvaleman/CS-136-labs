/* Alvaro Aleman (c) 2015
 *Comp sci 136
 *Lab 4- Student object that will be used to hold information for a particular student. Also includes compara * tors to sort data in different manners
 */

import java.util.Comparator;

public class Student implements Comparable<Student>{
    
    //Variables that will hold information for each student
    protected String name;
    protected String address;
    protected String campusPhone;
    protected int suBox;
    protected String homePhone;
    protected int vowelCount; 
    
    //Creates student object and assigns parameters to instance variables
    public Student (String name, String address, String campusPhone, int suBox, String homePhone){
	
	super();
	this.name = name;
	this.address = address;
	this.campusPhone = campusPhone;
	this.suBox = suBox;
	this.homePhone = homePhone;
	this.vowelCount = vowelCount;
	
	
	//Methods used to set and get instance variables from Student object	
    }
    
    public String getName(){
	return name;
    }
    
    public void setName(String name){
	this.name = name;
    }
    
    public String getaAddress(){
	return address;
    }
    
    public void setAddress(String address){
	this.address = address;
    }
    
    public String getCampusPhone(){
	return campusPhone;
    }
    
    public void setCampusPhone(String campusPhone){
	this.campusPhone = campusPhone;
    }
    
    public int getSuBox(){
	return suBox;
    }
    
    public void setSuBox(int suBox){
	this.suBox = suBox;
    }
    
    public String getHomePhone(){
	return homePhone;
    }
    
    public void setHomePhone(String homePhone){
	
	this.homePhone = homePhone;
    }
    
    
    //Method used to determine number of vowels in Student name
    public void setVowelCount(){
	vowelCount = 0; 
	for (int i = 0; i < name.length(); i++) {
	    char c = name.charAt(i);
	    if (c=='a' || c=='e' || c=='i' || c=='o' || c=='u' || c=='y' || c=='A' || c=='E' || c=='I' || c=='O' || c=='U' || c=='Y' ) {
		vowelCount++; 
	    }
	}
    }
    
    
    //Method used to return vowelCount
    public int getVowelCount(){
	
	return vowelCount;
    }
    
    
    
    
    
    // Method used to retrieve string representation of object
    public String toString() {
	String phoneBook =  "";
	phoneBook = name + "\n" + address + "\n" + campusPhone + " " + suBox + " " + homePhone + "\n" + "-----------------" + "\n";
	
	return phoneBook;
    }
    

    //compareTo method used to sort SU Boxes of Students
    public int compareTo(Student compareStudent) {
	
	int compareQuantity = compareStudent.getSuBox(); 
	
	//ascending order
	return this.suBox - compareQuantity;
	
	//descending order
	//return compareQuantity - this.homeZip;
	
    }
    
    
    
    //Comparator for Student Names
    public static Comparator<Student> StudentNameComparator 
	= new Comparator<Student>() {
		
		public int compare(Student student1, Student student2) {
		    
		    String studentName1 = student1.getName().toUpperCase();
		    String studentName2 = student2.getName().toUpperCase();
		    
		    //ascending order
		    return studentName1.compareTo(studentName2);
		    
		    //descending order
		    //return studentName2.compareTo(studentName1);
		}
		
	    };
    
    
    //Comparator for Student SU Boxes
    public static Comparator<Student> StudentSuBoxComparator 
	= new Comparator<Student>() {
		
		public int compare(Student student1, Student student2) {
		    
		    //ascending order
		    //return student1.compareTo(student2);
		    
		    //descending order
		    return student2.compareTo(student1);
		}
		
	    };
    
    //Comparator for Student Vowel Number
    public static Comparator<Student> StudentVowelNumberComparator 
	= new Comparator<Student>() {
		
		public int compare(Student student1, Student student2) {
		    
		    int vowelCount1 = student1.getVowelCount();
		    int vowelCount2 = student2.getVowelCount();
		    
		    //ascending order
		    //return vowelCount1-vowelCount2;
		    
		    //descending order
		    return vowelCount2-vowelCount1;
		}
		
	    };
    
    
    //Comparator for Student Home Phone number
    public static Comparator<Student> StudentHomePhoneComparator 
	= new Comparator<Student>() {
		
		public int compare(Student student1, Student student2) {
		    
		    String studentPhone1 = student1.getHomePhone();
		    String studentPhone2 = student2.getHomePhone();
		    
		    //ascending order
		    return studentPhone1.compareTo(studentPhone2);
		    
		    //descending order
		    //return studentName2.compareTo(studentName1);
		}
		
	    };
    
    
}
